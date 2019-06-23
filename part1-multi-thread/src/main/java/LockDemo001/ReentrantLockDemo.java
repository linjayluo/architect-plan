package LockDemo001;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by llj on 2019/4/15.
 */
public class ReentrantLockDemo extends Thread {
    ReentrantLock lock = new ReentrantLock();
    public void get(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"调用get()方法！");
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "调用set()方法！");
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args){
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();

    }
}
