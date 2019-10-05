package com.qin.dataStructure.Queue;

/**
 * 依旧使用数组来实现，为了避免只能的用一次的囧态，使用环形数组
 *
 * 1.  head 变量的含义做一个调整： head 就指向队列的第一个元素, 也就是说 arr[head] 就是队列的第一个元素
 * head 的初始值 = 0
 * 2.  tail 变量的含义做一个调整：tail 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
 * tail 的初始值 = 0
 *
 * 3. 当队列满时，条件是  (tail  + 1) % maxSize == head 【满】
 * 4. 对队列为空的条件， tail == head 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (tail + maxSize - head) % maxSize   // tail = 1 head = 0
 * 6. 我们就可以在原来的队列上修改得到，一个环形队列
 */
public class ArrayCircularQueue {

    private int maxSize; // 表示数组的最大容量
    private int head;
    private int tail; // 队列尾 （元素的后一个）
    private int[] arr; // 该数据用于存放数据, 模拟队列

    public ArrayCircularQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        return (tail + 1) % maxSize == head;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return tail == head;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        //直接将数据加入
        arr[tail] = n;
        //将 tail 后移, 这里必须考虑取模
        tail = (tail + 1) % maxSize;
    }

    // 获取队列的数据, 出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 head 对应的值保留到一个临时变量
        // 2. 将 head 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[head];
        head = (head + 1) % maxSize;
        return value;

    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        // 动脑筋
        for (int i = head; i < head + size() ; i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size() {
        // tail = 2
        // head = 1
        // maxSize = 3
        return (tail + maxSize - head) % maxSize;
    }

    // 显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[head];
    }
}
