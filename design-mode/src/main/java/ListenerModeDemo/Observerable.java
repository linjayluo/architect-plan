package ListenerModeDemo;

/**
 * 定义抽象观察者接口
 */
public interface Observerable {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyAllObserver();

    public void publishMessage(String msg);

}