package com.qin.example;

/**
 * 用栈来实现简单的计数器
 */
public class CalculatorArrayStack {
    public static void main(String[] args) {
        String expression = "70*2-5";
        Stack numStack = new Stack(10);
        Stack operStack = new Stack(10);
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int ch = 0;
        char oper = ' ';
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < expression.length(); i++) {
            oper = expression.charAt(i);
            // 判断是不是操作符
            if (operStack.isOper(oper)) {
                // 判断栈是否为不为空
                if (!operStack.isEmpty()) {
                    // 栈不为空，判断当前操作数与栈顶的优先级
                    if (operStack.priority(oper) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        ch = operStack.pop();
                        res = operStack.clac(num1, num2, ch);
                        numStack.push(res);
                        operStack.push(oper);
                    } else {
                        operStack.push(oper);
                    }
                } else {
                    operStack.push(oper);
                }
            } else {
                //不是操作符，观察是不是多位数
                //  numStack.push(oper - 48);
                buffer.append(oper);
                if (i == expression.length() - 1) {
                    numStack.push(Integer.valueOf(buffer.toString()));
                }else{
                    while (true){
                        oper = expression.charAt(i+1);
                        if (!operStack.isOper(oper)) {
                            buffer.append(oper);
                            i++;
                        } else {
                            numStack.push(Integer.valueOf(buffer.toString()));
                            buffer.delete(0, buffer.length());
                            break;
                        }
                    }
                }
            }
        }


        while(true) {
            //如果符号栈为空，则计算到最后的结果, 数栈中只有一个数字【结果】
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            ch = operStack.pop();
            res = numStack.clac(num1, num2, ch);
            numStack.push(res);//入栈
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);

    }

}


class Stack {
    private int capacity;
    private int[] stack;
    private int top = -1; // 栈顶

    public Stack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }

    /**
     * 是否栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * 是否栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 压栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈没空，无法取数据");
        }
        return stack[top--];
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("这是一个空栈");
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]\n", stack[i]);
        }
    }

    /**
     * 只看看栈顶元素，不出站
     * @return
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 两数之间做运算
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int clac(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+' :
                res = num1+ num2;
                break;
            case '-' :
                res = num2 - num1;
                break;
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 判断操作符的优先级
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/'){
            return 2;
        }
        if (oper == '+' || oper == '-') {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 判断是否是操作
     * @param oper
     * @return
     */
    public boolean isOper(char oper) {
        return oper =='*' || oper =='/' || oper =='+'|| oper == '-';
    }
}
