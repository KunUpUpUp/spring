package com.seasugar.mapper;


import com.seasugar.anno.MyService;
import com.seasugar.bean.User;

@MyService
public class UserMapper {
    public User getUser() {
        User user = new User();
        user.setName("zkp");
        return user;
    }
}
