package com.supplychain.service;

import com.supplychain.entity.Store;

import java.util.List;

public interface StoreService {
    List<Store> selectByUserName(String userName);
}
