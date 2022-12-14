package com.qin.labuladong.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

/**
 *@DESCRIPTION 动态规划 凑零钱问题
 *
 * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
 *
 * 首先，这个问题是动态规划问题，因为它具有「最优子结构」的。要符合「最优子结构」，子问题间必须互相独立。
 * 比如说，你的原问题是考出最高的总成绩，那么你的子问题就是要把语文考到最高，数学考到最高…… 为了每门课考到最高，你要把每门课相应的选择题分数拿到最高，填空题分数拿到最高…… 当然，最终就是你每门课都是满分，这就是最高的总成绩。
 * 得到了正确的结果：最高的总成绩就是总分。因为这个过程符合最优子结构，“每门科目考到最高”这些子问题是互相独立，互不干扰的
 *
 *@Author qinlianji
 *@Date 2022/12/9
 */
public class CoinChange {
	public static void main(String[] args) {
		System.out.println(dp(Arrays.asList(1, 2, 5), 11));
		// 备忘录。总容量是amount + 1
		Integer[] memo = new Integer[11 + 1];
		System.out.println(dp2(Arrays.asList(1, 2, 5), 11, memo));

		System.out.println("-------");
	}


	/**
	 * 递归树节点的个数也就是子问题，时间复杂度是O(n^k)，每个子问题中含有一个for循环，所以时间复杂度是O(k * n ^k), 所以是指数级别的
	 * @param coins
	 * @param amount
	 * @return
	 */
	private static int dp(List<Integer> coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		if (amount < 0) {
			return -1;
		}
		Integer min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int subProblem = dp(coins, amount - coin);
			if (subProblem == -1) {
				continue;
			}
			min = Math.min(min, 1 + subProblem);
		}
		if (min == Integer.MAX_VALUE) {
			return -1;
		}
		return min;
	}

	/**
	 * 带备忘录的递归。消除重复子问题. 所以子问题的总数不会超过金额数n，即子问题数目为O(n),处理一个子问题的时间不变，还是O(k), 所以总的时间复杂度是O(kn)
	 * @param coins
	 * @param amount
	 * @return
	 */
	private static int dp2(List<Integer> coins, int amount, Integer[] memo) {

		if (amount == 0) {
			return 0;
		}
		if (amount < 0) {
			return -1;
		}
		if (memo[amount] != null) {
			return memo[amount];
		}
		Integer min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int subProblem = dp2(coins, amount - coin, memo);
			if (subProblem == -1) {
				continue;
			}
			min = Math.min(min, 1 + subProblem);
		}
		if (min == Integer.MAX_VALUE) {
			return -1;
		}
		memo[amount] = min;
		return memo[amount];
	}


	/**
	 * dp数组迭代解法。自底向上
	 * @param coins
	 * @param amount
	 * @return
	 */
	private static int dp3(List<Integer> coins, int amount) {

		int[] dp = new int[amount + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = amount + 1;
		}
		for (int i = 0; i < dp.length; i++) {
			for (int coin : coins) {
				if (i < coin) {
					continue;
				}
				dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
			}
		}

		return (dp[amount] == amount + 1) ? -1 : dp[amount];
	}
}

