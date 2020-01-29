package com.llj.cloud.cglibProxy;
import com.llj.cloud.UserService;
import com.llj.cloud.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * PackageName:   com.llj.cloud.cglibProxy
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/24 0:08
 * Updater:     by luolinjie
 * Update_Date: 2019/9/24
 * Update_Description: luolinjie 补充
 **/
public class CglibProxy implements MethodInterceptor {

    private  Object targetObject;

    public Object getInstance( Object object){
        this.targetObject = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before involve... ");
        Object invoke = method.invoke(targetObject, args);
        System.out.println("after involve... ");
        return invoke;
    }

    public static void main(String[] args) {
        CglibProxy proxy  = new CglibProxy();
        UserService instance = (UserServiceImpl) proxy.getInstance(new UserServiceImpl());
        instance.add();
    }

}
