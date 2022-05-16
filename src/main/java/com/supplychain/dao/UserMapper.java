package com.supplychain.dao;

import com.supplychain.entity.User;
import java.util.List;

public interface UserMapper {
    int insert(User user);

    List<User> selectAll();

    User selectByUserName(String userName);
}