package com.qin.dataStructure.LRU;

import java.util.HashMap;

/**
 * 从底层构造开始写代码环境。可以继承LinkedHashMap<>
 */
public class LRUcache {
    private int size;
    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUcache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>(capacity, 0.75F);
    }

    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.value;
    }

    private Node getNode(int key) {
        Node node = map.get(key);
        if (node == null) return null;

        if (tail != node) {
            if (head == node) {
                head = node.next;
                head.prev = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            appendTail(node);
        }

        return node;
    }

    private void trimToSize() {
        while (size > capacity) {
            Node prev = head;
            head = head.next;
            head.prev = null;
            size--;
            map.remove(prev.key);
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        Node prev = getNode(key);

        if (prev == null) {
            map.put(key, node);
            appendTail(node);
            size++;
            trimToSize();
        } else {
            prev.value = value;
        }
    }

    private void appendTail(Node node) {
        if (size == 0) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            node.next = null;
            tail = node;
        }
    }

    class Node {
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        int key;
        int value;
        Node next;
        Node prev;
    }
}