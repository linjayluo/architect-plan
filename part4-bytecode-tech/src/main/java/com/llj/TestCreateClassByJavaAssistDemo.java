package com.llj;

import javassist.*;

import java.io.IOException;

/**
 * Created by llj on 2019/6/23.
 */
public class TestCreateClassByJavaAssistDemo{

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass userClass = pool.makeClass("com.llj.User");
        CtField nameField = CtField.make("    private String name;", userClass);

        CtField ageField= CtField.make("    private int age;", userClass);

        userClass.addField(nameField);
        userClass.addField(ageField);

        CtMethod getNameMethod = CtMethod.make("public String getName() {return name;}", userClass);

        //5.添加方法
        userClass.addMethod(getNameMethod);

        //6.添加构造函数
        CtConstructor constructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), pool.get("int")}, userClass);

        constructor.setBody("{ this.name = name;this.age=age;}");

        userClass.addConstructor(constructor);

        userClass.writeFile();




    }
}
