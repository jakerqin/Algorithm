package com.qin.Search;

/**
 * 二分查找法
 */
public class binarySearch {
    public static int rank(int key ,int[] a){
        return rank(key, a, 0, a.length);
    }

    public static int rank(int key,int[] a, int lo, int hi){
        if (lo > hi) return -1;
        int mod = lo + (hi - lo) / 2;
        if (key < a[mod]) return rank(key, a, lo, mod - 1);
        else if (key > a[mod]) return rank(key, a, mod + 1, hi);
        else return mod;
    }

    /**
     * 二分查找法的递归实现
     * @param arr
     * @param target
     * @return
     */
    public int binaryNoRecursion(int[] arr, int target) {
        int low = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (low < right) {
            mid =(low+right) /2;
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return -1;
    }

}
