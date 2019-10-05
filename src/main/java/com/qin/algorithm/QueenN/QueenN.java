package com.qin.algorithm.QueenN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** 此类时写leetcod所留，所以返回值时List<List<String>>
 */
public class QueenN {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new LinkedList<List<String>>();
        int[] arr = new int[n];

        check(0,n,arr, list);

        return list;
    }


    private void check(int curr,int n,int[] arr, List<List<String>> list){
        if(curr == n){
            list.add(new ArrayList<>(getList(arr)));
            return ;
        }

        for (int i = 0; i < n; i++) {
            arr[curr] = i;
            if (judge(curr, arr)) {
                check(curr + 1, n, arr, list);
            }
        }
    }


    // 判断是否冲突
    private boolean judge(int curr,int[] arr){

        for(int i = 0 ;i<curr;i++){
            // 先判断是否在同一列，然后判断是否在斜线上
            if(arr[i] == arr[curr] || Math.abs(curr-i) == Math.abs(arr[curr] - arr[i])){
                return false;
            }
        }
        return true;
    }

    // 转化为string
    private List<String> getList(int[] arr) {
        List<String> list = new ArrayList<>();
        for (int i : arr) {
            char[] ch = new char[arr.length];
            Arrays.fill(ch,'.');
            ch[i] = 'Q';
            list.add(String.valueOf(ch));
        }

        return list;
    }

    public static void main(String[] args) {
        QueenN n = new QueenN();
        List<List<String>> lists = n.solveNQueens(1);
        for (List<String> str : lists) {
            System.out.println(str.toString());
        }
    }

}
