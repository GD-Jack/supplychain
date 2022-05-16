package com.supplychain.entity;

public class LeftRow {
    private Integer leftRowId;

    private String leftRowData;

    private String topTitle;

    private String leftTitle;

    private String url;

    public Integer getLeftRowId() {
        return leftRowId;
    }

    public void setLeftRowId(Integer leftRowId) {
        this.leftRowId = leftRowId;
    }

    public String getLeftRowData() {
        return leftRowData;
    }

    public void setLeftRowData(String leftRowData) {
        this.leftRowData = leftRowData == null ? null : leftRowData.trim();
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}