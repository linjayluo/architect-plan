package ProxyMode3_cGlibProxy;
import ProxyMode1_StaticProxy.IUserDao;
import ProxyMode1_StaticProxy.UserDaoImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 什么是CGLIB动态代理
 * 使用cglib[Code Generation Library]实现动态代理，并不要求委托类必须实现接口，底层采用asm字节码生成框架生成代理类的字节码
 * Created by llj on 2019/4/24.
 */
public class CglibProxy implements MethodInterceptor {

    private Object targetObject;

    // 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
    public Object getInstance(Object target) {
        // 设置需要创建子类的类
        this.targetObject = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before invoke......");
        Object result = method.invoke(targetObject, args);
        System.out.println("after invoke......");
        return result;
    }

    public static void main(String[] args){
        CglibProxy cglibProxy = new CglibProxy();
        IUserDao userDao = (UserDaoImpl) cglibProxy.getInstance(new UserDaoImpl());
        userDao.save();
    }
}
