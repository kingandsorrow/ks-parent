package top.ks.common.algorithm.selectsort;

import com.alibaba.fastjson.JSON;

public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 9, 1};
        selectSort(arr);
        System.out.println(JSON.toJSON(arr));
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
