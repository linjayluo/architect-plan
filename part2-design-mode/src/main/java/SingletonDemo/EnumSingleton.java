package SingletonDemo;

/**
 * 枚举单例模式
 * Created by llj on 2019/4/21.
 */

enum Singleton{
    INSTANCE;

}

public class EnumSingleton {

    public static Singleton getInstance(){
        return Singleton.INSTANCE;
    }

    public static void main(String[] args){
        Singleton instance1 = EnumSingleton.getInstance();
        Singleton instance2 = EnumSingleton.getInstance();
        System.out.println(instance1==instance2);
    }

}
