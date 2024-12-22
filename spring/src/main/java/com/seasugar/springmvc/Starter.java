package com.seasugar.springmvc;

import com.seasugar.springmvc.config.WebConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class Starter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
    }
}
