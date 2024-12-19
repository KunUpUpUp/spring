package com.seasugar.factory.support;

import com.seasugar.BeanException;
import com.seasugar.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstarctBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean = null;
        try {
            bean = beanDefinition.getBean().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException("Instantiation of " + beanName + " failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
