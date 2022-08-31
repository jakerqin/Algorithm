package com.qin.LeetCode;

import java.util.Stack;

/**
 *@DESCRIPTION 验证栈序列
 *@Author qinlianji
 *@Date 2022/8/31
 */
public class Problem946 {

	public static void main(String[] args) {
		Problem946 problem = new Problem946();
		int[] pushed = new int[]{1, 2, 3, 4, 5};
		int[] popped = new int[]{4,3,5,1,2};
		System.out.println(problem.validateStackSequences(pushed, popped));
	}

	public boolean validateStackSequences(int[] pushed, int[] popped) {
		if (pushed.length == 0 || popped.length == 0) {
			return Boolean.TRUE;
		}
		int pushedIndex = 0;
		boolean hit ;
		Stack<Integer> temp = new Stack<>();
		for (int poppedIndex = 0; poppedIndex < popped.length; poppedIndex++) {
			hit = false;
			int curr = popped[poppedIndex];
			if (!temp.isEmpty() && temp.peek() == curr) {
				temp.pop();
				continue;
			}
			// 继续遍历数组
			for (; pushedIndex < pushed.length; pushedIndex++) {
				int currPush = pushed[pushedIndex];
				if (currPush != curr) {
					temp.push(currPush);
				} else {
					hit = Boolean.TRUE;
					pushedIndex ++;
					break;
				}
			}
			if (!hit) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
}
