package com.supplychain.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.supplychain.entity.Column;
import com.supplychain.entity.Commodity;
import com.supplychain.entity.User;
import com.supplychain.service.ColumnService;
import com.supplychain.service.CommodityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @Resource
    private ColumnService columnService;

    @GetMapping("/getCommodity")
    public ModelAndView getCommodity(String tableName) {
        ModelAndView mv = new ModelAndView();
        List<Commodity> commodities = commodityService.selectAll();
        List<Column> columns = columnService.selectTableColumn(tableName);
        mv.addObject("commodities", commodities);
        mv.addObject("columns", columns);
        mv.setViewName("taskfordeal");
        return mv;
    }

    @GetMapping("/getUserCommodity")
    public ModelAndView getUserCommodity(String tableName, HttpSession session){
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<Commodity> commodities = commodityService.selectByUserName(user.getUserName(), session);
        List<Column> columns = columnService.selectTableColumn(tableName);
        mv.addObject("commodities", commodities);
        mv.addObject("columns", columns);
        mv.setViewName("taskfordeal");
        return mv;
    }

    @GetMapping("/commodity")
    public ModelAndView commodity(String tableName, String storeName, String startdate, String enddate, String commodityId, String commodityCode, String commodityName, String pageNum, String flag, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<Commodity> commodities = null;
        try {
            if (flag != null && flag.equals("2")) {
                commodities = commodityService.selectSum(user.getUserName(), storeName, startdate, enddate,
                        commodityId.trim().equals("")?null:Integer.valueOf(commodityId.trim()), commodityCode.trim(), commodityName.trim(), Integer.valueOf(pageNum), session);
            } else {
                commodities = commodityService.select(user.getUserName(), storeName, startdate, enddate,
                        commodityId.trim().equals("")?null:Integer.valueOf(commodityId.trim()), commodityCode.trim(), commodityName.trim(), Integer.valueOf(pageNum), session);
            }
        } catch (NumberFormatException e) {
            if (flag != null && flag.equals("2")) {
                commodities = commodityService.selectSum(user.getUserName(), storeName, startdate, enddate,
                        000000001, commodityCode.trim(), commodityName.trim(), 1, session);
            } else {
                commodities = commodityService.select(user.getUserName(), storeName, startdate, enddate,
                        000000001, commodityCode.trim(), commodityName.trim(), 1, session);
            }
        }
        List<Column> columns = columnService.selectTableColumn(tableName);
        mv.addObject("commodities", commodities);
        mv.addObject("columns", columns);
        mv.setViewName("taskfordeal");
        return mv;
    }

    @GetMapping("/getPageCount")
    public void getPageCount(HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter pw  = response.getWriter();
        int pageCount = (int) session.getAttribute("pageCount");
        pw.println(pageCount);
        pw.flush();
        pw.close();
    }

    @RequestMapping("/toImportData")
    public String toImportData(){
        return "importData";
    }


    @RequestMapping(value = "/importData",method = {RequestMethod.POST})
    public String importData(@RequestParam(value = "file") MultipartFile file, String date, HttpSession session){
        String readResult = null;
        try {
            readResult = commodityService.insert(file, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "importDataResult";
    }
}
