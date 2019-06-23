package ListenerModeDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 */
public class WechatServer implements Observerable {
    String message = null;

    // 观察者的存储集合
    private List<Observer> list = new ArrayList<>();

    // 注册观察者
    public void registerObserver(Observer obs) {
        list.add(obs);
        System.out.println("用户\""+obs.name+"\"已注册");
    }

    // 删除观察者
    public void removeObserver(Observer obs) {
        list.remove(obs);
        System.out.println("用户:\"" + obs.name + "\" 已取消订阅公众号");
    }

    //发布新消息
    public void publishMessage(String message) {
        this.message = message;
        this.notifyAllObserver();
    }


    // 通知所有的观察者更新
    @Override
    public void notifyAllObserver() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }

}