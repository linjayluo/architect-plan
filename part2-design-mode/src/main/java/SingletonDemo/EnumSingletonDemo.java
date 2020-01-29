package SingletonDemo;
/**
 * 使用枚举实现单例模式
 *      优点:实现简单、枚举本身就是单例，由jvm从根本上提供保障!  避免通过反射和反序列化的漏洞
 *      缺点:没有延迟加载
 * Created by llj on 2019/4/22.
 */

public class EnumSingletonDemo {
    static enum SingletonEnum{
        INSTANCE;
        private EnumSingletonDemo singleton;
        private SingletonEnum(){
            singleton = new EnumSingletonDemo();
        }

        public EnumSingletonDemo getInstance(){
            return singleton;
        }
    }
    public static void main(String[] args){
        EnumSingletonDemo instance1 = SingletonEnum.INSTANCE.getInstance();
        EnumSingletonDemo instance2 = SingletonEnum.INSTANCE.getInstance();
        System.out.println(instance1==instance2);
    }
}
