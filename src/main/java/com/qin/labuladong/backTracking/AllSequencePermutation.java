package com.qin.labuladong.backTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *@DESCRIPTION 回溯算法--全排列问题。给定一个数组，给出这个数组的所有可能的元素排列
 * 			   找出有哪些可走的路径，并记录已经走过的路径，知道还有哪些可选元素列表，表示当前可以做出的选择；结束条件就是遍历到树的底层，也就是可选元素列表为空的时候
 *
 *@Author qinlianji
 *@Date 2022/12/14
 */
public class AllSequencePermutation {

	public static List<List<Integer>> permutation(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backTracking(res, nums, new LinkedList<>());
		return res;
	}

	public static void backTracking(List<List<Integer>> res, int[] nums, LinkedList<Integer> track) {
		if (track.size() == nums.length) {
			res.add(new LinkedList<>(track));
			return ;
		}
		for (int i = 0; i < nums.length; i++) {
			if (track.contains(nums[i])) {
				continue;
			}
			track.add(nums[i]);
			backTracking(res, nums, track);
			track.removeLast();
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> permutation = permutation(new int[]{1, 2, 3});
		System.out.println(permutation.toString());
	}
}
