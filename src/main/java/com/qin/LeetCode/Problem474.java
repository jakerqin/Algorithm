package com.qin.LeetCode;

/**
 * 0和1背包问题
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 示例：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *
 * @Author qinlianji
 * @Date 2023/5/23 13:40
 **/
public class Problem474 {

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int maxForm = findMaxForm(strs, 5, 3);
        System.out.println(maxForm);
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] cnt = new int[len][2];
        //  提取出数组中每个字符串元素有几个0，几个1
        for (int i = 0; i < len; i++) {
            char[] charArray = strs[i].toCharArray();
            int zero = 0, one = 0;
            for (int j = 0; j < charArray.length; j++) {
                if (charArray[j] == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            cnt[i] = new int[]{zero, one};
        }

        // 创建带有元素数量、0最多的数量、1最多数量的三维数组
        int[][][] f = new int[len + 1][m + 1][n + 1];
        for (int k = 1; k <= len; k++) {
            int zero = cnt[k - 1][0];
            int one = cnt[k - 1][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // （k-1）个元素时最大子集的数量
                    int a = f[k - 1][i][j];
                    // 加上当前元素看看最大子集是多少（考虑最多m个0，n个1）
                    int curr = (i >= zero && j >= one) ? f[k - 1][i - zero][j - one] + 1 : 0;
                    f[k][i][j] = Math.max(a, curr);
                }
            }
        }
        return f[len][m][n];
    }
}
