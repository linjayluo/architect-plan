package ListenerModeDemo;

/**
 * 具体被观察者对象的实现
 */
public class User extends Observer {


    public User(String name) {
        super.name = name;
    }

    @Override
    public void update(String message) {
        super.message = message;
        read();
    }

    public void read() {
        System.out.println(super.name + " 收到推送消息：\" " + message+"\"");
    }
}
