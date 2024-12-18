package cn.bugstack.springframework.test;

import cn.bugstack.springframework.BeanDefinition;
import cn.bugstack.springframework.BeanFactory;
import cn.bugstack.springframework.test.bean.UserService;
import cn.bugstack.springframework.test.bean.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * https://github.com/DerekYRC/mini-spring
 * https://github.com/code4craft/tiny-spring
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void testClass() {
        if (getClass() == ApiTest.class) {
            System.out.println("相同的Class");
        } else {
            System.out.println("不同的Class");
        }


        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.getClass());
    }

}
