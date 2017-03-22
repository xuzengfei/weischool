package com.ws.controller.wei.common;


/**
 * 微信端界面名
 *
 * @author 许增飞
 */
public enum PageEnum {
    INDEX("index"),
    NOTFOUNT("404"),
    LOGIN("login"),
    CLASSQUER_CHOOSE("classQuery_choose"),
    CORDQUERY("cordQuery"),
    INFORM("inform"),
    BOOK("book"),
   CERTIFICATE("certificate"),
    SCHOOLLIFE("schoolLife"),
    ABOUT("about"),
    CLASSTABLE("classTable"),
    CHOOSECLASS("fee_chooseClass");
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
