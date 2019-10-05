package com.qin.dataStructure.Queue;


import java.lang.reflect.Array;

/**
 * 用数组模拟队列
 * 只能使用一次，不是环形队列
 */
public class ArrayQueue<T> {
    private int capacity;
    private int head;
    private int tail;
    private T[] arr;

    public ArrayQueue(int capacity,Class<T> type){
        this.capacity = capacity;
        head=-1; //只能指向头部元素的前一个
        tail = -1;  // 指向尾部本身
        this.arr = (T[])Array.newInstance(type, capacity );
    }

    public boolean isFull() {
        return tail >= capacity -1;
    }

    public boolean isEmpty(){
        return head == tail;
    }

    public void addQueue(T t) {
        if (isFull()){
            System.out.println("队列已满！");
            return ;
        }
        tail ++;
        arr[tail] = t;
    }

    public T getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，取个屁");
        }
        head ++;

        return arr[head];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空队列，show个屁");
            return ;
        }
        for (int i = 0;i< arr.length;i++) {
            System.out.println(arr[i]);
        }
    }

    public T showHead(){

        if (isEmpty()) {
            throw new RuntimeException("对列为空！");
        }
        return arr[head+1];
    }


    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>(3,Integer.class);
        queue.addQueue(10);
        queue.addQueue(20);
        queue.addQueue(30);
        queue.addQueue(4);
    }
}
