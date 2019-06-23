package Thread001;

/**
 * 使用join（）方法，指定子线程于主线程之前执行完
 * Created by llj on 2019/3/31.
 */
public class joinThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("---main thread start---");
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i=0;i<=60;i++){
                    System.out.println("sub thread ruuning"+i);
                }
            }
        });
        thread.setDaemon(false);
        thread.start();
        //主线程让子线程先执行完毕
        thread.join();
        for (int i=0;i<=30;i++){
            System.out.println("main thread ruuning"+i);
        }
        System.out.println("-----main thread end----");
    }
}
