package com.qin.sort;

import java.util.Random;

/**
 * 冒泡排序的测试
 * 最好的情况下时间复杂度是O(n)  最坏的情况下是O(n^2)
 */
public class BubbleSort {

    static Random r = new Random();

    public static void swap(int[] arr, int i, int j) {
        int tem = 0;
        tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {

        int[] arr = new int[20];
        for (int i = 0;i<arr.length;i++) {
            arr[i] = r.nextInt(100);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i<arr.length-1;i++) {
            for(int j = 0;j<arr.length-i-1;j++) {
                if (arr[j] < arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        for (int i : arr) {
            System.out.print(i + " ");
        }


    }
}
