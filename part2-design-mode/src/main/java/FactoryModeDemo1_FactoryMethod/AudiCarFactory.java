package FactoryModeDemo1_FactoryMethod;

/**
 * 工厂实现--奥迪工厂
 * Created by llj on 2019/4/24.
 */
public class AudiCarFactory implements CarFactory {
    public Car createCar() {
        return new AudiCar();
    }
}
