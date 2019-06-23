package Producer_Consumer2;

/**
 *
 * 使用wait-notify 模拟生产-消费模式
 * Created by llj on 2019/4/6.
 */

class Res {
    public String name;
    public String sex;
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
                if (count == 0) {
                    res.name = "小红";
                    res.sex = "女";
                } else {
                    res.name = "小军";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
            }
        }
    }
}

/**
 * 生产者线程
 */
class Consumer extends Thread {
    public Res res;

    public Consumer(Res res) {
        this.res = res;
    }

    @Override
    public synchronized void run() {
        while (true) {
            synchronized (res) {
                System.out.println(res.name + "," + res.sex);
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
