package com.bugframework.common.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.Enum.DateIntervalEnum;
import com.bugframework.common.Enum.TagEnum;
import com.bugframework.common.utility.DataUtils;


 

public class DataGridTag  extends TagSupport{

	private String name;//表格ID，和名称
	private String actionUrl;//提交的路径
	private boolean	   pagination =true;//是否分页
	private String  width;//表格宽度
	private boolean checkbox =false;//是否显示复选框
	private String  style ="tablelist";//表格样式：普通列表tablelist（默认）,图片列表imgtable
	private String  onLoadSuccess;//数据加载成调用方法
	private String  onDblClick;//双击调用方法
	private String  onClick;//单击调用方法
	private String queryMode="single";//查询模式：single(单条件查询：默认),group(组合查询)
	private String keywordField;
	
	private List<DataGridColumn> datacDataGridColumns = new ArrayList<DataGridColumn>();//列数组
	private List<DataGridButton> czBtnList = new ArrayList<DataGridButton>();//操作里面的按钮操作
	private List<DataGridButton> toolBars = new ArrayList<DataGridButton>();//表头的按钮操作
	private List<String> fields = new ArrayList<String>();//存放参数名称
	
	private List<DataGridColumn> queryFields = new ArrayList<DataGridColumn>();//存放需要查询的列
	
	private short colspan = 0;//显示的列数
	
	//public RoleDao service = (RoleDao)SpringContextUtil.getBean("systemService");
	
