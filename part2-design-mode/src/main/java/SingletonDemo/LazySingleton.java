package SingletonDemo;

/**
 * 懒汉式：在需要时创建
 * Created by llj on 2019/4/21.
 */
public class LazySingleton {
    public static LazySingleton singleton ;

    private LazySingleton(){
        System.out.println("初始化");
    }

    public static LazySingleton getInstance(){
        if (null==singleton) {
            System.out.println("创建实例");
            singleton = new LazySingleton();
        }
        return singleton;
    }

    public static void main(String[] args){
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        System.out.println(instance1==instance2);
    }
}
