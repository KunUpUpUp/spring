package com.seasugar;

import com.seasugar.bean.Cat;
import com.seasugar.config.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cat.class);
        System.out.println("Ioc have been created");
        Cat cat = context.getBean(Cat.class);
        context.close();
        cat.destory();
    }
}