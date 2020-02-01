import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.llj.cloud.service.UserService;

/**
 * PackageName:   PACKAGE_NAME
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2020/1/31 17:06
 * Updater:     by luolinjie
 * Update_Date: 2020/1/31
 * Update_Description: luolinjie 补充
 **/
public class TestMainApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
