package SingletonDemo;

/**
 * 双重检测锁单例模式--不建议使用
 * 存在缺陷：可能存在因代码重排序导致的线程不安全问题
 * 解决方案：即使对 singleton 使用volatile修饰禁止重排序解决可见性问题，但不能保证原子性
 * Created by llj on 2019/4/22.
 */
public class DoubleCheckLockSingleton {
    private static DoubleCheckLockSingleton singleton;

    private DoubleCheckLockSingleton() {
    }

    public static DoubleCheckLockSingleton getInstance() {
        if (null == singleton) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (null == singleton) {
                    singleton = new DoubleCheckLockSingleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args){

        System.out.println(DoubleCheckLockSingleton.getInstance()==DoubleCheckLockSingleton.getInstance());
    }
}
