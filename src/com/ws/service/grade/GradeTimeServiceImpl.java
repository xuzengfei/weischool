package com.ws.service.grade;

import java.util.List;

import com.ws.pojo.student.Student;
import com.ws.pojo.student.StudentGrade;
import com.ws.service.student.StudentGradeService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugframework.common.utility.ResultCode;
 
import com.ws.pojo.grade.GradeTime;

import com.ws.service.grade.dao.GradeTimeDao;

@Service("gradeTimeService")
public class GradeTimeServiceImpl implements GradeTimeService{
    @Autowired
    private GradeTimeDao dao;

	@Override
	public ResultCode add(GradeTime t) {
		if(t.getGradId()==null||t.getStart()==null||t.getEnd()==null)
			return ResultCode.INVALID;
		dao.add(t);
		return ResultCode.SUCCESS;
	}

	@Override
	public List<GradeTime> list(String gradId, Long start, Long end) {
		Criteria cq = this.dao.getSession().createCriteria(GradeTime.class);
		cq.add(Expression.eq("gradId",gradId));
	 	cq.add(Expression.ge("start",start));
 		cq.add(Expression.le("end",end));
		List<GradeTime> list  =cq.list();
		return list;
	}

	@Override
	public List<GradeTime> list(String[] gradIdArray, Long start, Long end) {
	/*	List<StudentGrade> list = this.studentGradeService.findOrderRestClass(cpId, userId);
		if(list!=null&&!list.isEmpty()){
			String[] gradIdArray= new String[list.size()];
			for (int i=0;i<list.size();i++){
				gradIdArray[i] =list.get(i).getGradeId();
			}
			Criteria cq = this.dao.getSession().createCriteria(GradeTime.class);
			cq.add(Expression.in("gradId",gradIdArray));
			cq.add(Expression.ge("start",start));
			cq.add(Expression.le("end",end));
			cq.addOrder(Order.asc("start"));
			return cq.list();
		}*/
		Criteria cq = this.dao.getSession().createCriteria(GradeTime.class);
		cq.add(Expression.in("gradId",gradIdArray));
		cq.add(Expression.ge("start",start));
		cq.add(Expression.le("end",end));
		cq.addOrder(Order.asc("start"));
		return cq.list();
	}

	/**
	 * 验证是否很烦
	 * @param t
	 * @return
	 */
	private  boolean isValid(GradeTime t){
		List<GradeTime> list = this.dao.find("from GradeTime where graId=?  and ((start >? and start<?)  or( end >? and end<?)  or (start<? and end>?)) ", t.getGradId(),t.getStart(),t.getEnd(),t.getStart(),t.getEnd(),t.getStart(),t.getEnd());
		if(list==null||list.size()==0)
			return true;
		else return false;
	}

	@Override
	public ResultCode del(String id) {
		 if(id==null)
			 return ResultCode.INVALID;
		 this.dao.delete(id);
		return ResultCode.SUCCESS;
	}
}
