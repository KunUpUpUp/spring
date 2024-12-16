package com.seasugar.factory;

import com.seasugar.BeanException;

public interface BeanFactory {
    Object getBean(String name) throws BeanException;
}
