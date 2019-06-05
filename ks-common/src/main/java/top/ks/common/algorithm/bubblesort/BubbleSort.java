package top.ks.common.algorithm.bubblesort;

import com.alibaba.fastjson.JSON;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 8, 9};
        bubbleSort(arr);
        System.out.println(JSON.toJSON(arr));
    }

    /**
     * 冒泡排序从小到大
     * 把最大的冒上去（N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次）
     * 最好的时间复杂度()数组本来就是顺序排的 即为O(n)
     * 最差的时间复杂度 数组正好是逆序的 即为O(n²)
     * (n - 1) + (n - 2) + (n - 3) + ... + 0
     * = (0 + n - 1) * n / 2
     * = O (n ^2)
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                System.out.println("最优情况,不要排序");
                break;
            }
        }
    }

}
