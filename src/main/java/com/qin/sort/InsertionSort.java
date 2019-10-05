package com.qin.sort;

import java.util.Random;

/**
 * 插入排序：将无序序列插入到有序序列中
 * 插入排序在什么情况下效率高？
 *      1.在基本有序的情况
 *      2.数据序列比较少
 * 希尔排序就是利用这两点
 */
public class InsertionSort {

    static Random r = new Random();

    public static void swap(int[] arr, int i, int j) {
        int tem = 0;
        tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {
        int[] arr = new int[20000];
        for (int i = 0;i<arr.length;i++) {
            arr[i] = r.nextInt(100);
        }

        int j = 0;
        long start = System.currentTimeMillis();
        //here
        for (int i = 1;i<arr.length;i++) {
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i];
                for (j = i-1; j>=0 && temp < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j+1] =temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }

    }
}
