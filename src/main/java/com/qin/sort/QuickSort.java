package com.qin.sort;

import java.util.Random;

/**
 * 快速排序：
 *      分治发  + 挖坑填空
 *      大问题分解成各个小问题求解
 *  例如：从小到大排序
 *      把第一个数作为基准数，
 *      从右边向左开始找小于基准数的位置
 */
public class QuickSort {

    static Random r = new Random();

    public static void quick(int[] arr, int start, int end) {
        if (start >= end) {
            return ;
        }
        int temp = arr[start];
        int i = start;
        int j = end;
        while (i < j) {

            while (i < j && arr[j] >= temp) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[i] < temp) {
                i++;
            }

            if(i<j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = temp;
        quick(arr, start, i - 1);
        quick(arr,i+1 ,end);
    }

    public static void swap(int[] arr, int i, int j) {
        int tem = 0;
        tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {
        int[] arr = new int[800000];
        for (int i = 0;i<arr.length;i++) {
            arr[i] = r.nextInt(1000000);
        }
        long start = System.currentTimeMillis();
        quick(arr,0,(arr.length-1));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }

    }
}
