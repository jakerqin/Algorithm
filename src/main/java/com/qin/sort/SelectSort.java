package com.qin.sort;

import java.util.Random;

/**
 * 选择排序，依旧是两个for循环，时间复杂度跟冒泡一样，只是减少了数组交换的次数
 * 还是比冒泡排序快一点
 */
public class SelectSort {
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

        long start = System.currentTimeMillis();
        for (int i = 0;i<arr.length;i++) {
            int min = i;
            for (int j = i+1;j<arr.length;j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr,min,i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }

    }
}
