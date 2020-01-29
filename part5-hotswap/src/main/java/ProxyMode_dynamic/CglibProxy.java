package ProxyMode_dynamic;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * PackageName:   FactoryMode_dynamic
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/7/31 21:28
 * Updater:     by luolinjie
 * Update_Date: 2019/7/31
 * Update_Description: luolinjie 补充
 **/
public class CglibProxy implements MethodInterceptor {

    private Object targetProxy;
    Object getInstance(Object targetObj){
        this.targetProxy = targetObj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetProxy.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before invoke");
        Object invoke = method.invoke(targetProxy,args);
        System.out.println("after invoke");
        return invoke;
    }


    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        UserDao instance = (UserDao) proxy.getInstance(new UserDaoImpl());
        instance.save("12455");

    }

}
