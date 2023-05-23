package com.qin.LeetCode;

/**
 * 求最长回文子串
 * @Author qinlianji
 * @Date 2023/4/26 19:42
 **/
public class Problem5 {

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        //中心扩展算法
        //从每一个位置mid出发，向两边扩散
        int len = 1;
        int maxLeft = 0;//记录最长回文子串的起点
        int maxlen = 0;//记录最长回文子串的长度
        char[] chars = s.toCharArray();
        for (int mid = 0; mid < s.length(); ++mid) {
            int left = mid - 1;
            int right = mid + 1;
            //向左侧扩展，直到超过边界或遇到与中心字符不等跳出while循环
            while (left >= 0 && chars[left] == chars[mid]) {
                left--;//left字符与mid字符一致，继续左移
                len++;//与mid字符一致，回文长度+1
            }
            //向右侧扩展，直到超过边界或遇到与中心字符不等跳出while循环
            while (right <= s.length() - 1 && chars[right] == chars[mid]) {
                right++;//right字符与mid字符一致，继续左移
                len++;//与mid字符一致，回文长度+1
            }
            //同时向左右两侧扩展
            while (left >= 0 && right <= s.length() - 1 && chars[left] == chars[right]) {
                //注意此处，在最后一次循环中，即最长回文子串索引为：i~j，此时的left=i-1，right=j+1
                left--;
                right++;
                len += 2;
            }
            if (len > maxlen) {
                maxLeft = left;
                maxlen = len;
            }
            len = 1;
        }
        //返回子串,从pos位开始，长度为len
        return s.substring(maxLeft + 1, maxLeft + 1 + maxlen);
    }
}

