package ProxyMode1_StaticProxy;

/**
 * 代理类
 * Created by llj on 2019/4/24.
 */
public class UserDaoImplProxy implements IUserDao {

    IUserDao userDao;

    UserDaoImplProxy(IUserDao userDao){
        this.userDao = userDao;
    }

    public void save() {
        System.out.println("before save....");
        userDao.save();
        System.out.println("after save....");
    }
}
