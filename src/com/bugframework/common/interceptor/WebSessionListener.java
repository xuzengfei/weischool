package com.bugframework.common.interceptor;

import com.bugframework.common.utility.ResourceUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author HanQichao
 * @version v1.0
 * @since 2016/10/20
 */
public class WebSessionListener implements HttpSessionListener {
    /**
     * Notification that a session was created.
     *
     * @param se the notification event
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    /**
     * Notification that a session is about to be invalidated.
     *
     * @param se the notification event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (ResourceUtil.getSession() != null) {
            ResourceUtil.removeSession();
        }
    }
}
