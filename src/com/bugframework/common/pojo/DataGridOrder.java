package com.bugframework.common.pojo;

import org.hibernate.criterion.Order;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/12.
 */
public class DataGridOrder  extends Order{
    public DataGridOrder(String propertyName, boolean ascending) {
        super(propertyName, ascending);
    }
}
