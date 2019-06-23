package Thread001;

/**
 *
 * 验证JMM内存模型：线程运行时是在副本中改变共享变量的值，当还未及时刷新到主内存前，该线程不会结束
 * Created by llj on 2019/4/2.
 */
class ThreadVolatileDemo extends Thread {
    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("线程开始执行");
        while (flag == true) {

        }
        System.out.println("线程结束");
    }

    public void setRunning(boolean flag) {
        this.flag = flag;
    }
}


public class volatileDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo thread = new ThreadVolatileDemo();
        thread.start();
        Thread.sleep(3000);
        thread.setRunning(false);
        System.out.println("flag has been set into false!");
        Thread.sleep(1000);
        System.out.println(thread.flag);
    }
}