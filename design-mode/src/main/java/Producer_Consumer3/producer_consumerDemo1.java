package Producer_Consumer3;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Calendar;

/**
 *
 * 使用wait-notify 模拟生产-消费模式
 * Created by llj on 2019/4/6.
 */

class Res {
    public String name;
    public String sex;

    //true:允许读，不允许写
    //false:允许写，不允许读
    public boolean flag = false;
}

/**
 * 生产者线程
 */
class Producer extends Thread {
    public Res res;

    public Producer(Res res) {
        this.res = res;
    }

    @Override
    public  void run() {
        int count = 0;
        while (true) {
            synchronized (res) {
                if (res.flag == false) {
                    if (count == 0) {
                        res.name = "小红";
                        res.sex = "女";
                    } else {
                        res.name = "小军";
                        res.sex = "男";
                    }
                    count = (count + 1) % 2;
                    res.flag=true;

                    res.notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    try {
                        res.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

/**
 * 生产者线程
 */
class Consumer extends Thread {
    private String format = "HH:mm:ss";
    public Res res;

    public Consumer(Res res) {
        this.res = res;
    }

    @Override
    public synchronized void run() {
        while (true) {
            synchronized (res) {
                if (res.flag==true) {
                    System.out.println(DateFormatUtils.format(Calendar.getInstance(),format) +res.name + "," + res.sex);
                    res.flag=false;
                    res.notify();
                }else {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


public class producer_consumerDemo1 {


    public static void main(String[] args) {
        Res res = new Res();
        Producer producer = new Producer(res);
        Consumer consumer = new Consumer(res);
        producer.start();
        consumer.start();
    }
}
