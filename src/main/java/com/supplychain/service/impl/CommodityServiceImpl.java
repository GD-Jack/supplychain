package com.supplychain.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supplychain.dao.CommodityMapper;
import com.supplychain.dao.StoreMapper;
import com.supplychain.entity.Commodity;
import com.supplychain.entity.User;
import com.supplychain.service.CommodityService;
import com.supplychain.util.ReadExcel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private StoreMapper storeMapper;

    @Override
    public List<Commodity> selectAll() {
        List<Commodity> commodities = commodityMapper.selectAll();
        return commodities;
    }

    @Override
    public List<Commodity> selectByUserName(String useName, HttpSession session) {
        if (useName != null) {
            Page page = PageHelper.startPage(1, 20);
            List<Commodity> commodities = commodityMapper.selectByUserName(useName);
            session.setAttribute("pageCount", page.getPages());
            return commodities;
        } else {
            return null;
        }
    }

    @Override
    public List<Commodity> selectByUserNameAndStoreName(String userName, String storeName, HttpSession session) {
        if (storeName != null) {
            Page page = PageHelper.startPage(1, 20);
            List<Commodity> commodities = commodityMapper.selectByUserNameAndStoreName(userName, storeName);
            session.setAttribute("pageCount", page.getPages());
            System.out.println(page.getPages());
            return commodities;
        } else {
            return selectByUserName(userName, session);
        }
    }


    @Override
    public List<Commodity> select(String userName, String storeName, String start, String end, Integer commodityId, String commodityCode, String commodityName, Integer pageNum, HttpSession session) {
        Page page = PageHelper.startPage(pageNum, 20);
        List<Commodity> commodities = commodityMapper.select(userName, storeName, start, end, commodityId, commodityCode, commodityName);
        session.setAttribute("pageCount", page.getPages());
        return commodities;
    }

    @Override
    public List<Commodity> selectSum(String userName, String storeName, String start, String end, Integer commodityId, String commodityCode, String commodityName, Integer pageNum, HttpSession session) {
        String limitDate = "";
        if (start.equals("") && end.equals("")) {
            limitDate = "销售至今";
        } else if (start.equals("") && !end.equals("")){
            limitDate = "截至" + end;
        } else if (!start.equals("") && end.equals("")){
            limitDate = start + "至今";
        } else if (!start.equals("") && !end.equals("")){
            limitDate = start + "至" + end;
        }
        Page page = PageHelper.startPage(pageNum, 20);
        List<Commodity> commodities = commodityMapper.selectSum(userName, storeName, start, end, commodityId, commodityCode, commodityName, limitDate);
        session.setAttribute("pageCount", page.getPages());
        return commodities;
    }

    @Override
    public String insert(MultipartFile file, HttpSession session) {
        //创建处理EXCEL的类   utils里面的
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Commodity> commodityList = null;
        int insertResult = 0;
        String insertMsg = "";
        try {
            commodityList = readExcel.getExcelInfo(file);
            //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
            //和你具体业务有关,这里不做具体的示范
            //数据库插入
            if (null == commodityList) {
                insertMsg = "文件名不是excel格式或者excel表格为空,插入失败";
                session.setAttribute("insertMsg", insertMsg);
                return insertMsg;
            }
            User user = (User) session.getAttribute("user");
            for (Commodity commodity : commodityList) {
                commodity.setUserName(user.getUserName());
                System.out.println(commodity);
                if (storeMapper.selectByUserNameAndStoreName(commodity.getUserName(), commodity.getStoreName()) == null) {
                    storeMapper.insert(commodity.getStoreName(), commodity.getUserName());
                }
                int m = commodityMapper.insert(commodity);
                insertResult = insertResult + m;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            System.err.println("接受excel表格中的数据失败！！！");
        }
        if (insertResult == commodityList.size()) {
            insertMsg = "导入成功";
            session.setAttribute("insertMsg", insertMsg);
        } else {
            insertMsg = "导入失败";
            session.setAttribute("insertMsg", insertMsg);
        }
        return insertMsg;
    }
}
