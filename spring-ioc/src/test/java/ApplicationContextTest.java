import com.seasugar.bean.AnnotationApplicationContext;
import com.seasugar.bean.ApplicationContext;
import com.seasugar.service.UserService;
import org.junit.jupiter.api.Test;

public class ApplicationContextTest {
    @Test
    public void testIoc() {
        ApplicationContext context = new AnnotationApplicationContext("com.seasugar");
        UserService userService = (UserService) context.getBean(UserService.class);
        userService.useMapper();
    }
}
