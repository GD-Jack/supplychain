package com.supplychain.service.impl;

import com.supplychain.dao.StoreMapper;
import com.supplychain.entity.Store;
import com.supplychain.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreMapper storeMapper;

    @Override
    public List<Store> selectByUserName(String userName) {
        return storeMapper.selectByUserName(userName);
    }
}
