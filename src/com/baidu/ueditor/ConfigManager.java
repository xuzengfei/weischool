package com.baidu.ueditor;

import com.baidu.ueditor.define.ActionMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置管理器
 *
 * @author hancong03@baidu.com
 */
public final class ConfigManager {

    private final String rootPath;
    private final String originalPath;
    private final String contextPath;
    private static final String configFileName = "config.json";
    private String parentPath = null;
    private JSONObject jsonConfig = null;
    // 涂鸦上传filename定义
    private final static String SCRAWL_FILE_NAME = "scrawl";
    // 远程图片抓取filename定义
    private final static String REMOTE_FILE_NAME = "remote";

    /*
     * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
     */
    private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {

        rootPath = rootPath.replace("\\", "/");

        this.rootPath = rootPath;
        this.contextPath = contextPath;

        if (contextPath.length() > 0) {
            this.originalPath = this.rootPath + uri.substring(contextPath.length());
        } else {
            this.originalPath = this.rootPath + uri;
        }

        this.initEnv();

    }

    /**
     * 配置管理器构造工厂
     *
     * @param rootPath    服务器根路径
     * @param contextPath 服务器所在项目路径
     * @param uri         当前访问的uri
     * @return 配置管理器实例或者null
     */
    public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {

        try {
            return new ConfigManager(rootPath, contextPath, uri);
        } catch (Exception e) {
            return null;
        }

    }

