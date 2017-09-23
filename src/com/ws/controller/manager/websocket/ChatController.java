package com.ws.controller.manager.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 许增飞
 * @version 1.0
 * @from 2017-07-17 13:39
 */
@Controller
@RequestMapping("web/manager/chat")
public class ChatController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(){
        return "chat";
    }
}
