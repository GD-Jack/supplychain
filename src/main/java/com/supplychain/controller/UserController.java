package com.supplychain.controller;

import com.supplychain.entity.User;
import com.supplychain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String login(String userName, String password, HttpSession session) {
        int result = userService.login(userName, password);
        User user = userService.selectByUserName(userName);
        if (result == 1) {
            session.setAttribute("user", user);
            return "adminIndex";
        } else if (result == 2) {
            session.setAttribute("user", user);
            return "index";
        } else {
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "login";
    }

    @PostMapping("signup")
    public String signup(String userName, String password, String name, Model model) {
        int result = userService.signup(userName, password, name);
        if (result != 0) {
            model.addAttribute("msg", "注册成功");
            return "login";
        } else {
            model.addAttribute("msg", "注册失败，用户名已被注册");
            return "signup";
        }
    }
}
