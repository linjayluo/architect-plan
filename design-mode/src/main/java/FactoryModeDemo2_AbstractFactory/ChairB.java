package FactoryModeDemo2_AbstractFactory;

/**
 * 座椅+B'
 * Created by llj on 2019/4/24.
 */
public class ChairB implements Chair {
    public void run() {
        System.out.println("没法自动加热，织物座椅");
    }
}
