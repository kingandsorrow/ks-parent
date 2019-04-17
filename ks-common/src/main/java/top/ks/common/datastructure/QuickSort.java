package top.ks.common.datastructure;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {8, 7, 3, 2, 9, 0, 6, 5, 1, 4};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 快速排序 递归调用
     *
     * @param a
     */
    private static void quickSort(int[] a) {
        if (a != null && a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }

    /**
     * 具体递归方法
     * @param a
     * @param low
     * @param high
     */
    private static void quickSort(int[] a, int low, int high) {
        //1. 递归的出口
        if (low > high) {
            return;
        }
        // 2.替换
        int i = low;
        int j = high;
        // 3.找一个基础值
        int index = a[i];
        while (i < j) {
            while (i < j && a[j] >= index) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] < index) {
                i++;
            }
            if (i < j) {
                a[j--] = a[i];
            }
        }
        a[i] = index;
        quickSort(a, low, i - 1);
        quickSort(a, i + 1, high);

    }
}
