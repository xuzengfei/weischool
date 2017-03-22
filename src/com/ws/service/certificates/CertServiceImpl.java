package com.ws.service.certificates;

import com.bugframework.common.pojo.DataGrid;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.certificates.Certificates;
import com.ws.service.attach.AttachService;
import com.ws.service.certificates.dao.CertDao;
import com.ws.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 荣誉证书服务接口实现层
 *
 * @author 许增飞
 */
@Service(value = "certService")
public class CertServiceImpl implements CertService {

    @Autowired
    private CertDao certDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AttachService attachService;

    @Override
    public ResultCode add(Certificates cert) {

        if (cert.getStudent() == null || cert.getStudent().getNo() == null
                || "".equals(cert.getStudent().getNo()))
            return ResultCode.INVALID;
        cert.setStudent(studentService.findByNo(cert.getStudent().getNo()));
        if (cert.getStudent() == null)
            return ResultCode.NOEXIST;
        this.certDao.add(cert);
        this.attachService.edit(cert.getPics(), cert.getId());
        return ResultCode.SUCCESS;
    }

    @Override
    public ResultCode edit(Certificates cert) {

        if (cert.getId() == null)
            return ResultCode.INVALID;
        this.certDao.update(cert);
        return ResultCode.SUCCESS;
    }

    @Override
    public Certificates get(String id) {
        if (id == null)
            return null;
        return this.certDao.get(id);
    }

    @Override
    public void datagrid(Certificates cert, DataGrid<Certificates> datagrid,
                         HttpServletRequest request) {
        this.certDao.datagrid(cert, datagrid, request);

    }

    @Override
    public ResultCode del(Certificates cert) {
        if (cert.getId() == null)
            return ResultCode.INVALID;
        this.certDao.update(cert);
        this.attachService.edit(cert.getId(),1);
        return ResultCode.SUCCESS;
    }

}
