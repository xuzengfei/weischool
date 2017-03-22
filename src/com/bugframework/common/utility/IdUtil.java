package com.bugframework.common.utility;

import java.util.UUID;

/**
 * Created by admin on 2017/1/10.
 */
public class IdUtil{
    private IdUtil(){//防止被实例化

    }
    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid.toUpperCase();
    }
}
