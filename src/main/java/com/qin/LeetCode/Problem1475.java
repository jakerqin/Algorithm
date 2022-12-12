package com.qin.LeetCode;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 *@DESCRIPTION 给你一个数组prices，其中prices[i]是商店里第i件商品的价格。
 * 			商店里正在进行促销活动，如果你要买第i件商品，那么你可以得到与 prices[j] 相等的折扣，
 * 			其中j是满足j > i且prices[j] <= prices[i]的最小下标，如果没有满足条件的j，你将没有任何折扣。
 * 			请你返回一个数组，数组中第i个元素是折扣后你购买商品 i最终需要支付的价格。
 * 		【8,4,6,2,3】 -> [4,2,4,2,3]
 *@Author qinlianji
 *@Date 2022/8/31
 */
public class Problem1475 {

	public static void main(String[] args) {
		Problem1475 problem = new Problem1475();
		int[] test = new int[]{8,4,6,2,3};
		System.out.println(problem.finalPrices(test));
	}

	/**
	 * 遍历了多遍
	 * @param prices
	 * @return
	 */
//	public int[] finalPrices(int[] prices) {
//		int[] finalPrice = new int[prices.length];
//		int length = prices.length;
//		for (int i = 0; i < length; i++) {
//			finalPrice[i] = prices[i];
//			for (int j = i + 1; j < length; j++) {
//				if (prices[j] <= prices[i]) {
//					finalPrice[i] = prices[i] - prices[j];
//					break;
//				}
//			}
//		}
//		return finalPrice;
//	}

	/**
	 * 单调栈 只遍历一次
	 * @param prices
	 * @return
	 */
	public int[] finalPrices(int[] prices) {
		int n = prices.length;
		int[] ans = new int[n];
		Deque<Integer> stack = new ArrayDeque<Integer>();
		// 倒叙
		for (int i = n - 1; i >= 0; i--) {
			// 循环判断栈中元素是否比当前的大，如果大就出栈，所以栈中要么没值，要么就是比当前值小的
			while (!stack.isEmpty() && stack.peek() > prices[i]) {
				stack.pop();
			}
			ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
			// 进栈
			stack.push(prices[i]);
		}
		return ans;
	}

}
