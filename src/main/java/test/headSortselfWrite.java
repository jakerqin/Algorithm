package test;

public class headSortselfWrite {


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void AdjectHeap(int[] arr, int index, int length) {
        int max = index;
        int left = index * 2 +1;
        int right = index * 2 +2;

        if (left < length && arr[left] > arr[max]) {
            max = left;
        }
        if (right < length && arr[right] > arr[max]) {
            max = right;
        }
        if (max != index) {
            swap(arr,index,max);
            AdjectHeap(arr,max,length);
        }

    }

    public static void heapSort(int arr[]) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            AdjectHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr,i,0);
            AdjectHeap(arr,0,i);

        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,3,1,8,9,5};
        heapSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
