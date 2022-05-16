package com.supplychain.dao;

import com.supplychain.entity.Column;
import com.supplychain.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnMapper {

    List<Column> selectAll();

    List<Column> selectTableColumn(String tableName);

    Column selectColumn(String tableName, String columnName);

    int importData(Commodity commodity);
}