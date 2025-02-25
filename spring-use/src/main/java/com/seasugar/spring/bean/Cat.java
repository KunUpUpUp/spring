package com.seasugar.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class Cat {

    public Cat() {
        System.out.println("Cat have been created");
    }

    @PostConstruct
    private void init() {
        System.out.println("Init Method...");
    }

    // prototype时无效，需手动调用该方法，就算关闭IoC容器也没用
    @PreDestroy
    public void destory() {
        System.out.println("Destory Method...");
    }
}
