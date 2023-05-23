package test.disruptor;

/**
 * @Author qinlianji
 * @Date 2023/3/22 11:26
 **/
public class BossEvent {

    public String channel;

    public void clear() {
        channel = null;
    }

    public static void main(String[] args) {
        Disruptor<BossEvent> dis = new Disruptor<>(BossEvent::new);
    }
}
