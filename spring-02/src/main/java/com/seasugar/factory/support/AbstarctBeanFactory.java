package com.seasugar.factory;

import com.seasugar.BeanException;

public abstract class AbstarctBeanFactory implements BeanFactory{
    @Override
    public Object getBean(String name) throws BeanException {
        return null;
    }


}
