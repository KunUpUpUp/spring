package com.seasugar.config;

import com.seasugar.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destory")
    public Cat getCat() {
        return new Cat();
    }
}
