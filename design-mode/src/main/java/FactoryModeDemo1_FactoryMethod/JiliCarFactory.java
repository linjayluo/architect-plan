package FactoryModeDemo1_FactoryMethod;

/**
 * 具体实现：吉利工厂
 * Created by llj on 2019/4/24.
 */
public class JiliCarFactory implements CarFactory {
    public Car createCar() {
        return new JiliCar();
    }
}
