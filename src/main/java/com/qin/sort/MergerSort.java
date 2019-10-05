package com.qin.sort;

import java.util.Random;

/**
 * 归并排序：
 *  需要额外的空间，空间换时间
 */
public class MergerSort {

    static Random r = new Random();

    private static void mergeArray(int[] arr, int start, int mid, int end, int[] temp) {
        int i = start;//第一个有序序列的开始下标
        int j = mid +1;//第二个有序序列的开始下标

        int length = 0;

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[length++] = arr[i];
                i++;
            } else {
                temp[length++] = arr[j];
                j++;
            }
        }
        //可能有一边没走完
        while (i <= mid) {
            temp[length++] = arr[i++];
        }
        while (j <= end) {
            temp[length++] = arr[j++];
        }
        //覆盖原来位置的无序数组
        for (int k = 0;k<length;k++) {
            arr[start + k] = temp[k];
        }
    }

    //归并排序
    public static void mergersort(int[] arr,int start, int end,int[] temp ) {
        //递归结束条件
        if (start == end) {
            return ;
        }
        //从中间位置拆分
        int mid = (start+end) / 2;
        mergersort(arr, start, mid, temp);
        mergersort(arr, mid + 1, end, temp);
        //合并两个有序数组
        mergeArray(arr, start, mid, end, temp);

    }


    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0;i<arr.length;i++) {
            arr[i] = r.nextInt(100);
        }
        int[] temp = new int[arr.length];
        //打印
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();

        long start = System.currentTimeMillis();
        mergersort(arr,0,arr.length-1,temp);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        for (int i : arr) {
            System.out.print(i+" ");
        }

    }
}
