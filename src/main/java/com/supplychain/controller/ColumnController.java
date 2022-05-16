package com.supplychain.controller;

import com.supplychain.entity.Column;
import com.supplychain.service.ColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ColumnController {

    @Resource
    ColumnService columnService;

    @GetMapping("/getColumn")
    @ResponseBody
    public String getColumn(String tableName) {
        List<Column> columns = columnService.selectTableColumn(tableName);
        return null;
    }
}
