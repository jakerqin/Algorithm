package com.qin.labuladong.backTracking;


import java.util.ArrayList;
import java.util.List;

/**
 *@DESCRIPTION N皇后问题
 *@Author qinlianji
 *@Date 2022/12/19
 */
public class NQueues {

	public static void main(String[] args) {
		solveNQueue(4);
	}

	public static List<List<String>> solveNQueue(int n) {
		List<List<String>> result = new ArrayList<>();
		String[][] board = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = ".";
			}
		}
		backTrack(result, board, 0);
		return result;
	}

	public static void backTrack(List<List<String>> result, String[][] board, int row) {
		if (row == board.length) {
			result.add(array2List(board));
			return ;
		}
		int n = board.length;
		for (int col = 0; col < n; col++) {
			// 不符合条件的跳过
			if (!isValid(board, row, col)) {
				continue;
			}
			// 放置一个皇后
			board[row][col] = "Q";
			// 进入下一行的决策
			backTrack(result, board, row + 1);
			// 撤销
			board[row][col] = ".";
		}
	}

	private static boolean isValid(String[][] board, int row, int col) {
		// 检测列中是否有冲突的皇后
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col].equals("Q")) {
				return false;
			}
		}
		// 检测右后是否有皇后冲突
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j].equals("Q")) {
				return false;
			}
		}
		// 检测左后是否有皇后冲突
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j].equals("Q")) {
				return false;
			}
		}
		return true;
	}

	private static List<String> array2List(String[][] board) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < board.length; j++) {
				sb.append(board[i][j]);
			}
			result.add(sb.toString());
		}
		return result;
	}
}
