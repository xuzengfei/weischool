package com.ws.controller.manager.websocket;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 许增飞
 * @version 1.0
 * @from 2017-07-17 11:32
 */
@ServerEndpoint(value = "/echo")
public class PriceServer {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Callback hook for Connection open events. This method will be invoked
     * when a client requests for a WebSocket connection.
     *
     * @param session
     *            the session which is opened.
     */
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    /**
     * Callback hook for Connection close events. This method will be invoked
     * when a client closes a WebSocket connection.
     *
     * @param session
     *            the session which is opened.
     */
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a
     * client send a message.
     *
     * @param message
     *            The text message
     * @param session
     *            The session of the client
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message Received: " + message);
        for (Session remote : sessions) {
            System.out.println("Sending to " + remote.getId());
            remote.getAsyncRemote().sendText(message);
        }
    }
}

