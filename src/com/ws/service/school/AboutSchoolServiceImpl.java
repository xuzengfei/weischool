package com.ws.service.school;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.school.AboutSchool;
import com.ws.service.school.dao.AboutSchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/3/14.
 */
@Service
public class AboutSchoolServiceImpl implements AboutSchoolService {
    @Autowired
    private AboutSchoolDao aboutSchoolDao;

    @Override
    public ResultCode addOrUp(AboutSchool aboutSchool) {
        if (aboutSchool.getId() == null || "".equals(aboutSchool.getId()))
            this.aboutSchoolDao.add(aboutSchool);
        else
            this.aboutSchoolDao.update(aboutSchool);
        return ResultCode.SUCCESS;
    }

    @Override
    public AboutSchool get() {
        List<AboutSchool> list = aboutSchoolDao.findAll();
        if (list != null && !list.isEmpty())
            return list.get(0);
        return null;
    }
}
