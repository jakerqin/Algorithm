package com.qin.dataStructure.skipList;

/**
 *@DESCRIPTION
 *@Author qinlianji
 *@Date 2022/8/31
 */
public class SkipListNode<T> {
	public int key;
	public T value;
	public SkipListNode<T> pre, next, up, down; //上下左右四个节点，pre和up存在的意义在于 "升层"的时候需要查找相邻节点

	public static final int HEAD_KEY = Integer.MIN_VALUE; // 负无穷
	public static final int TAIL_KEY = Integer.MAX_VALUE; // 正无穷

	public SkipListNode(int k, T v) {
		key = k;
		value = v;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof SkipListNode<?>)) {
			return false;
		}
		SkipListNode<T> ent;
		try {
			ent = (SkipListNode<T>) o; //检测类型
		} catch (ClassCastException ex) {
			return false;
		}
		return (ent.getKey() == key) && (ent.getValue() == value);
	}

	@Override
	public String toString() {
		return "key-value:" + key + "," + value;
	}
}
