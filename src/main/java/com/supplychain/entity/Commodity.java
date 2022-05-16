package com.supplychain.entity;

public class Commodity {
    private Integer id;

    private Integer commodityId;

    private String commodityName;

    private String commodityCode;

    private Integer salesVolumes;

    private Float price;

    private Float cost;

    private String unit;

    private String userName;

    private String date;

    private String storeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    public Integer getSalesVolumes() {
        return salesVolumes;
    }

    public void setSalesVolumes(Integer salesVolumes) {
        this.salesVolumes = salesVolumes;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", salesVolumes=" + salesVolumes +
                ", price=" + price +
                ", cost=" + cost +
                ", unit='" + unit + '\'' +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}