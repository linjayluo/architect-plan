package ProxyMode2_JdkProxy;

import ProxyMode1_StaticProxy.IUserDao;
import ProxyMode1_StaticProxy.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK反射实现代理
 * Created by llj on 2019/4/24.
 */
public class InvocationHandlerImplProxy implements InvocationHandler {

    private Object target;// 业务实现类对象，用来调用具体的业务方法

    // 通过构造函数传入目标对象
    public InvocationHandlerImplProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("before invoke...");
        result = method.invoke(target,args);
        System.out.println("after invoke...");
        return result;
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //被代理对象
        IUserDao userDao = new UserDaoImpl();
        //创建代理类 -- 开始
        InvocationHandlerImplProxy invocationHandlerImpl = new InvocationHandlerImplProxy(userDao);
        ClassLoader loader = userDao.getClass().getClassLoader();
        Class<?>[] interfaces = userDao.getClass().getInterfaces();
        // 主要装载器、一组接口及调用处理动态代理实例
        IUserDao newProxyInstance = (IUserDao) Proxy.newProxyInstance(loader, interfaces, invocationHandlerImpl);
        //创建代理类 -- 结束

        newProxyInstance.save();
    }
}
