package ListenerModeDemo;

/**
 * 参考微信中 用户-订阅-公众号 的模式：
 *  实现对已订阅该公众号的用户进行消息推送
 *  对于取消订阅的该用户，将无法收到公众号发布的最新消息
 */
public class MainTest {

	public static void main(String[] args) {

		//创建观察者
        Observerable wechatServer = new WechatServer();
        //创建用户和注册用户
        User zhangsan = new User("张三");
        User lisi = new User("李四");
        User wangwu = new User("王五");
        System.out.println("------------------Start----------------------------");
        wechatServer.registerObserver(zhangsan);
        wechatServer.registerObserver(lisi);
        wechatServer.registerObserver(wangwu);
        System.out.println("----------------------------------------------");
		//观察者发布新消息，已订阅用户均可以收到消息
        wechatServer.publishMessage("来自公众号的第1条消息：秦始皇向你借钱时该怎么弄？");
        System.out.println("----------------------------------------------");
		//用户取消订阅公众号
        wechatServer.removeObserver(lisi);
        System.out.println("----------------------------------------------");
		//观察者发布新消息，取消订阅的该用户将无法收到消息
        wechatServer.publishMessage("来自公众号的第2条消息：秦始皇弟弟向你借钱时又该怎么弄？");
        System.out.println("------------------End----------------------------");

	}

}