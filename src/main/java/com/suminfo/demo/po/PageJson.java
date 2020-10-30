package com.suminfo.demo.po;



import java.util.List;

public class PageJson {
    private Integer records = 0;
    private Integer total = 0;
    private int page = 1;
    private List<?> rows = null;


    public PageJson() {
    }

    public PageJson(List<?> rows, Integer records, Integer total) {
        this.rows = rows;
        this.records = records;
        this.total = total;
    }

    public PageJson(List<?> rows, Integer records) {
        this.rows = rows;
        this.records = records;
    }



    public Integer getRecords() {
        return this.records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<?> getRows() {
        return this.rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public Integer getTotal() {
        return this.total;
    }
}
