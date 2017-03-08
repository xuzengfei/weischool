package com.bugframework.common.utility;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/8/22.
 */
public class BaseDownload {

    /**
     * 文件下载方法
     *
     * @param fileName 文件名称
     * @param path     路径
     * @param response HttpServletResponse对象
     */
    public void download(String fileName, String path, HttpServletResponse response) {
        try {

            // path是指欲下载的文件的路径。
            String path1 = PropertiesUtil.get("file_root_dir") + path;
            File file = new File(path1);

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path1));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

    }

    /**
     * 打包成zip下载
     *
     * @param zipName   下载的时候显示zip文件名称
     * @param fileName  文件名称
     * @param filePaths 文件路径
     * @param response  response
     */
    public void zipDownLoad(String zipName, String[] fileName, String[] filePaths, HttpServletResponse response) {
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(zipName.getBytes("UTF-8"), "ISO8859-1"));
            response.setContentType("application/octet-stream");
            ZipOutputStream ouputStream = new ZipOutputStream(response.getOutputStream());
            for (int i = 0; i < filePaths.length; i++) {
                this.zipFile(new File(PropertiesUtil.get("file_root_dir") + filePaths[i]), fileName[i], ouputStream);
            }
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zipFile(File file, String fileName, ZipOutputStream out) {
        byte[] buffer = new byte[1024];
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    FileInputStream fis = new FileInputStream(file);
                    out.putNextEntry(new ZipEntry(fileName));
                    int len;
                    //读入需要下载的文件的内容，打包到zip文件
                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.closeEntry();
                    fis.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
