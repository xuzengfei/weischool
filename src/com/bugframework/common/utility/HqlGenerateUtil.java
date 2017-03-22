package com.bugframework.common.utility;

import com.bugframework.auth.pojo.UserAccount;
import com.bugframework.common.Enum.DateIntervalEnum;
import com.bugframework.common.annotation.DateColumn;
import com.bugframework.common.annotation.ForeKey;
import com.bugframework.common.exception.IllegalDataException;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class HqlGenerateUtil {

    private static final String SUFFIX_COMMA = ",";
    private static final String SUFFIX_KG = " ";
    /**
     * 模糊查询符号
     */
    private static final String SUFFIX_ASTERISK = "*";
    // --update-begin--Author:coco Date:20130520 for：模糊查询
    private static final String SUFFIX_ASTERISK_VAGUE = "%";
    // --update-begin--Author:coco Date:20130520 for：模糊查询
    /**
     * 不等于查询符号
     */
    private static final String SUFFIX_NOT_EQUAL = "!";
    private static final String SUFFIX_NOT_EQUAL_NULL = "!NULL";

    /**
     * 时间查询符号
     */
    private static final String END = "end";
    private static final String BEGIN = "begin";
    private static final Logger logger = Logger.getLogger(HqlGenerateUtil.class);

    /**
     * 自动生成查询条件HQL 模糊查询 【只对Integer类型和String类型的字段自动生成查询条件】
     *
     * @param cq
     * @param searchObj
     * @throws Exception
     */
    public void installHql(Criteria cq, Object searchObj,
                           HttpServletRequest request) {
        Class clazz = searchObj.getClass();
        Field[] fields = searchObj.getClass().getDeclaredFields();//获得属性
        Field[] pfields = searchObj.getClass().getSuperclass().getDeclaredFields();//获得父属性
        if (fields.length > 0)
            listField(fields, searchObj, request, clazz, cq);
        if (pfields.length > 0)
            listField(pfields, searchObj, request, clazz, cq);
    }

    private void listField(Field[] fields, Object searchObj, HttpServletRequest request, Class clazz, Criteria cq) {
        Map<String, String> hasAlias = new HashMap<String, String>();
        for (Field field : fields) {
            String name = field.getName();// 属性名称
            String type = field.getGenericType().toString();// 属性类型
            Object value = null;
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();//获得get方法
                boolean methodAnnotation = getMethod.isAnnotationPresent(Column.class);
                boolean dateAnnotation = getMethod.isAnnotationPresent(DateColumn.class);
                boolean isforeKeyAnnotation = getMethod.isAnnotationPresent(ForeKey.class);
                boolean isIdAnnotation = getMethod.isAnnotationPresent(Id.class);
                if (methodAnnotation) {
                    value = PropertyUtils.getSimpleProperty(searchObj, name);
                    if (value == null && !dateAnnotation) {
                        continue;
                    }
                } else if (isforeKeyAnnotation) {
                    value = PropertyUtils.getSimpleProperty(searchObj, name);
                    if (value == null) {
                        continue;
                    }
                    Annotation foreKeyAnnotation = getMethod.getAnnotation(ForeKey.class);
                    Method categoryMethod = foreKeyAnnotation.getClass().getDeclaredMethod("category", null);
                    Method columnMethod = foreKeyAnnotation.getClass().getDeclaredMethod("column", null);
                    Method idMethod = foreKeyAnnotation.getClass().getDeclaredMethod("id", null);
                    String category = (String) categoryMethod.invoke(foreKeyAnnotation, null);
                    String column = (String) columnMethod.invoke(foreKeyAnnotation, null);
                    Boolean id =(Boolean)idMethod.invoke(foreKeyAnnotation, null);
                    if (category == null) {
                        logger.error(clazz + " 中ForeKey 注解出问题：值为空");
                        return;
                    }
                    if (column == null) {
                        logger.error(clazz + " 中column 注解出问题：值为空");
                        return;
                    }
                    isIdAnnotation =id;
                    if (!hasAlias.containsKey(category)) {
                        cq.createAlias(category, category);
                        hasAlias.put(category, "1");
                    }
                    name = category + "." + column;
                    //cq.add(Expression.eq(category+"."+column, value));
                    // continue;
                } else {
                    continue;
                }

                if ("class java.lang.String".equals(type)) {
                    //cq.add(Expression.like(name, "%" + searchValue + "%"));
                    if(isIdAnnotation)
                        cq.add(Expression.eq(name, value));
                    else
                         cq.add(Expression.like(name, value + "%"));
                    continue;

                } else if ("class java.lang.Integer".equals(type)) {
                    cq.add(Expression.eq(name, value));
                    continue;
                } else if ("class java.math.BigDecimal".equals(type)) {
                    cq.add(Expression.eq(name, value));
                    continue;
                } else if ("class java.lang.Short".equals(type)) {
                    cq.add(Expression.eq(name, value));
                    continue;
                } else if ("class java.lang.Long".equals(type)) {
                    // 可能是时间类型，所以要判断
                    if (value != null && !"".equals(value)) {
                        cq.add(Expression.eq(name, value));
                    } else {
                        if (dateAnnotation) {
                            String startTime = request.getParameter(name + "_" + DateIntervalEnum.START);
                            String endTime = request.getParameter(name + "_" + DateIntervalEnum.END);
                            if (startTime != null && !"".equals(startTime)) {
                                cq.add(Expression.ge(name, Long.valueOf(startTime)));
                            }
                            if (endTime != null && !"".equals(endTime))
                                cq.add(Expression.le(name, Long.valueOf(endTime)));
                        }
                    }
                    continue;
                } else if ("class java.util.Date".equals(type)) {
                    // 判断开始时间
                    cq.add(Expression.eq(name, (Date) value));
                    continue;
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }


    /**
     * 得到对象属性中所有name
     *
     * @param origDescriptors
     * @return
     */
    private static String getDescriptorsNames(
            PropertyDescriptor origDescriptors[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < origDescriptors.length; i++) {
            sb.append(origDescriptors[i].getName() + ",");
        }
        return sb.toString();
    }

    public static AjaxJson datagrid(DataGrid<?> datagrid) {
        AjaxJson j = new AjaxJson();
        j.setObj(datagrid);
        j.setSuccess(true);
        return j;
    }


    public static boolean isSearch(String name) {
        return false;
    }

    public static List<String> getSearchColumn(Object obj) {
        List<String> list = new ArrayList<String>();
        Class pojo = obj.getClass();
        Method[] methodList = pojo.getMethods();
        for (int j = 0; j < methodList.length; j++) {
            Method method = methodList[j];
            boolean methodAnnotation = method.isAnnotationPresent(Column.class);

            if (methodAnnotation) {

                try {

                    Object value = method.invoke(obj);
                    if (value != null && !"".equals(value)) {
                        list.add(method.getName());//执行get方法返回一个Object
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
        try {
            Class clazz = obj.getClass();
            Field[] fields = obj.getClass().getDeclaredFields();//获得属性
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();//获得get方法
                Object o = getMethod.invoke(obj);//执行get方法返回一个Object
                boolean methodAnnotation = getMethod.isAnnotationPresent(Column.class);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return list;
    }

    /**
     * 通过反射获得更新的HQL语句
     *
     * @param obj 传入对象值
     * @return 返回  Map<String, Object>  key :hql（返回更新的sql字符串） 和 data(返回Map<String,Object> 属性名和属性值)，如果出现异常则返回null
     */
    public Map<String, Object> getUpdHql(Object obj) {
        Map<String, Object> hqlMap = new HashMap<String, Object>();
        String paramHql = "";
        String idHql = "";
        Integer key = 0;
        Object idValue = null;
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        try {
            PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(obj);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();// 属性名称
                boolean methodAnnotation = origDescriptors[i].getReadMethod().isAnnotationPresent(Column.class);
                boolean IdAnnotation = origDescriptors[i].getReadMethod().isAnnotationPresent(Id.class);
                boolean isforeKeyAnnotation = origDescriptors[i].getReadMethod().isAnnotationPresent(ForeKey.class);
                Object value = null;
                if (IdAnnotation) {
                    value = PropertyUtils.getSimpleProperty(obj, name);
                    if (value == null) {
                        throw new IllegalDataException("ID 不能为空");
                    }
                    idHql = name + "= ?";
                    //map.put(name, value);
                    idValue = value;
                    continue;
                }
                if (methodAnnotation) {
                    value = PropertyUtils.getSimpleProperty(obj, name);
                    if (value != null) {
                        paramHql += name + "=?,";
                        map.put(key, value);
                        key++;
                        continue;
                    }
                }
                if (isforeKeyAnnotation) {
                    value = PropertyUtils.getSimpleProperty(obj, name);
                    if (value == null) {
                        continue;
                    }
                    Annotation foreKeyAnnotation = origDescriptors[i].getReadMethod().getAnnotation(ForeKey.class);
                    Method categoryMethod = foreKeyAnnotation.getClass().getDeclaredMethod("category", null);
                    Method columnMethod = foreKeyAnnotation.getClass().getDeclaredMethod("column", null);
                    Method idMethod = foreKeyAnnotation.getClass().getDeclaredMethod("id", null);
                    String category = (String) categoryMethod.invoke(foreKeyAnnotation, null);
                    String column = (String) columnMethod.invoke(foreKeyAnnotation, null);
                    Boolean id = (Boolean) idMethod.invoke(foreKeyAnnotation, null);
                    if (category == null) {
                        logger.error(obj.getClass().getSimpleName() + " 中ForeKey 注解出问题：值为空");
                        return null;
                    }
                    if (column == null) {
                        logger.error(obj.getClass().getSimpleName() + " 中column 注解出问题：值为空");
                        return null;
                    }
                    if(id) {
                        paramHql += category + "." + column + "=?,";
                        map.put(key, value);
                        key++;
                    }
                    continue;
                }
            }
            if (!paramHql.equals("")) {
                paramHql = paramHql.substring(0, paramHql.length() - 1);
            }
            map.put(key, idValue);
            hqlMap.put("hql", "update " + obj.getClass().getSimpleName() + " set " + paramHql + " where  " + idHql);
            hqlMap.put("data", map);
            return hqlMap;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public static void main(String[] args) {

        UserAccount user = new UserAccount();
        user.setId("1212");
        user.setPassword("1234");
        user.setAccount("ssssssssssssss");

        Map<String, Object> map = new HqlGenerateUtil().getUpdHql(user);
        String hql = (String) map.get("hql");
        Map<String, Object> paraMap = (Map<String, Object>) map.get("data");
        System.out.println(hql);
        for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        /*PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(user);
		// 获得对象属性中的name

		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			System.out.println(origDescriptors[i].getWriteMethod());
		}*/
		
		/*String paramSql="";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(user);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();// 属性名称
				boolean methodAnnotation = origDescriptors[i].getReadMethod().isAnnotationPresent(Column.class);
				 Object value = null;
				if(methodAnnotation){
					 value = PropertyUtils.getSimpleProperty(user, name);
					 if(name.equals("id")){
						 if (value == null) {
							 throw new IllegalDataException("ID 不能为空");
						 }
						 paramId =value.toString();
							continue;
						}
					  if (value != null) {
						  paramSql+=name+"=?,";
						  map.put(name, value);
					  } 
				}
			}
			if(!paramSql.equals("")){
				paramSql=paramSql.substring(0,paramSql.length()-1);
			}
			String sql ="update "+user.getClass().getSimpleName()+" set "+paramSql+" where id=?";
			System.out.println(sql);
		} catch (Exception e) {
			 
		}*/

    }


}