	public void setName(String name) {
		this.name = name;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}
	public void setOnDblClick(String onDblClick) {
		this.onDblClick = onDblClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
	
	public void setKeywordField(String keywordField) {
		this.keywordField = keywordField;
	}
	
	public String setKeyWorkName(){
		for(DataGridColumn c:this.queryFields){
			return c.getTitle();
		}
		if(this.queryFields.isEmpty()){
			return "[keywordField输入不正确或者为空]";
		}
		return null;
	}
	/**
	 * 存放操作里面的按钮
	 * @param czBtnList
	 */
	public void setCzBtn(DataGridButton czBtnList) {
		this.czBtnList.add(czBtnList);
	}
	
	/**
	 * 存放表格的列的各种参数
	 * @param column
	 */
	public void setDataGridColumns(DataGridColumn column){
		fields.add(column.getField());
		if(this.queryMode.equals("group")){//组合查询
			if(column.isQuery()){
				queryFields.add(column);
			}
		}
		if(this.queryMode.equals("single")){//关键字查询
			if(column.getField().equals(this.keywordField)){
				queryFields.add(column);
			}
		}
		if(column.isHidden()==false){
			colspan++;
		}
		datacDataGridColumns.add(column);
	} 
	
	
	/**
	 * 存放表头的按钮
	 * @param button
	 */
	public void setToolBar(DataGridButton button) {
		toolBars.add(button);
		
	}
	/*****开始执行方法***/
	@Override
	public int doStartTag() throws JspException {
		datacDataGridColumns.clear();
		czBtnList.clear();
		fields.clear();
		toolBars.clear();
		queryFields.clear();
		colspan=0;
		return EVAL_PAGE;
	}
	/*******结束执行方法**************/
	@Override
	public int doEndTag() throws JspException {
		
		try {
			setTableHead();//设置表头
		 	setTableValue();//设置表值
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	/**
	 * 设置表的顶部按钮
	 * @throws IOException
	 */
	public StringBuffer setTableButton()  throws IOException{
		StringBuffer sb = new StringBuffer();
		if(this.toolBars==null||this.toolBars.size()==0)//如果为空，则不执行下去
			return sb.append("<div class=\"cl bg-1  mt-20\"></div>");
		sb.append("<div class=\"cl pd-5 bg-1 bk-gray mt-20\">");
		sb.append("<span class=\"l\">");
		for(DataGridButton db:this.toolBars){
		 	String icon=db.getIcon();
			if(icon!=null)
				icon="<i class=\"Hui-iconfont\">"+icon+"</i>";
			String name = TagUtil.getFunction(db.getFunname());
			String parmars = TagUtil.getFunParams(db.getFunname());
			sb.append("<a href=\"javascript:void(0);\" onclick=" + name + "(" + parmars + ")   class=\"btn "+db.getBtClass()+" radius\">");
			sb.append(icon+" "+db.getTitle());
			sb.append("</a> ");
		}
        
       sb.append("</span>"); 
       sb.append("</div>");
		return sb;
	}
	/**
	 * 设置查询按钮
	 * @return
	 * @throws IOException
	 */
	public StringBuffer setSearch() throws IOException{
		StringBuffer sb = new StringBuffer();
		if(this.queryFields==null||this.queryFields.size()==0)//如果为空那么就不执行下去
			return sb;
		sb.append("<div class=\"text-c seachform\" id=\""+this.name+"_seachform\" > ");
		for(DataGridColumn column:this.queryFields){
			if(DataUtils.isStrNotEmpty(column.getReplace())){
				sb.append("<span class=\"select-box inline\"  >");
					sb.append("<select name='"+column.getField()+"'  class=\"select  "+this.queryMode+"Class\">");
						sb.append("<option value=''>请输入"+column.getTitle()+"</option>");    
						String[] selectarray = column.getReplace().split(",");
						 for(int i =0;i<selectarray.length;i++){
							 sb.append("<option value='"+selectarray[i].split("_")[0]+"'>"+selectarray[i].split("_")[1]+"</option>");	 
						}
					sb.append("</select>");
				sb.append("</span>");
			}else if(DataUtils.isStrNotEmpty(column.getFormatter())){
				sb.append(column.getTitle()+":<input type=\"text\" name='"+column.getField()+"_"+DateIntervalEnum.START+"'  onfocus='WdatePicker({dateFmt:\""+column.getFormatter()+"\"})'   class=\"input-text Wdate "+this.queryMode+"Class\" style=\"width:120px;\">");
				sb.append("-<input type=\"text\" name='"+column.getField()+"_"+DateIntervalEnum.END+"'  onfocus='WdatePicker({dateFmt:\""+column.getFormatter()+"\"})'   class=\"input-text Wdate "+this.queryMode+"Class\" style=\"width:120px;\">");
			}else{
				sb.append("<input type=\"text\" class=\"input-text "+this.queryMode+"Class\" style=\"width:150px\" placeholder=\"输入"+column.getTitle()+"\" name='"+column.getField()+"' >");
			}
		}
		 
		sb.append("<button type=\"button\" class=\"btn btn-success scbtn\" onclick='doSearch()'><i class=\"Hui-iconfont\">&#xe665;</i>查询</button>");
		sb.append("</div>");
        return sb;
	}
	/**
	 * 设计表头
	 * @throws IOException
	 */
	public void setTableHead() throws IOException{
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		 
		sb.append("<div class=\"pd-20\" id=\""+this.name+"\" action=\""+this.actionUrl+"\" >");
		//插入搜索按钮
		sb.append(setSearch());
		//插入表头操作按钮
		sb.append(setTableButton());
		sb.append("<div   class=\"dataTables_wrapper no-footer\">");
		sb.append("<table class=\"table table-border table-bordered table-hover table-bg table-sort\">");
		sb.append("<thead>");
		sb.append("<tr class=\"text-c\">");
		if(this.checkbox) 
			sb.append("<th width=\"25\"><input type=\"checkbox\" id=\""+this.name+"_checkall\" name=\"\" value=\"\"></th>");
		for(DataGridColumn column:this.datacDataGridColumns){
			if(column.getField().equals("opt"))
				sb.append("<th width="+column.getWidth()+">操作</th>");
			else{
				String ishidden ="";
				if(column.isHidden()){
					ishidden =" style=\"display:none\"";
				}
				sb.append("<th "+ishidden+" width="+column.getWidth()+" >"+column.getTitle()+"</th>");
			}
		}
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append("<tbody>");
		///此处插入数据，在下一步操作通过js赋值
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("</div>");
		sb.append("</div>");
	 
		out.print(sb.toString()); 
		out.flush(); 
		 
	}
	/**
	 * 设置表的值
	 */
	public void setTableValue() throws IOException{
	//	 List<AuthRole> role= service.getRoleList();
		 JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>");
		sb.append("var queryMode ='"+this.queryMode+"' ;");
		sb.append("var searchParams  ={};");//定义搜索列名称
		
		for(DataGridColumn c:this.datacDataGridColumns){
			sb.append("searchParams['"+c.getField()+"']=null;");
		}
		sb.append("searchParams['pageNo']=1;");//设置分页的初始值
		
		
		//回车登录
		sb.append("$('.seachform').keydown(function(e){");
				sb.append("if(e.keyCode == 13) {");
						sb.append("	doSearch();");
				sb.append("}");
		sb.append("});");
		
		sb.append("$(function(){");
		sb.append("	$(\"#"+this.name+"_checkall\").click(function() { ");
		sb.append("var flag = $(this).attr(\"checked\"); ");
		sb.append("$(\"input[name="+this.name+"_checkbox]:checkbox\").each(function() {");
		sb.append("if(flag==undefined){ $(this).removeAttr(\"checked\");}else{$(this).attr(\"checked\", flag);} ");
		sb.append("}) ");
		sb.append("}) ");
		sb.append("});");
		
		
		sb.append("function roadDataGrid(){");
		sb.append("var url = $('#"+this.name+"').attr('action');");
		sb.append("var getdata = {};");
	 	sb.append(" getdata =getSearchParams(searchParams);");
		sb.append("$.ajax({");
		sb.append("type:'get',");
		sb.append("url:url,");
		sb.append("data:getdata,");
		sb.append("datatype:'json', ");
		sb.append("contentType: \"application/x-www-form-urlencoded; charset=utf-8\",");
		sb.append("async:false,");
		sb.append("success:function(data){ ");
		sb.append("if(data.success==true){ ");
			sb.append("var flag =0;");
			sb.append("var items =data.obj.datas;");
			sb.append("$('#"+this.name+" tbody').html('');");//清空旧数据
			if(this.pagination){
				sb.append("$('#"+this.name+"_pagin').html('');");//清空分页栏目
			}
			sb.append("var field ;");
			sb.append("var ishide ;");
			sb.append("var html='';");
			sb.append("$.each(items,function(i,value){");
			
				sb.append("flag=1;");
				sb.append("html+='<tr class=\"text-c\" >';");
				if(this.checkbox){
					sb.append("html+='<td><input name=\""+this.name+"_checkbox\" type=\"checkbox\" value=\"'+value.id+'\"   /></td>';");
				}
				for(DataGridColumn column:this.datacDataGridColumns){
					//String onclickHtml ="";//是否点击事件
					 if(DataUtils.isStrNotEmpty(column.getFunname())){//是否文本点击事件
						 	String name = TagUtil.getFunction(column.getFunname());
							String parmars = TagUtil.getFunParams(column.getFunname());
							sb.append("var textfunname =\"" + name + "(" + TagUtil.setValueReplace(parmars, fields) + ")\";");
					 }
					
					if(column.isImage()){
						if(column.getIcon()!=null)
							sb.append("html+='<td><a href=\"javascript:;\" onclick=\"'+funname+'\"><i class=\"Hui-iconfont\">"+column.getIcon()+"</i></a></td>';");
						else
							sb.append("html+='<td><a href=\"javascript:;\" onclick=\"'+funname+'\"><img width=\""+column.getImgWidth()+"\" class=\"picture-thumb\" src=\""+TagUtil.uplaodPath(pageContext)+"'+field+'\"></a></td>';");
					}else if(column.getField().equals(TagEnum.OPTION.toString())){//操作
						 sb.append("var czhtml='';");
						for(DataGridButton button:czBtnList){
							String name = TagUtil.getFunction(button.getFunname());
							String parmars = TagUtil.getFunParams(button.getFunname());
							String iconHtml="";
							if(DataUtils.isStrNotEmpty(button.getIcon())){//判断是否有按钮样式
								iconHtml="<i class=\"Hui-iconfont\">"+button.getIcon()+"</i>";
							}
							sb.append("var funname =\"" + name + "(" + TagUtil.setValueReplace(parmars, fields) + ")\";");
							if(DataUtils.isStrNotEmpty(button.getExp())){//这是拓展方法，通过嵌入表达式控制这个按钮是显示还是隐藏
								String[] exps= button.getExp().split("#");
								String exps_Start = exps[0];
								String exps_Middle=exps[1].replace("eq", "==").replace("ne", "!=");
								String exps_End=exps[2].replace("empty", "");
								if(DataUtils.isNumeric(exps_End)){
									sb.append("  if(value."+exps_Start+exps_Middle+exps_End+" ){");
								}else{
									sb.append(" if(value."+exps_Start+exps_Middle+"\""+exps_End+"\" ){");
								}
								
								 sb.append(" czhtml+='<a class=\"ml-5\" style=\"text-decoration:none\" onclick=\"'+funname+'\" href=\"javascript:;\" title=\""+button.getTitle()+"\">"+iconHtml+button.getTitle()+"</a>';");
								 sb.append("}");
							 }else{
								 
						  	 sb.append("   czhtml+='<a class=\"ml-5\" style=\"text-decoration:none\" onclick=\"'+funname+'\" href=\"javascript:;\" title=\""+button.getTitle()+"\">"+iconHtml+button.getTitle()+"</a>';");
							 }
							
						}
						 sb.append("html+='<td class=\"td-manage\">'+czhtml+'</td>';");//存放操作按钮
						 
					}else {
						sb.append("	var hide=\"\";");
						if(column.isHidden()){
							sb.append("	  hide=\"display:none\";");
						}

						sb.append(" field =value."+column.getField()+";if(field==null){field=''}");
						 if(DataUtils.isStrNotEmpty(column.getReplace())){
							sb.append(" field =replaceField(field,'"+column.getReplace()+"');");	
						} 
						 
						if(DataUtils.isStrNotEmpty(column.getFormatter())){
							sb.append("if(!isNaN(field)){field =  new Date().format(\""+column.getFormatter()+"\",field);}");
						}
					   if(DataUtils.isStrNotEmpty(column.getFunname())){
							sb.append("html+='<td  class=\""+column.getTdClass()+"\" style=\"text-align:"+column.getAlign()+";'+hide+'\"  ><a class=\"maincolor\" href=\"javascript:;\"  onclick=\"'+textfunname+'\"   >'+field+'</a></td>';");
					   }else{
						   sb.append("html+='<td   class=\""+column.getTdClass()+"\" style=\"text-align:"+column.getAlign()+";'+hide+'\" >'+field+'</td>';");
					   }
						
					}
				}
				
		       sb.append("html+='</tr>';");
		       if(DataUtils.isStrNotEmpty(this.onLoadSuccess)){
		       sb.append(this.onLoadSuccess);
		       }
			sb.append("});");
		
			sb.append("$('#"+this.name+" tbody').append(html);");
			sb.append(" if(flag==0){");
			if(this.checkbox){//如果是拥有选择列的话，这里要加一
				this.colspan++;
			}
		    sb.append(" $('#"+this.name+" tbody').append('<tr class=\"text-c\" ><td colspan=\""+this.colspan+"\"  >暂无数据！</td></tr>');");
		    sb.append("}");
		    
		    if(this.pagination){
		    /*****分页部分 start*******/
		    sb.append("searchParams['pageNo']=data.obj.pageNo;");//设置当前页面
		    sb.append("$(\"#"+this.name+"_info\").remove();");
		    sb.append("$(\"#"+this.name+"_pagin\").remove();");
		    sb.append("var pageNo = data.obj.pageNo;");//当前页数
		    sb.append("var pageCount =data.obj.pageCount;");//所有页数
			sb.append("var pageSize =data.obj.pageSize;");//一页显示数
		    sb.append("var i=pageNo-pageSize;");
		    sb.append("var size = pageCount;");
		    sb.append("html='<div class=\"dataTables_info\" id=\""+this.name+"_info\" role=\"status\" aria-live=\"polite\">共'+data.obj.recordCount+'条记录，当前显示第'+data.obj.pageNo+'页</div>';");
		    sb.append("html+='<div class=\"dataTables_paginate paging_full_numbers\" id=\""+this.name+"_pagin\" >';");
		    sb.append("html+='<a class=\"paginate_button first disabled\" href=\"javascript:firstPage('+data.obj.pageNo+');\"  >第一页</a>';");
		    sb.append("html+='<a class=\"paginate_button previous disabled\" href=\"javascript:prevPage('+data.obj.pageNo+');\"  >上一页</a>';");
		    sb.append("html+='<span>';");
		    

		 /*	sb.append("if(pageNo-pageSize<=0){");
		    sb.append(" i=1;");
		    sb.append("}");
		    sb.append("if(pageNo>pageCount){");
		    sb.append("	size = pageCount;");
		    sb.append(" }");
	 	    sb.append("for(;i<=size;i++){");
		    sb.append("if(i==pageNo){");//当前页码,把样式current 添加上
		    sb.append("	html+='<span><a class=\"paginate_button current\">'+i+'</a></span>';");	
		    sb.append("}else{");
		    sb.append("html+='<span><a class=\"paginate_button\" href=\"javascript:currentPage('+pageNo+','+i+');\"   >'+i+'</a></span>';");
		    sb.append("}");
		    sb.append("}");*/
		    sb.append("var start = pageNo-5>0?(pageNo-5):1;");
			sb.append("var end =pageNo+4>pageCount?pageCount:(pageNo+4);");
			sb.append("if(end<10&&10<pageCount)");
			sb.append("		end=10;");
			sb.append("for(var i=start;i<=end;i++){");
			sb.append("if(i==pageNo){");//当前页码,把样式current 添加上
			sb.append("	html+='<span><a class=\"paginate_button current\">'+i+'</a></span>';");
			sb.append("}else{");
			sb.append("html+='<span><a class=\"paginate_button\" href=\"javascript:currentPage('+pageNo+','+i+');\"   >'+i+'</a></span>';");
			sb.append("}");
			sb.append("}");
		    sb.append("html+='</span>';");
		    sb.append("html+='<a class=\"paginate_button next disabled\" href=\"javascript:nextPage('+pageNo+','+pageCount+');\" >下一页</a>';");
		    sb.append("html+='<a class=\"paginate_button last disabled\"  href=\"javascript:lastPage('+pageNo+','+pageCount+');\" >最后一页</a>';");
		    sb.append("html+='</div></div>';");
		    sb.append("$('#"+this.name+">.dataTables_wrapper').append(html);");

		    }
		 sb.append("}"); 
       sb.append("  },"); 
         //调用出错执行的函数
       sb.append(" error: function(data){"); 
       sb.append("	 alert('网络异常');"); 
       sb.append(" } "); 
		sb.append("});");
		sb.append("}");
		
		sb.append("roadDataGrid();");//////////////////////////执行datagrid
		//sb.append("$('."+this.style+" tbody tr:odd').addClass('odd');");
		sb.append("</script>");
		
		out.print(sb.toString());
		out.flush();
		
	}
	
	
	
}
