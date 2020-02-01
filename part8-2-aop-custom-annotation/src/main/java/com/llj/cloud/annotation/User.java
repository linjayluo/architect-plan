package com.llj.cloud.annotation;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * PackageName:   com.llj.cloud.annotation
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2020/2/1 15:38
 * Updater:     by luolinjie
 * Update_Date: 2020/2/1
 * Update_Description: luolinjie 补充
 **/
public class User implements Serializable {
    private String name;
    private int age;
    public void add() {

    }


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.llj.cloud.annotation.User");
        Method[] declaredMethods = forName.getDeclaredMethods();
        for (Method method : declaredMethods) {
            ExtAnnotation addAnnotation = method.getDeclaredAnnotation(ExtAnnotation.class);
            if (addAnnotation == null) {
                System.out.println("方法 "+method.getName()+" 未找到@AddAnnotation注解");
                continue;
            }

//            System.out.println("userId:" + addAnnotation.userId());
//            System.out.println("userName:" + addAnnotation.userName());
//            System.out.println("userId:" + addAnnotation.arrays());
        }

    }
}

