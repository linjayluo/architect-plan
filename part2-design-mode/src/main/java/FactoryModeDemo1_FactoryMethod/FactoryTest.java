package FactoryModeDemo1_FactoryMethod;

/**
 * 工厂方法：测试
 * Created by llj on 2019/4/24.
 */
public class FactoryTest {
    public static void main(String[] args){
        CarFactory factory1 = new AudiCarFactory();
        Car car = factory1.createCar();
        car.run();

        CarFactory factory2 = new JiliCarFactory();
        Car car2 = factory2.createCar();
        car2.run();
    }
}
