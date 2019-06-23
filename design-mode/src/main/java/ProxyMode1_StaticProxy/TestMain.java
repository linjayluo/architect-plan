package ProxyMode1_StaticProxy;

/**
 * Created by llj on 2019/4/24.
 */
public class TestMain {
    public static void main(String[] args){

        UserDaoImpl userDao1 = new UserDaoImpl();
        IUserDao userDao = new UserDaoImplProxy(userDao1) ;
        userDao.save();
    }
}
