package DisruptorProject;

import DisruptorProject.consumer.LongEventHandler1;
import DisruptorProject.consumer.LongEventHandler2;
import DisruptorProject.factory.LongEventFactory;
import DisruptorProject.producer.LongEventProducer;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by llj on 2019/4/20.
 */
public class DisruptorProjectMain {

    public static void main(String[] args) {
        //1.创建可以缓冲的线程池，提供给consumer
        ExecutorService executor = Executors.newCachedThreadPool();

        //2.创建Event工厂
        EventFactory eventFactory = new LongEventFactory();

        //3.指定ringbuffer大小
        int ringbufferSize = 1024*1024;

        //4.创建Disruptor处理器
        Disruptor disruptor = new Disruptor(eventFactory, ringbufferSize, executor, ProducerType.MULTI, new YieldingWaitStrategy());

        //5.连接消费者--注册消费者
        disruptor.handleEventsWith(new LongEventHandler1());
        disruptor.handleEventsWith(new LongEventHandler2());

        //多个消费者 一个生产者 默认重复消费 配置分组

//        6.启动
        disruptor.start();

        //7.获取已创建的RingBuffer容器
        RingBuffer ringBuffer  = disruptor.getRingBuffer();

        //8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        // 9.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(8); //申请8个字节空间的缓冲区

        // 生产100条消息
        for (int i=1;i<100;i++){
            byteBuffer.putLong(0,i);
            producer.onData(byteBuffer);
        }

        //关闭线程池
        executor.shutdown();
        //关闭并发处理器
        disruptor.shutdown();

    }
}
