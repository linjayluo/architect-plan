package FactoryModeDemo2_AbstractFactory;

/**
 * 工厂方法：测试
 * Created by llj on 2019/4/24.
 */
public class FactoryTest {
    public static void main(String[] args){

        CarFactory factory= new JiLiFactory();
        Engine engine = factory.createEngine();
        engine.run();
        engine.start();
        Chair chair = factory.createChair();
        chair.run();

    }
}
