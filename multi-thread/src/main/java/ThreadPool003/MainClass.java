package ThreadPool003;

/**
 * Created by llj on 2019/4/11.
 */
public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        FutureClient futureClient = new FutureClient();
        Data submit = futureClient.submit("245677");
        System.out.println("主线程等待10s。。。");
        Thread.sleep(10000);

        System.out.println("在主线程中获取子线程中获取的值");
        String request = submit.getRequest();

        System.out.println(request);
    }
}
