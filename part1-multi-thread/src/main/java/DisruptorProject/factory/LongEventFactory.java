package DisruptorProject.factory;

import DisruptorProject.entity.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * 消息工厂
 * Created by llj on 2019/4/20.
 */
public class LongEventFactory  implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }
}
