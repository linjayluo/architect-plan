package DisruptorProject.producer;

import DisruptorProject.entity.LongEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 生产者处理逻辑
 * Created by llj on 2019/4/20.
 */
public class LongEventProducer {
    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer ringBuffer){
        this.ringBuffer = ringBuffer;
    }


    public void onData(ByteBuffer byteBuffer){
        //获取事件队列 下标位置
        long sequence = ringBuffer.next();

        try {
            //取出空队列
            LongEvent longEvent = ringBuffer.get(sequence);
            //给空队列赋值
            longEvent.setValue(byteBuffer.getLong(0));
        }catch (Exception e){

        }finally {
            System.out.println("生产者发送数据。。。");
            //通知消费者可以取当前游标位置为sequence的值了
            ringBuffer.publish(sequence);
        }
    }
}
