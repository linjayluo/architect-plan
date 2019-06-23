package AtomicDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by llj on 2019/4/16.
 */
public class Test001 extends Thread {
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        while (true) {
            int count = getCount();
            if (count >= 300) {
                break;
            }
            System.out.println(count);
        }
    }

    public int getCount() {
        try {
            Thread.sleep(35);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count.addAndGet(1);
    }


    public static void main(String[] args) {
        Test001 test001 = new Test001();
        Thread t1 = new Thread(test001);
        Thread t2 = new Thread(test001);
        t1.start();
        t2.start();
    }
}
