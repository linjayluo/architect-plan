package ThreadPool001;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by llj on 2019/4/9.
 */
class MyThead implements Runnable {
    private int num;

    public MyThead(int num) {
        this.num = num;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "--" + num);
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                return;
            }
        }
    }
}

public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3));

        //第1个线程，小于核心线程数，直接执行
        threadPoolExecutor.execute(new MyThead(1));
        //第2个线程，大于核心线程数，小于缓冲队列长度，放入缓存队列
        threadPoolExecutor.execute(new MyThead(2));
        //第3个线程，大于核心线程数，小于缓冲队列长度，放入缓存队列
        threadPoolExecutor.execute(new MyThead(3));
        //第4个线程，大于核心线程数，小于缓冲队列长度，放入缓冲队列
        threadPoolExecutor.execute(new MyThead(4));
//        //第5个线程，大于核心线程数，大于缓冲队列长度，小于最大线程数，创建线程，并执行
        threadPoolExecutor.execute(new MyThead(5));
//        //超过最大线程数，报错
//        threadPoolExecutor.execute(new MyThead(6));
        threadPoolExecutor.shutdown();
    }
}
