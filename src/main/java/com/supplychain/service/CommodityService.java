package com.supplychain.service;

import com.supplychain.entity.Commodity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public interface CommodityService {
    List<Commodity> selectAll();

    String insert(MultipartFile file, HttpSession session);

    List<Commodity> selectByUserName(String useName, HttpSession session);

    List<Commodity> selectByUserNameAndStoreName(String userName, String storeName, HttpSession session);

    List<Commodity> select(String userName, String storeName, String start, String end, Integer commodityId, String commodityCode, String commodityName, Integer pageNum, HttpSession session);

    List<Commodity> selectSum(String userName, String storeName, String start, String end, Integer commodityId, String commodityCode, String commodityName, Integer pageNum, HttpSession session);
}
