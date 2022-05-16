package com.supplychain.dao;

import com.supplychain.entity.LeftRow;
import java.util.List;

public interface LeftRowMapper {


    List<LeftRow> selectByTopId(int topId);


}