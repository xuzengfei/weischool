package com.ws.service.school;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.school.AboutSchool;

/**
 * Created by admin on 2017/3/14.
 */
public interface AboutSchoolService {
    ResultCode addOrUp(AboutSchool aboutSchool);

    AboutSchool get();
}
