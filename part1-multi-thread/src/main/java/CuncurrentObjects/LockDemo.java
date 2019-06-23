package CuncurrentObjects;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by llj on 2019/4/8.
 */


class Res {
    public String name;
    public String sex;
    public boolean flag = false; //true: 只能读不能写  false:只能写不能读
    public Lock lock = new ReentrantLock(); //创建可重入锁
}

class Producer extends Thread {
    private Res res;
    private Condition newCondition;

    public Producer(Res res, Condition newCondition) {
        this.res = res;
        this.newCondition = newCondition;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                res.lock.lock();
                if (res.flag) {
                    try {
                        newCondition.await();
                    } catch (Exception e) {

                    }
                }
                if (count == 0) {
                    res.name = "于胜军";
                    res.sex = "男";
                } else {
                    res.name = "小红";
                    res.sex = "女";
                }
                count = (count + 1) % 2;
                res.flag = true;
                newCondition.signal(); //类似于res.notify()
                Thread.sleep(1000);
            } catch (Exception e) {

            } finally {
                res.lock.unlock();
            }
        }
    }
}

class Consumer extends Thread {
    private Res res;
    private Condition newCondition;

    public Consumer(Res res, Condition newCondition) {
        this.res = res;
        this.newCondition = newCondition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                res.lock.lock();
                if (!res.flag) {
                    newCondition.await();
                }

                System.out.println(res.name + "," + res.sex);
                res.flag = false;
                newCondition.signal();
            } catch (Exception e) {

            } finally {
                res.lock.unlock();
            }
        }
    }
}


public class LockDemo {
    public static void main(String[] args) {
        Res res = new Res();
        Condition newCondition = res.lock.newCondition(); //注意获取condition的方式
        Producer producer = new Producer(res, newCondition);
        Consumer consumer = new Consumer(res, newCondition);
        producer.start();
        consumer.start();
    }

}
