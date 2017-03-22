package com.bugframework.common.utility;


import com.bugframework.common.pojo.AjaxJson;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/7/27.
 */
public class BaseUpload {
    private static String THUMB_PREVFIX = "thumb";
    /**
     * 允许文件格式
     */
    private String allowSuffix;//允许文件格式
    /**
     * 允许文件大小
     */
    private long allowSize = 2L;//允许文件大小
    /**
     * 输出文件路径+名称
     */
    private String fileName;
    /**
     * 输出文件路径+名称，数组格式保存
     */
    private String[] fileNames;
    /**
     * 文件存放的文件夾
     */
    private String fileDir;
    /**
     * 重命名
     */
    private String fileNameNew;
    /**
     * 是否要生成缩略图
     */
    private boolean isThumb;
    /**
     * 缩略图宽
     */
    private int thumbW;
    /**
     * 缩略图长
     */
    private int thumbH;

    public String getAllowSuffix() {
        return allowSuffix;
    }

    public void setAllowSuffix(String allowSuffix) {
        this.allowSuffix = allowSuffix;
    }

    public long getAllowSize() {
        return allowSize * 1024 * 1024;
    }

    public void setAllowSize(long allowSize) {
        this.allowSize = allowSize;
    }

    public String getFileName() {
        return fileName == null ? "" : fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    
    public void setThumb(boolean isThumb) {
		this.isThumb = isThumb;
	}

	public void setThumbW(int thumbW) {
		this.thumbW = thumbW;
	}

	public void setThumbH(int thumbH) {
		this.thumbH = thumbH;
	}

	/**
     * 文件重新命名
     *
     * @return
     */
    public String getFileNameNew() {
        return IdUtil.uuid();
    }

    /**
     * 多文件上传
     *
     * @param files    传入文件集合
     * @param request  传入request对象
     * @param response 传入 response对象
     * @return 返回  Map<String, Object>; code =1 成功 -1 失败  ;msg 失败原因;data 返回文件名称
     */
    public void uploads(List<MultipartFile> files, HttpServletRequest request, HttpServletResponse response, AjaxJson j) {

        String basePath = PropertiesUtil.get("file_root_dir");
        StringBuffer msg = new StringBuffer();

        fileNames = new String[files.size()];
        int index = 0;
        Iterator<MultipartFile> l = files.iterator();
        while (l.hasNext()) {
            MultipartFile file = l.next();
            try {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                int length = getAllowSuffix().indexOf(suffix);
                if (length == -1 && !"*".equals(getAllowSuffix())) {
                    l.remove();
                    msg.append(" " + file.getOriginalFilename() + "上传格式不正确");
                    continue;
                }
                if (file.getSize() > getAllowSize()) {
                    l.remove();
                    msg.append(" " + file.getOriginalFilename() + "文件大小已经超出范围");
                    continue;
                }
                File destFile = new File(basePath + fileDir);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                fileNameNew = getFileNameNew() + "." + suffix;
                File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
                file.transferTo(f);
                f.createNewFile();
                if(this.isThumb){
                	 File thubFile = new File(basePath +THUMB_PREVFIX+File.separator+fileDir);
                     if (!thubFile.exists()) {
                    	 thubFile.mkdirs();
                     }
                	ImageUtil.thumbImage(f, this.thumbW, this.thumbH, thubFile.getAbsoluteFile() + File.separator + fileNameNew);
                }
                fileNames[index++] = fileDir +"/"+ fileNameNew;
                msg.append("上传成功");
            } catch (Exception e) {
                l.remove();
                msg.append(" " + file.getOriginalFilename() + "上传失败");
            }
        }
        j.setObj(fileNames);
        j.setSuccess(true);
        j.setMsg(msg.toString());

    }

    /**
     * 单文件上传
     *
     * @param file     传入文件
     * @param request  HttpServletRequest 对象
     * @param response HttpServletResponse对象
     * @return Map<String, Object>; code =1 成功 -1 失败  ;msg 失败原因;data 返回文件路径
     */
    public void upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response, AjaxJson j) {
        String separator = File.separator;
        //String basePath = System.getProperty("user.dir").replace("bin", "file") + separator;
        String basePath = PropertiesUtil.get("file_root_dir");
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            this.setFileName(file.getOriginalFilename());
            int length = getAllowSuffix().indexOf(suffix);
            if (length == -1 && !"*".equals(getAllowSuffix())) {
                j.setSuccess(false);
                j.setObj("");
                j.setMsg("请上传允许格式的文件");
                return;
            }
            if (file.getSize() > getAllowSize()) {
                j.setSuccess(false);
                j.setObj("");
                j.setMsg("您上传的文件大小已经超出范围");
                return;
            }

            File destFile = new File(basePath + fileDir);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            fileNameNew = getFileNameNew() + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
            file.transferTo(f);
            f.createNewFile();
            if(this.isThumb){
            	 File thubFile = new File(basePath +THUMB_PREVFIX+File.separator+fileDir);
                 if (!thubFile.exists()) {
                	 thubFile.mkdirs();
                 }
            	ImageUtil.thumbImage(f, this.thumbW, this.thumbH, thubFile.getAbsoluteFile() + File.separator + fileNameNew);
            }
            j.setSuccess(true);
            j.setObj(fileDir +"/"+ fileNameNew);
            this.fileName=fileDir+"/"+fileNameNew;
            j.setMsg("上传成功");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setSuccess(false);
            j.setObj("上传失败");
            j.setMsg("上传成功");
        }
    }

    /**
     * 删除
     *
     * @param path 路径
     */
    public void del(String path) {
        if (path != null && !"".equals(path)) {
            String basePath = PropertiesUtil.get("file_root_dir");
            String filePath = basePath +File.separator+ path;
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 批量删除
     *
     * @param paths 传入路径数组
     */
    public void batchDel(Object[] paths) {
        for (int i = 0; i < paths.length; i++) {
            if (paths[i] != null) {
                del(paths[i].toString());
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> l = list.iterator();
        while (l.hasNext()) {
            Integer v = l.next();
            if (v == 3) {
                l.remove();
            }
        }

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
