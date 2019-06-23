package FactoryModeDemo2_AbstractFactory;

/**
 * Created by llj on 2019/4/24.
 */
public class EngineA implements Engine {

    public void run() {
        System.out.println("豪华品牌转子发动机，转的贼快！");
    }

    public void start() {
        System.out.println("自动挡，启动快");
    }
}
