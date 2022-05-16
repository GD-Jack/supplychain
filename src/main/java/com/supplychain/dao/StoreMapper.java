package com.supplychain.dao;

import com.supplychain.entity.Store;
import java.util.List;

public interface StoreMapper {

    List<Store> selectByUserName(String userName);

    Store selectByUserNameAndStoreName(String userName, String storeName);

    int insert(String storeName, String userName);
}