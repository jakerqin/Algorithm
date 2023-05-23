package test.disruptor;

/**
 * @Author qinlianji
 * @Date 2023/3/22 11:23
 **/
public interface EventFactory<T> {
    T newInstance();
}
