package com.seasugar.service;


import com.seasugar.anno.DI;
import com.seasugar.anno.MyService;
import com.seasugar.bean.User;
import com.seasugar.mapper.UserMapper;

@MyService
public class UserServiceImpl implements UserService{
    @DI
    private UserMapper userMapper;

    @Override
    public void useMapper() {
        User user = userMapper.getUser();
        System.out.println(user.getName());
    }
}
