package SingletonDemo;

/**
 * 　优势：兼顾了懒汉模式的内存优化（使用时才初始化）以及饿汉模式的安全性（不会被反射入侵）。
 　  劣势：需要两个类去做到这一点，虽然不会创建静态内部类的对象，但是其 Class 对象还是会被创建，而且是属于永久带的对象。
 * Created by llj on 2019/4/21.
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
    }

    private static class SingletonClassInstance {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
    // 方法没有同步
    public static StaticInnerClassSingleton getInstance() {
        return SingletonClassInstance.instance;
    }

    public static void main(String[] args) {
        StaticInnerClassSingleton s1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton s2 = StaticInnerClassSingleton.getInstance();
        System.out.println(s1 == s2);
    }
}
