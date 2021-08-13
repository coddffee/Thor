package com.thor.type;

public class PageData {

    /**
     * page capacity
     */
    private Integer size = 10;
    /**
     * total count of records
     */
    private Integer count = 0;
    /**
     * page count
     */
    private Integer pages = 0;

    public PageData() {
        super();
    }

    public PageData(Integer size, Integer count, Integer pages) {
        this.size = size;
        this.count = count;
        this.pages = pages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
