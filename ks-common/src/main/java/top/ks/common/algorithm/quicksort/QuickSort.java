package top.ks.common.algorithm.quicksort;

import com.alibaba.fastjson.JSON;

public class QuickSort {

    //给定一个数组 查询top K问题

    public static int findKth(int[] p, int L, int R, int k) {
        if (L > R || k < 1)//检查输入参数是否合法
            return -1;
        if (L == R)//如果L等于R说明已找到，直接返回
            return p[R];
        int temp = quickSort(p, L, R);//进行一次快排，返回下标
        if (k + L == temp + 1)//如果k+L等于返回的下标加１（L不一定从０开始）
            return p[temp];//则直接返回
        if (k + L < temp + 1)//如果k+L小于返回的下标加１
            return findKth(p, L, temp - 1, k);//在temp的左边查找第k大数
        else//否则，在temp的右边部分查找第k-(temp-L+1)大数。这里，右边的第
            //k-(temp-L+1)大数就是整个数组的第k大数
            return findKth(p, temp + 1, R, k - (temp - L + 1));
    }


    /*
      * 一次快速排序
      ＊以p[L]为比较对象，比p[L]大或等于的在其左边，否则在其右边
      */
    public static int quickSort(int[] p, int L, int R) {
        return 0;
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 快速排序
     * @author : birjc
     * @CreateDate : 2020-07-16 00:16
     */
    public static void quickSortArr(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = getMiddle(arr, low, high);
        quickSortArr(arr, low, mid - 1);
        quickSortArr(arr, mid + 1, high);
    }

    private static int getMiddle(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 2, 9, 10, 8, 7, 5, 1};
        quickSortArr(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSON(arr));
    }
}
