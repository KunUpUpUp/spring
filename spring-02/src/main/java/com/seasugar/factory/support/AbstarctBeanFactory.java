package com.seasugar.factory.support;

import com.seasugar.BeanException;
import com.seasugar.factory.config.BeanDefinition;

public abstract class AbstarctBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeanException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        // 创建Bean（单例）
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException;
}
