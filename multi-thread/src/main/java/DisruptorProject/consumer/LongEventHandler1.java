package DisruptorProject.consumer;

import DisruptorProject.entity.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 *  消费者逻辑代码
 * Created by llj on 2019/4/20.
 */
public class LongEventHandler1 implements EventHandler<LongEvent> {
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("消费者1 获取生产者数据..longEvent:" + longEvent.getValue());
    }
}
