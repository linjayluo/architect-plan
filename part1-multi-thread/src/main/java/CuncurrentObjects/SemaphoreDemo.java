package CuncurrentObjects;

import java.util.concurrent.Semaphore;

/**
 * Created by llj on 2019/4/7.
 */
class Thread001 extends Thread {
    private String name;
    private Semaphore wc;

    public Thread001(String name, Semaphore wc) {
        this.name = name;
        this.wc = wc;
    }

    @Override
    public void run() {
        int availablePermits = wc.availablePermits();
        if (availablePermits > 0) {
            System.out.println(name + "终于有坑位了，赶紧进去解决一下人生大事。。。");
        } else {
            System.out.println(name + "麻蛋，怎么还没有人出来");
        }

        //申请资源  如果申请不到的话，将会阻塞，直到获取到资源
        try {
            wc.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + "终于可以不担心拉裤裆了，当前剩余坑位:" + wc.availablePermits());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "解决完大事了，熏死了，快出去（释放资源）");
        //释放资源
        wc.release();
    }
}


public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Thread001 thread001 = new Thread001("No." + i, semaphore);
            thread001.start();
        }
    }
}
