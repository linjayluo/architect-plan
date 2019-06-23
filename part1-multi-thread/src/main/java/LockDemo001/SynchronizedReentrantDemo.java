package LockDemo001;

/**
 * Created by llj on 2019/4/15.
 */
public class SynchronizedReentrantDemo implements Runnable {

    public synchronized void get(){
        System.out.println( Thread.currentThread().getName()+"调用get（）方法");
        set();
    }


    public synchronized String set(){
        System.out.println(Thread.currentThread().getName() + "调用set（）方法");
        return "java";
    }



    public void run() {
        get();
    }

    public static void main(String[] args){
        SynchronizedReentrantDemo sd = new SynchronizedReentrantDemo();
        new Thread(sd).start();
        new Thread(sd).start();
        new Thread(sd).start();
        new Thread(sd).start();
    }
}
