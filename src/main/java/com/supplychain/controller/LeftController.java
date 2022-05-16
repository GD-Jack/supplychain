package com.supplychain.controller;

import com.supplychain.entity.LeftRow;
import com.supplychain.service.LeftService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LeftController {

    @Resource
    private LeftService leftService;

    @GetMapping("/left")
    public ModelAndView show(int type) {
        ModelAndView mv = new ModelAndView();
        List<LeftRow> leftRows = leftService.selectByTopId(type);
        List<LeftRow> leftRows1 = new ArrayList<>();
        List<LeftRow> leftRows2 = new ArrayList<>();
        String leftTitle1 = leftRows.get(0).getLeftTitle();
        for (LeftRow leftRow : leftRows) {
            if (leftRow.getLeftTitle().equals(leftTitle1)) {
                leftRows1.add(leftRow);
            } else {
                leftRows2.add(leftRow);
            }
        }
        mv.addObject("lefts1", leftRows1);
        mv.addObject("lefts2", leftRows2);
        mv.setViewName("left");
        return mv;
    }
}
