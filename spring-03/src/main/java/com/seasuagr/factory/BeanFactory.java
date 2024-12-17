package com.seasuagr.factory;

import com.seasuagr.BeanException;

public interface BeanFactory {
    Object getBean(String name) throws BeanException;
}
