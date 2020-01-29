package ProxyMode_dynamic;
/**
 * PackageName:   FactoryMode_dynamic
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/7/30 21:07
 * Updater:     by luolinjie
 * Update_Date: 2019/7/30
 * Update_Description: luolinjie 补充
 **/
public class UserDaoImpl implements UserDao {

    public void save(String username) {
        System.out.println("new user "+username+" is added into DB!");
    }
}
