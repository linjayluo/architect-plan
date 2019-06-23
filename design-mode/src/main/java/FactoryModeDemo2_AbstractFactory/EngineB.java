package FactoryModeDemo2_AbstractFactory;

/**
 * Created by llj on 2019/4/24.
 */
public class EngineB implements Engine {

    public void run() {
        System.out.println("三线品牌发动机，转的慢！");
    }

    public void start() {
        System.out.println("手动挡，操控爽！");
    }
}
