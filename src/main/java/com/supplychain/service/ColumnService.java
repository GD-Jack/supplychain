package com.supplychain.service;

import com.supplychain.entity.Column;

import java.util.List;

public interface ColumnService {
    List<Column> selectTableColumn(String tableName);
}
