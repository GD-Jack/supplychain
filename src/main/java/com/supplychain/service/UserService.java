package com.supplychain.service;

import com.supplychain.entity.User;

public interface UserService {
    int signup(String userName, String password, String name);

    int login(String userName, String password);

    User selectByUserName(String userName);
}
