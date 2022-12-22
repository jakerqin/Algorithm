package com.qin.labuladong.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *@DESCRIPTION 打开旋转锁。你有一个带有四个圆形波轮的转盘锁，没个波轮都有0～9共10个数字，没个拨轮都可以想上或向下旋转。
 * 				计算从拨轮初始状态0000到xxxx开锁至少需要转动拨轮多少下，不能解锁返回-1(存在死亡数字列表deadends)
 *@Author qinlianji
 *@Date 2022/12/22
 */
public class OpenLock {
	int BFS(String deadends, String target) {
		Queue<String> queue = new LinkedList<>();
		// 记录转动到的锁面，每次放入queue中先从这个表中判断是否已经存在，避免导致死循环，
		Set<String> existSet = new HashSet<>();
		int step = 0;
		String root = "0000";
		existSet.add(root);
		queue.offer(root);
		while (!queue.isEmpty()) {
			for (int j = 0; j < queue.size(); j++) {
				String curr = queue.poll();
				if (deadends.contains(curr)) {
					continue;
				}
				if (target.equals(curr)) {
					return step;
				}
				for (int i = 0; i < 4; i++) {
					// 向上转动
					String plusOne = plusOne(curr, i);

					if (!existSet.contains(plusOne)) {
						existSet.add(plusOne);
						queue.add(plusOne);
					}

					// 向下转动
					String minusOne = minusOne(curr, i);
					if (target.equals(minusOne)) {
						return ++step;
					}
					if (!existSet.contains(minusOne)) {
						existSet.add(minusOne);
						queue.add(minusOne);
					}
				}
			}
			step++;
		}
		return -1;
	}

	/**
	 * 向下拨动
	 * @param curr
	 * @param index
	 * @return
	 */
	private String minusOne(String curr, int index) {
		char[] chars = curr.toCharArray();
		if (chars[index] == '0') {
			chars[index] = '9';
		} else {
			chars[index] -= 1;
		}
		return new String(chars);
	}

	/**
	 * 向上拨动
	 * @param curr
	 * @param index
	 * @return
	 */
	private String plusOne(String curr, int index) {
		char[] chars = curr.toCharArray();
		if (chars[index] == '9') {
			chars[index] = '0';
		} else {
			chars[index] += 1;
		}
		return new String(chars);
	}
}
