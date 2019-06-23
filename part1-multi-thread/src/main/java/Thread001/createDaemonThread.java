package Thread001;

/**
 * 创建守护线程： 当主线程结束的时候，守护线程也随之结束
 * Created by llj on 2019/3/31.
 */
public class createDaemonThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("---main thread start---");
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i=0;i<=100;i++){
                    try {
                        Thread.sleep(10);
                        System.out.println("sub thread ruuning"+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.setDaemon(false);
        thread.start();
        for (int i=0;i<=10;i++){
            Thread.sleep(10);
            System.out.println("main thread ruuning"+i);
        }
        System.out.println("-----main thread end----");
    }
}
