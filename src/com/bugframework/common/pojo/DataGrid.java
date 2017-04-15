package com.bugframework.common.pojo;


import java.util.List;

/**
 * 存放分页数据的模型
 */
public class DataGrid<T> {

    /**
     * 当前页号
     */
    private int pageNo = 1;
    /**
     * 每页显示的记录条数
     */
    private int pageSize = 20;
    /**
     * 总记录数
     */
    private long recordCount;
    /**
     * 存放分页数据的集合
     */
    private List<T> datas;

    /**
     * 总页数根据pageSize和recordCount计算得出
     */
    private int pageCount;

    private List<String[]> order;

    private boolean paging = true;

    public DataGrid() {
    }

    public DataGrid(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        if (paging) {
            if (this.getRecordCount() <= 0) {
                return 0;
            } else {
                pageCount = (int) ((recordCount + pageSize - 1) / pageSize);
                return pageCount;
            }
        }
        return 0;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<String[]> getOrder() {
        return order;
    }

    public void setOrder(List<String[]> order) {
        this.order = order;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }


}