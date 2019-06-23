package ListenerModeDemo;


/**
 * 观察者抽象对象
 */
public abstract class Observer {
    public String name;
    public String message;
    public abstract void update(String message);
}