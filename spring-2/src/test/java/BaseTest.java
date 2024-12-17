import org.junit.Test;

public class BaseTest {
    @Test
    public void testCase() {
        int a = 30;
        switch (a) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                throw new RuntimeException("不符合范围");
        }
    }
}
