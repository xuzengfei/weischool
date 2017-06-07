package com.ws.util;

/**
 * @author 许增飞
 * @description 全局
 * @datetime 2017/6/7 17:04.
 */
public class Global {
    private volatile static String ACCESS_TOKEN;
    private volatile static Long ACCESS_TOKEN_EXPIRES=0L;
    private volatile static String JSAPI_TICKET;
    private volatile static Long JSAPI_TICKET_EXPIRES=0L;

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public static Long getAccessTokenExpires() {
        return ACCESS_TOKEN_EXPIRES;
    }

    public static void setAccessTokenExpires(Long accessTokenExpires) {
        ACCESS_TOKEN_EXPIRES = accessTokenExpires;
    }

    public static String getJsapiTicket() {
        return JSAPI_TICKET;
    }

    public static void setJsapiTicket(String jsapiTicket) {
        JSAPI_TICKET = jsapiTicket;
    }

    public static Long getJsapiTicketExpires() {
        return JSAPI_TICKET_EXPIRES;
    }

    public static void setJsapiTicketExpires(Long jsapiTicketExpires) {
        JSAPI_TICKET_EXPIRES = jsapiTicketExpires;
    }
}
