package com.seasugar;

import com.seasugar.boot.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class Test {

    @Autowired
    private UserServiceImpl userService;

    @org.junit.jupiter.api.Test
    public void testService() {
        userService.testService();
    }

    @BeforeEach
    public void addTest() {
        System.out.println("我在你们之前运行");
    }

    @org.junit.jupiter.api.Test
}