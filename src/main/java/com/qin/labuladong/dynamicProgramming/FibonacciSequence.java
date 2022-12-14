package com.qin.labuladong.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 *@DESCRIPTION 斐波那契数列的几种解法来明确动态规划思想
 *@Author qinlianji
 *@Date 2022/12/9
 */
public class FibonacciSequence {

	/**
	 * 这个就是暴力递归。但是画出递归树会发现有很多重复的计算---> 动态规划需要解决的第一个问题：重叠子问题
	 * 而且时间复杂度是子问题的个数乘以解决这个子问题的时间，二叉树的节点总数为指数级别，所以子问题个数是O(2^n)
	 * @param n
	 * @return
	 */
	private static int fib(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return fib(n - 1) + fib(n - 2);
	}

	/**
	 * 带备忘录的递归算法, 解决重叠子问题, 时间复杂度是O(N)
	 * 这是一种自顶向下的解法
	 * @param n
	 * @return
	 */
	private static int fib2(int n) {
		List<Integer> memo = new ArrayList<>(n + 1);
		return helper(memo, n);
	}

	private static int helper(List<Integer> memo, int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		if (memo.get(n) != null) {
			return memo.get(n);
		}
		memo.set(n, helper(memo, n - 1) + helper(memo, n - 2));
		return memo.get(n);
	}

	/**
	 * 动态规划一般都是自底向上，直接从最底下，最简单，问题规模最小的 f(1) 和 f(2) 开始往上推，直到推到我们想要的答案 f(n)，这就是动态规划的思路，
	 * 这也是为什么动态规划一般都脱离了递归，而是由循环迭代完成计算
	 *
	 * DP数组的迭代解法---自底向上
	 * 状态转移方程：f(n) = f(n-1) + f(n-2)
	 * @param n
	 * @return
	 */
	private static int fib3(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1; dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	/**
	 * 根据斐波那契数列的状态转移方程，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，
	 * 只要想办法存储之前的两个状态就行了。所以，可以进一步优化，把空间复杂度降为 O(1)
	 * @param n
	 * @return
	 */
	private static int fib4(int n) {
		int pre = 1; int curr = 1;
		for (int i = 3; i <= n; i++) {
			int temp = curr;
			curr = pre + curr;
			pre = temp;
		}
		return curr;
	}

	public static void main(String[] args) {
		System.out.println(fib(20));
		System.out.println(fib3(20));
		System.out.println(fib4(20));
	}
}
