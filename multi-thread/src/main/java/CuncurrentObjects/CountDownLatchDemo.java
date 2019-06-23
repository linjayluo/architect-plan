package CuncurrentObjects;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch类，可以实现类似计数器的功能
 * Created by llj on 2019/4/7.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable(){
            public void run() {
                System.out.println(Thread.currentThread().getName()+"子线程开始");
                System.out.println("执行递减操作，当前值是："+countDownLatch.getCount());
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"子线程结束");
            }
        }
        ).start();
        new Thread(new Runnable(){
            public void run() {
                System.out.println(Thread.currentThread().getName()+"子线程开始");
                System.out.println("执行递减操作，当前值是："+countDownLatch.getCount());
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"子线程结束");
            }
        }
        ).start();

        countDownLatch.await();
        System.out.println("子线程已全部结束，主线程继续执行！");
        for(int count=0;count<10;count++){
            System.out.println("主线程：count="+count);
        }
    }
}
