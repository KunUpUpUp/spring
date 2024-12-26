import bean.UserSerivce;
import com.seasugar.factory.config.BeanDefinition;
import com.seasugar.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserSerivce.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserSerivce userService = (UserSerivce) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserSerivce userService_singleton = (UserSerivce) beanFactory.getSingleton("userService");
        userService_singleton.queryUserInfo();
    }
}
