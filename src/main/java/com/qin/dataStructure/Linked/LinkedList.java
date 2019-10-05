package com.qin.dataStructure.Linked;

/**
 * 单向链表
 */
public class LinkedList<E> {
    int size;
    private Node head;
    private Node tail;

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E elem){
        //加入节点
        if (head == null) {
            head = tail = new Node(elem);
            head.next = null;
        } else {
            tail.next = new Node(elem);
            tail = tail.next;
            size++;
        }
    }

    /**
     * 得到第index的值
     * @param index
     * @return
     */
    public E get(int index) {
        if (index > size || index <= 0) {
            return null;
        }
        Node sec = head;
        for (int i = 0;i<index;i++) {
            sec = sec.next;
        }

        return (E)sec.elem;
    }

    /**
     * 按索引删除链表
     * @param index
     * @throws Exception
     */
    public void delete(int index) throws Exception {
        if (index > size || index <= 0) {
            throw new Exception("非法索引！");
        }
        Node node = new Node(null, head);
        for (int i = 0;i<index-1;i++) {
            node = node.next;
        }
        if (node.next == head) {
            head = head.next;
        }
        if (node.next == tail) {
            tail = node;
        }
        node.next = node.next.next;
        size--;
    }

    /**
     * 按元素值删除
     * @param elem
     * @return
     */
    public boolean delete(E elem) {
        Node temp = new Node(null, head);

        while (temp.next != null) {
            if (temp.next.elem.equals(elem)) {
                if (temp.next == tail) {
                    tail = temp;
                }else if (temp.next == head) {
                    head = head.next;
                }else{
                    temp.next = temp.next.next;
                }
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}

class Node<E>{
    E elem;
    Node next;

    public Node(E elem) {
        this.elem = elem;
    }

    public Node(E elem, Node next) {
        this.elem = elem;
        this.next = next;
    }

    public E getElem(){
        return this.elem;
    }

    public Node getNext(){
        return this.next;
    }
}