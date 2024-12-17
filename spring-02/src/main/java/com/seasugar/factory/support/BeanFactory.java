package com.seasugar.factory.support;

import com.seasugar.BeanException;

public interface BeanFactory {
    Object getBean(String name) throws BeanException;
}
