package com.ws.controller.wei.tc.common;


/**
 * 微信端界面名
 *
 * @author 许增飞
 */
public enum PageEnum {
    INDEX("index"),
    NOTFOUNT("404"),
    CALLNAME("callName");
    private String val;

    private PageEnum(String val) {
        this.val = val;
    }

    public static PageEnum findPage(String val) {

        for (PageEnum t : PageEnum.values()) {
            if (t.toString().equals(val)) {
                return t;
            }
        }
        return PageEnum.NOTFOUNT;
    }

    @Override
    public String toString() {
        return this.val;
    }

}
