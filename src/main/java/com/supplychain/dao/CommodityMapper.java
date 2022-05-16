package com.supplychain.dao;

import com.supplychain.entity.Commodity;

import java.sql.Date;
import java.util.List;

public interface CommodityMapper {

    int insert(Commodity record);

    List<Commodity> selectAll();

    List<Commodity> selectByUserName(String userName);

    List<Commodity> selectByUserNameAndStoreName(String userName, String storeName);

    List<Commodity> select(String userName, String storeName, String start, String end, Integer commodityId, String commodityCode, String commodityName);

    List<Commodity> selectSum(String userName, String storeName, String start, String end, Integer commodityId, String commodityCode, String commodityName, String limitDate);
}