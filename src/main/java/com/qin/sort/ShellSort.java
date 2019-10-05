package com.qin.sort;

import java.util.Random;

/**
 * 希尔排序：
 *      先分组，然后对每组数据进行插入排序
 *   但是按哪个增量要分组一直没有定论。经过分析，[increment/3+1]是不错的一个选择
 *   shell排序和插入【排序很相似，就是多了一个increment
 */
public class ShellSort {

    static Random r = new Random();

    public static void swap(int[] arr, int i, int j) {
        int tem = 0;
        i = i^ j;
        j = j ^ i;
        i = i^ j;
    }

    public static void main(String[] args) {
        int[] arr = new int[80];
        for (int i = 0;i<arr.length;i++) {
            arr[i] = r.nextInt(1000);
        }
        int j,k;
        int increment = arr.length;
        long start = System.currentTimeMillis();
        //here
        do{
            increment = increment /3 +1 ;
            for (int i = 0;i < increment;i++) {
                for (j = i+increment ;j<arr.length;j+=increment) {
                    if (arr[j] < arr[j - increment]) {
                        int temp = arr[j];
                        for (k = j - increment;k>=0 && temp <arr[k];k-=increment) {
                            arr[k + increment] = arr[k];
                        }
                        arr[k+increment] = temp;
                    }
                }
            }
        }while (increment > 1);

        long end = System.currentTimeMillis();
        System.out.println(end-start);
        for (int i : arr) {
            System.out.print(i+" ");
        }

    }
}
