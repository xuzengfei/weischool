package com.ws.service.consumer.dao;

import com.bugframework.common.dao.impl.BaseDaoImpl;
import com.ws.pojo.consumer.Consumer;
import org.springframework.stereotype.Repository;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/14.
 */
@Repository(value = "consDao")
public class ConsDaoImpl extends BaseDaoImpl<Consumer> implements ConsDao {
}
