package com.supplychain.controller;

import com.supplychain.entity.Store;
import com.supplychain.entity.User;
import com.supplychain.service.CommodityService;
import com.supplychain.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private StoreService storeService;

    @Resource
    private CommodityService commodityService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/top")
    public String top() {
        return "top";
    }
    @GetMapping("/bar")
    public String bar() {
        return "bar";
    }
    @GetMapping("/screen")
    public ModelAndView screen(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<Store> stores = storeService.selectByUserName(user.getUserName());
        mv.addObject("stores", stores);
        int pageCount = session.getAttribute("pageCount") == null ? 0 : (int) session.getAttribute("pageCount");
        if (pageCount == 0) {
            commodityService.selectByUserName(user.getUserName(), session);
        }
        mv.setViewName("screen");
        return mv;
    }

    @GetMapping("/taskfordeal")
    public String taskfordeal() {
        return "taskfordeal0";
    }

}
