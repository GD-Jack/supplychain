package com.supplychain.service.impl;

import com.supplychain.dao.LeftRowMapper;
import com.supplychain.entity.LeftRow;
import com.supplychain.service.LeftService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeftServiceImpl implements LeftService {

    @Resource
    private LeftRowMapper leftRowMapper;

    @Override
    public List<LeftRow> selectByTopId(int topId) {
        return leftRowMapper.selectByTopId(topId);
    }
}
