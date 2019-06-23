package SingletonDemo;

/**
 * 饿汉式
 * Created by llj on 2019/4/21.
 */
public class HungrySingleton {
    public static final HungrySingleton singleton = new HungrySingleton();

    //构造方法要私有化，防止从外部创建该对象
    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        HungrySingleton instance1 = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();

        System.out.println(instance1 == instance2);
    }
}