    // 验证配置文件加载是否正确
    public boolean valid() {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig() {

        return this.jsonConfig;

    }
    //TODO 添加虚拟路径
    public Map<String, Object> getConfig(int type) {

        Map<String, Object> conf = new HashMap<String, Object>();
        String savePath = null;
        boolean virtualPath = false;    //是否使用虚拟路径映射 默认不使用
        switch (type) {

            case ActionMap.UPLOAD_FILE:
                conf.put("isBase64", "false");
                conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
                conf.put("allowFiles", this.getArray("fileAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
                savePath = this.jsonConfig.getString("filePathFormat");
                //for virtual path mapping
                String filePathFormat = this.jsonConfig.getString("filePathFormat");
                String fileUsingVirtualPath = this.jsonConfig.getString("fileUsingVirtualPath");
                if ("yes".equalsIgnoreCase(fileUsingVirtualPath)) {
                    String fileRealMappingPath = this.jsonConfig.getString("fileRealMappingPath");
                    savePath = fileRealMappingPath + filePathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", fileRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

            case ActionMap.UPLOAD_IMAGE:
                conf.put("isBase64", "false");
                conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
                conf.put("allowFiles", this.getArray("imageAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
                savePath = this.jsonConfig.getString("imagePathFormat");
                //for virtual path mapping
                String imagePathFormat = this.jsonConfig.getString("imagePathFormat");
                String imageUsingVirtualPath = this.jsonConfig.getString("imageUsingVirtualPath");
                if ("yes".equalsIgnoreCase(imageUsingVirtualPath)) {
                    String imageRealMappingPath = this.jsonConfig.getString("imageRealMappingPath");
                    savePath = imageRealMappingPath + imagePathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", imageRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

            case ActionMap.UPLOAD_VIDEO:
                conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
                conf.put("allowFiles", this.getArray("videoAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
                savePath = this.jsonConfig.getString("videoPathFormat");
                //for virtual path mapping
                String videoPathFormat = this.jsonConfig.getString("videoPathFormat");
                String videoUsingVirtualPath = this.jsonConfig.getString("videoUsingVirtualPath");
                if ("yes".equalsIgnoreCase(videoUsingVirtualPath)) {
                    String videoRealMappingPath = this.jsonConfig.getString("videoRealMappingPath");
                    savePath = videoRealMappingPath + videoPathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", videoRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

            case ActionMap.UPLOAD_SCRAWL:
                conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
                conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
                conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
                conf.put("isBase64", "true");
                savePath = this.jsonConfig.getString("scrawlPathFormat");
                //for virtual path mapping
                String scrawlPathFormat = this.jsonConfig.getString("scrawlPathFormat");
                String scrawlUsingVirtualPath = this.jsonConfig.getString("scrawlUsingVirtualPath");
                if ("yes".equalsIgnoreCase(scrawlUsingVirtualPath)) {
                    String scrawlRealMappingPath = this.jsonConfig.getString("scrawlRealMappingPath");
                    savePath = scrawlRealMappingPath + scrawlPathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", scrawlRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

            case ActionMap.CATCH_IMAGE:
                conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
                conf.put("filter", this.getArray("catcherLocalDomain"));
                conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
                conf.put("allowFiles", this.getArray("catcherAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
                savePath = this.jsonConfig.getString("catcherPathFormat");
                //for virtual path mapping
                String catcherPathFormat = this.jsonConfig.getString("catcherPathFormat");
                String catcherUsingVirtualPath = this.jsonConfig.getString("catcherUsingVirtualPath");
                if ("yes".equalsIgnoreCase(catcherUsingVirtualPath)) {
                    String catcherRealMappingPath = this.jsonConfig.getString("catcherRealMappingPath");
                    savePath = catcherRealMappingPath + catcherPathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", catcherRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

            case ActionMap.LIST_IMAGE:
                conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
                conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
                //for virtual path mapping
                String imageManagerPathFormat = this.jsonConfig.getString("imageManagerListPath");
                String imageManagerUsingVirtualPath = this.jsonConfig.getString("imageManagerUsingVirtualPath");
                if ("yes".equalsIgnoreCase(imageManagerUsingVirtualPath)) {
                    String imageManagerRealMappingPath = this.jsonConfig.getString("imageManagerRealMappingPath");
                    savePath = imageManagerRealMappingPath + imageManagerPathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", imageManagerRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

            case ActionMap.LIST_FILE:
                conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
                conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
                //for virtual path mapping
                String fileManagerPathFormat = this.jsonConfig.getString("fileManagerListPath");
                String fileManagerUsingVirtualPath = this.jsonConfig.getString("fileManagerUsingVirtualPath");
                if ("yes".equalsIgnoreCase(fileManagerUsingVirtualPath)) {
                    String fileManagerRealMappingPath = this.jsonConfig.getString("fileManagerRealMappingPath");
                    savePath = fileManagerRealMappingPath + fileManagerPathFormat;
                    virtualPath = true;
                    conf.put("realMappingPath", fileManagerRealMappingPath);//put into conf map using key=realMappingPath
                }
                break;

        }

        conf.put("savePath", savePath);
        conf.put("rootPath", this.rootPath);
        conf.put("virtualPath", virtualPath);//add put
        return conf;

    }

    private void initEnv() throws FileNotFoundException, IOException {

        File file = new File(this.originalPath);

        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }

        this.parentPath = file.getParent();

        String configContent = this.readFile(this.getConfigPath());

        try {
            JSONObject jsonConfig = new JSONObject(configContent);
            this.jsonConfig = jsonConfig;
        } catch (Exception e) {
            this.jsonConfig = null;
        }

    }

    private String getConfigPath() {
        return this.parentPath + File.separator + ConfigManager.configFileName;
    }

    private String[] getArray(String key) {

        JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
        String[] result = new String[jsonArray.length()];

        for (int i = 0, len = jsonArray.length(); i < len; i++) {
            result[i] = jsonArray.getString(i);
        }

        return result;

    }

    private String readFile(String path) throws IOException {

        StringBuilder builder = new StringBuilder();

        try {

            InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);

            String tmpContent = null;

            while ((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();

        } catch (UnsupportedEncodingException e) {
            // 忽略
        }

        return this.filter(builder.toString());

    }

    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
    private String filter(String input) {

        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

    }

}
