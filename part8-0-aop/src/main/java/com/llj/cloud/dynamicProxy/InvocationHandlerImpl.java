package com.llj.cloud.dynamicProxy;
import com.llj.cloud.UserService;
import com.llj.cloud.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * PackageName:   com.llj.cloud.dynamicProxy
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/23 23:54
 * Updater:     by luolinjie
 * Update_Date: 2019/9/23
 * Update_Description: luolinjie 补充
 **/
public class InvocationHandlerImpl implements InvocationHandler{
    private UserServiceImpl userServiceImpl;

    public InvocationHandlerImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before add()");
        Object invoke = method.invoke(userServiceImpl, args);
        System.out.println("after add()");
        return invoke;
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        InvocationHandlerImpl handler = new InvocationHandlerImpl(userService);
        ClassLoader loader = userService.getClass().getClassLoader();
        Class<?>[] interfaces = userService.getClass().getInterfaces();
        UserService newProxyInstance = (UserService) Proxy.newProxyInstance(loader, interfaces, (InvocationHandler) handler);
        newProxyInstance.add();
    }


}
