package com.llj;

/**
 * Created by llj on 2019/6/27.
 */
public class ClassLoadOrderTest {

    public int b = 1;

    private static final double a;

    private int c;

    public ClassLoadOrderTest(int c) {
        this.c = c;
        System.out.println("构造方法初始化：c=" +c);
    }

    static {
        a = 0.2;
        System.out.println("静态代码块初始化：a=" + a);
    }

    {
        b = 5;
        System.out.println("非静态代码块初始化：a=" + b);
    }

    public static void main(String[] args) {

        ClassLoadOrderTest classLoadOrderTest = new ClassLoadOrderTest(3);
    }
}
