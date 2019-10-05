package com.qin.dataStructure.Linked;

/**
 * Created by 13350 on 2019/4/9.
 */
public class DoubleLink<E> {
    int size;
    private DNode head; //头结点
    private DNode tail; //尾节点

    public int getSize() {
        return size;
    }

    public DNode getHead() {
        return head;
    }

    public void setHead(DNode head) {
        this.head = head;
    }

    public DNode getTail() {
        return tail;
    }


    public void setTail(DNode tail) {
        this.tail = tail;
    }
    public boolean isEmpty(){
        return size==0 ? true : false;
    }
    public void add(E value){
        if (head == null) {
            head = tail = new DNode(value,null,null);
        }else {
            tail.next = new DNode(value);//加入节点
            tail.next.prev = tail; //新节点前驱节点
            tail = tail.next; //移动表尾指针
            tail.next = null; //
        }
        size++;
    }

    public E get(int index) {
        //返回第index位置的节点的值
        if (index > size || index < 0 || size==0)
            return null;
        if (index < size /2) { //正向查找
            DNode tempiont = head;
            for (int i = 0; i < index; i++) {
                tempiont = tempiont.next;
            }
            return (E) tempiont.value;
        }else {
            DNode tempiont = tail;
            for (int j=0; j< size-index-1;j++){
                tempiont = tempiont.prev;
            }
            return (E) tempiont.value;
        }
    }

    public void inest(int index,E value){
        /**
         * @Description: 在index后面插入一个节点
         * @Param: [index, value]
         */
        DNode node = getNode(index);
        if (node == tail){//在表尾插入
            add(value);
        }else {
            DNode newnode = new DNode(value);
            newnode.prev = node;
            newnode.next = node.next;
            node.next = newnode;
            newnode.next.prev = newnode;
        }
    }

    public DNode getNode(int index) {
//返回第index位置的节点的值
        if (index > size || index < 0 || size == 0)
            throw new IndexOutOfBoundsException("非法索引");
        if (index < size / 2) { //正向查找
            DNode tempiont = head;
            for (int i = 0; i < index; i++) {
                tempiont = tempiont.next;
            }
            return tempiont;
        } else {
            DNode tempiont = tail;
            for (int j = 0; j < size - index - 1; j++) {
                tempiont = tempiont;
            }
            return tempiont;
        }
    }

    public void delect(int index){
/**
 * @Description:删除index 位置处的节点
 * @Param: [index]
 * @return: void
 * @Author: Huabuxiu
 * @Date: 2019/4/6
 */
        if (index >= size || index < 0 || size==0)
            throw new IndexOutOfBoundsException("非法索引");
        DNode node = getNode(index);
        if (node == head){ //删头
            if (head != tail){
                head = head.next;
                head.prev = null;
            }else {
                head = head.next;
            }
            size--;
            node = null;
            return;
        }else if(node == tail){ //删尾
            tail = tail.prev;
            tail.next = null;
            size--;
            node = null;
            return;
        }else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node = null;
            size--;
        }
    }

}

class DNode<E>{
    public E value;
    public DNode prev;
    public DNode next;
    public DNode(E value, DNode prev, DNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
    public DNode(E value) {
        this.value = value;
    }
}
