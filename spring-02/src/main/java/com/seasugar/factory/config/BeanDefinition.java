package com.seasugar.factory.config;

public class BeanDefinition {
    private Class bean;

    public BeanDefinition(Class bean) {
        this.bean = bean;
    }

    public Class getBean() {
        return bean;
    }
}
