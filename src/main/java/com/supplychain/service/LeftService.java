package com.supplychain.service;

import com.supplychain.entity.LeftRow;

import java.util.List;

public interface LeftService {
    List<LeftRow> selectByTopId(int topId);
}
