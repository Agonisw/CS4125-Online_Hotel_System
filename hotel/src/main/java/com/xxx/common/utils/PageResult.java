package com.xxx.common.utils;
import java.util.List;


public class PageResult<T> {

    private Long pageNum;

    private Long pageSize;

    private Long totalSize;

    private Long totalPages;

    private List<T> rows;

    private String baseImageUrl;

    public Long getPageNum() {
        return pageNum;
    }
    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }
    public Long getPageSize() {
        return pageSize;
    }
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
    public Long getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
    public Long getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }
}