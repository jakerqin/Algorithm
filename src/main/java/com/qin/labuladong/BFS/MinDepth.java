package com.qin.labuladong.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 *@DESCRIPTION BFS（广度优先搜索）。求树最小的树深
 *@Author qinlianji
 *@Date 2022/12/22
 */
public class MinDepth {

	int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int step = 1;
		while (!queue.isEmpty()) {
			for (int i = 0; i < queue.size(); i++) {
				TreeNode curr = queue.poll();
				if (curr.left == null && curr.right == null) {
					return step;
				}
				if (curr.left != null) {
					queue.offer(curr.left);
				}
				if (curr.right != null) {
					queue.offer(curr.right);
				}
			}
			step++;
		}
		return step;
	}

}

class TreeNode {
	TreeNode left;
	TreeNode right;
}
