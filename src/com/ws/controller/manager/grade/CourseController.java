package com.ws.controller.manager.grade;

import java.util.List;

import com.ws.pojo.grade.GradeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.grade.TimeInfo;
import com.ws.service.grade.GradeTimeService;
import com.ws.service.grade.TimeService;

@Controller
@RequestMapping("/web/manager/course")
public class CourseController {
	@Autowired
	private GradeTimeService gradeTimeService;
	@Autowired
	private TimeService timeService;

	@RequestMapping(value = "/{gradId}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String gradId, String graName) {
		return new ModelAndView("/grade/courselist")
				.addObject("gradId", gradId).addObject("graName", graName);
	}

	@RequestMapping(value = "/to/add/time", method = RequestMethod.GET)
	public ModelAndView timeadd() {
		return new ModelAndView("/grade/coursetimeadd");
	}
	@RequestMapping(value = "/add/time", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson addTime(String start,String end){
		if(start==null||end==null){
			return new AjaxJson("数据不合法", false, null);
		}
		TimeInfo timeInfo = new TimeInfo();
		timeInfo.setTitle(start+"-"+end);	
		timeInfo.setCreateBy(ResourceUtil.getUserSession().getId());
		timeInfo.setCreateTime(System.currentTimeMillis());
		ResultCode code =this.timeService.add(timeInfo);
		return new AjaxJson().result(code);
	}
	@RequestMapping(value = "/list/time", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listTime(){
		List<TimeInfo> list = this.timeService.list(ResourceUtil.getUserSession().getId());
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setObj(list);
		return j;
	}
	@RequestMapping(value = "/del/time/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delTime(@PathVariable String id){
		ResultCode code  = this.timeService.del(id);
		return new AjaxJson().result(code);
	}

	@RequestMapping(value = "/add/gradetime/{gradId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson addGradeTime(@PathVariable String gradId, GradeTime gradeTime){
		gradeTime.setGradId(gradId);
		ResultCode code =this.gradeTimeService.add(gradeTime);
		return new AjaxJson().result(code);
	}
	@RequestMapping(value = "/list/gradetime/{gradId}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson listGradTime(@PathVariable String gradId,Long start,Long end){
		List<GradeTime> list = this.gradeTimeService.list(gradId,start,end);
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setObj(list);
		return j;
	}
	@RequestMapping(value = "/del/gradetime/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delGradeTime(@PathVariable String id){
		ResultCode code  = this.gradeTimeService.del(id);
		return new AjaxJson().result(code);
	}
}
