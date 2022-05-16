package com.supplychain.service.impl;

import com.supplychain.dao.ColumnMapper;
import com.supplychain.entity.Column;
import com.supplychain.service.ColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnMapper columnMapper;

    @Override
    public List<Column> selectTableColumn(String tableName) {
        return columnMapper.selectTableColumn(tableName);
    }
}
