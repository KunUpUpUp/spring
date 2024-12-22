package com.seasugar.factory.support;

import com.seasugar.BeanException;
import com.seasugar.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        if (beanDefinitionMap.get(beanName) != null) {
            return beanDefinitionMap.get(beanName);
        } else {
            throw new BeanException("No bean named" + beanName + "is defined");
        }
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
