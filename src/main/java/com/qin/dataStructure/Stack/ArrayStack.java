package com.qin.dataStructure.Stack;

public class ArrayStack{
    private int capacity;
    private int[] stack;
    private int top = -1 ; // 栈顶

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }

    /**
     * 是否栈满
     * @return
     */
    public boolean isFull() {
        return top == capacity-1;
    }

    /**
     * 是否栈空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 压栈
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            return ;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈没空，无法取数据");
        }
        return stack[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("这是一个空栈");
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]\n",stack[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        stack.push(40);
        System.out.println(stack.pop());
        stack.push(30);
        stack.push(20);
        stack.push(10);
        stack.push(100);
        stack.push(100);
        stack.list();

    }
}
