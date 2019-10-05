package com.qin.sort;

import java.util.Random;

/**
 * 堆排序（完全二叉树：最后一层节点尽力靠左）：
 *      节点数/2 等于最后一个非叶子节点，然后从非叶子节点递减排序
 *
 */
public class HeapSort {
    static Random r = new Random();

    public static void swap(int[] arr, int i, int j) {
        int tem = 0;
        tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }
    /**
     * 大顶堆，从小到大，因为最大的顶换到最后了
     */
    public static void heapSort(int[] arr, int len) {
        //初始化堆
        for (int i = len /2-1;i>=0;i--) {
            headAdjust(arr, i, len);
        }
        //交换堆顶和最后一个元素
        for (int i = len -1; i>0;i--) {
            swap(arr,0,i);
            headAdjust(arr,0,i);
        }

    }

    public static void headAdjust(int[] arr, int index, int len) {
        //保存当前节点的索引
        int max = index;
        //保存左右节点的索引
        int lchild = index *2 + 1;
        int rchild = index *2 + 2;

        if (lchild < len && arr[lchild] > arr[max]) {
            max = lchild;
        }
        if (rchild < len && arr[rchild] > arr[max]) {
            max = rchild;
        }
        if (max != index) {
            swap(arr, index, max);
            headAdjust(arr, max, len);
        }
    }



    public static void main(String[] args) {
        int[] arr = {4,2,8,0,5,7,1,3,9};

        //打印
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();

        long start = System.currentTimeMillis();
        heapSort(arr,arr.length);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        for (int i : arr) {
            System.out.print(i+" ");
        }

    }


}
