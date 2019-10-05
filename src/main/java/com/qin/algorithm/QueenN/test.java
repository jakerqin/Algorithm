package com.qin.algorithm.QueenN;

public class test {
    private int sum = 0;
    public int totalNQueens(int n) {
        int[] arr = new int[n];
        check(arr, 0, n);
        return sum;
    }

    private void check (int[] arr,int curr, int n){
        if(curr == n){
            sum++;
            return ;
        }

        for(int i = 0;i < n;i++){
            arr[curr] = i;
            if(judge(curr,arr)){
                check(arr,curr+1,n);
            }
        }
    }

    private boolean judge(int curr,int[] arr){
        for(int i= 0; i < curr;i++ ){
            if(arr[curr] == arr[i] || Math.abs(curr - i) == Math.abs(arr[curr] - arr[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        test t = new test();
        System.out.println(t.totalNQueens(1));
    }
}
