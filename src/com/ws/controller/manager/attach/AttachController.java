package com.ws.controller.manager.attach;

import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.utility.BaseUpload;
import com.bugframework.common.utility.ResourceUtil;
import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.attach.Attach;
import com.ws.service.attach.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Created by admin on 2017/1/10.
 */
@Controller
@RequestMapping("/web/manager/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @param moduleType 模块类型：参考枚举类 ModuleType
     * @param moduleId   模块的ID
     * @param filetype   文件类型：1--图片 2--文件
     * @param single     上传数量控制 1--单文件 2--多文件（控制一次最多只能上传5个文件）
     * @return
     */
    @RequestMapping(value = "/{moduleType}/{moduleId}/{filetype}/{single}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson upload(HttpServletRequest request, HttpServletResponse response, @PathVariable String moduleType, @PathVariable String moduleId, @PathVariable int filetype, @PathVariable int single) {
        AjaxJson j = new AjaxJson();
        BaseUpload baseUpload = new BaseUpload();
        baseUpload.setThumb(true);
        baseUpload.setThumbW(100);
        baseUpload.setThumbH(100);
        baseUpload.setFileDir("upload/" + moduleType);
        if (filetype == 1) {
            baseUpload.setAllowSuffix("jpg,jpeg,bmp,png");
        } else {
            baseUpload.setAllowSuffix("jpg,jpeg,bmp,png,doc,txt,pdf,doc,docx,xls,xlsx,wps,et");
        }
        if (single == 1) {
            MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
            baseUpload.setAllowSize(1);
            baseUpload.upload(file, request, response, j);
            if (j.isSuccess()) this.editData(moduleId, moduleType, file, baseUpload.getFileName(), j);
        } else {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            baseUpload.setAllowSize(5);
            baseUpload.uploads(files, request, response, j);
            String[] filePaths = baseUpload.getFileNames();
            for (int i = 0; i < files.size(); i++) {
                this.editData(moduleId, moduleType, files.get(i), filePaths[i], j);
            }
        }
        return j;
    }

    private void editData(String moduleId, String moduleType, MultipartFile file, String filePath, AjaxJson j) {
        Attach attach = new Attach();
        attach.setModuleId(moduleId);
        attach.setModuleType(moduleType);
        attach.setName(file.getOriginalFilename());
        attach.setPath(filePath);
        attach.setSize(file.getSize());
        attach.setCreateBy(ResourceUtil.getUserSession().getId());
        attach.setCreateTime(System.currentTimeMillis());
        ResultCode code = this.attachService.save(attach);
        switch (code) {
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j.setObj(attach);
                break;
        }
    }

    //下载
    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxJson del(@PathVariable String id) {
        ResultCode code = this.attachService.del(id);
        AjaxJson j = new AjaxJson();
        switch (code) {
            case INVALID:
                j = new AjaxJson("提交数据不合法", false, ResultCode.INVALID.getValue());
                break;
            default:
                j = new AjaxJson("操作成功", true, ResultCode.SUCCESS.getValue());
                break;
        }
        return j;
    }

    //获取
    @RequestMapping(value = "/list/{moduleId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson list(@PathVariable String moduleId) {
        Attach attach = new Attach();
        attach.setModuleId(moduleId);
        List<Attach> list = this.attachService.list(attach);
        AjaxJson j = new AjaxJson();
        j.setObj(list);
        j.setSuccess(true);
        return j;
    }


}
