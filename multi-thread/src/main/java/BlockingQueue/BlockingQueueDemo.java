package BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞式队列：使用BlockingQueue
 * Created by llj on 2019/4/7.
 */

class ProducerThread implements Runnable {
    private BlockingQueue blockingQueue;
    private AtomicInteger count = new AtomicInteger();
    private volatile boolean FLAG = true;

    public ProducerThread(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (true) {
            while (FLAG) {
                String data = count.incrementAndGet() + "";
                //当队列未满时，往队列中添加消息
                try {
                    boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                    if (offer) {
                        System.out.println(Thread.currentThread().getName() + "生产队列" + data + "成功");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "生产队列" + data + "失败");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
                System.out.println("停止生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        FLAG = false;
    }

    public void restart() {
        FLAG = true;
    }
}

/**
 * 消费者线程
 */
class ConsumerThread implements Runnable {
    private volatile boolean FLAG = true;
    private BlockingQueue<String> blockingQueue;

    public ConsumerThread(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        System.out.println("消费者线程启动...");
        while (FLAG) {
            try {
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (data == null) {
//                    FLAG = false;
                    System.out.println("等待超过2s，获取失败");
//                    return;
                }else {
                    System.out.println("消费者成功获取到队列信息，data：" + data);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

        ProducerThread producerThread = new ProducerThread(queue);
        ConsumerThread consumerThread = new ConsumerThread(queue);
        Thread threadP = new Thread(producerThread);
        Thread threadC = new Thread(consumerThread);
//        threadP.setDaemon(true);
        threadP.start();
//        threadC.setDaemon(true);
        threadC.start();

        Thread.sleep(5000L);
        System.out.println("主线程手动停止生产线程");
        producerThread.stop();

        Thread.sleep(3000L);
        System.out.println("尝试重启生产者线程");
        producerThread.restart();

        System.out.println("主线程结束");
    }
}
