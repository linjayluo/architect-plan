package LockDemo001;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by llj on 2019/4/15.
 */
public class ReentrantReadWriteLockDemo extends Thread {
    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    public static final Object get(String key) {
        readLock.lock();

        try {
            System.out.println("正在做读的操作,key:" + key + " 开始");
            Thread.sleep(100);
            Object object = map.get(key);
            System.out.println("正在做读的操作,key:" + key + " 结束");
            System.out.println();
            return object;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return key;
    }

    //
    public static final Object put(String key, Object value) {//为什么要加 final关键字？？？
        writeLock.lock();

        try {
            System.out.println("正在做写的操作。。 key：" + key + ",value:" + value + "开始。。");
            Thread.sleep(100);
            Object object = map.put(key, value);
            System.out.println("正在做写的操作。。 key：" + key + ",value:" + value + "结束。。");

            return object;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        return value;
    }


    public void clearAll() {
        writeLock.lock();

        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ReentrantReadWriteLockDemo.put(i + "", i + "");
                }
            }
        });
        Thread t2 =new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ReentrantReadWriteLockDemo.get(i + "");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
