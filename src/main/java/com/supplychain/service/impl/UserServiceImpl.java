package com.supplychain.service.impl;

import com.supplychain.dao.UserMapper;
import com.supplychain.entity.User;
import com.supplychain.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int signup(String userName, String password, String name) {
        if (userMapper.selectByUserName(userName) != null) {
            return 0;
        } else {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setName(name);
            user.setAdmin(false);
            userMapper.insert(user);
            return 1;
        }
    }

    @Override
    public int login(String userName, String password) {
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                if (user.isAdmin()) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }
}
