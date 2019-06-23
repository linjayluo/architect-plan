package FactoryModeDemo0_SimpleFacory;

import org.apache.commons.lang.StringUtils;

/**
 * 简单工厂模式
 * 缺点：可维护性较差
 * Created by llj on 2019/4/22.
 */
public class SimpleFactory {
    public static Car getCar(String name){
        if (StringUtils.isEmpty(name)){
            return null;
        }
        if ("吉利".equals(name)){
            return new JiliCar();
        }
        if ("比亚迪".equals(name)){
            return new BydCar();
        }
        return null;
    }

    public static void main(String[] args){
        Car byd = SimpleFactory.getCar("比亚迪");
        byd.run();
    }
}
