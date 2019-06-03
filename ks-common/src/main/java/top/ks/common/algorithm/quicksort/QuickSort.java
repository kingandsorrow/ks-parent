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
        if (L >= R)
            return -1;
        int i = L;
        int j = R;
        int temp = p[L];
        while (i < j) {
            while (i < j && p[j] < temp) j--;
            if (i < j) {
                p[i] = p[j];
                i++;
            }
            while (i < j && p[i] > temp) i++;
            if (i < j) {
                p[j] = p[i];
                j--;
            }
        }
        p[i] = temp;
        //去掉以下两句注释，再将return注释掉，并且将返回值改为void，
        //就是一个完整的快速排序
        //quickSort(p ,L,i-1);
        //quickSort(p ,i+1,R);
        return i;
    }

    //快速排序标准写法
    public static void quickSortArr(int[] p, int L, int R) {
        if (L >= R)
            return;
        int i = L;
        int j = R;
        int temp = p[L];
        while (i < j) {
            while (i < j && p[j] < temp) {
                j--;
            }
            if (i < j) {
                p[i] = p[j];
                i++;
            }
            while (i < j && p[i] > temp) {
                i++;
            }
            if (i < j) {
                p[j] = p[i];
                j--;
            }
        }
        p[i] = temp;
        //去掉以下两句注释，再将return注释掉，并且将返回值改为void，
        //就是一个完整的快速排序
        quickSortArr(p, L, i - 1);
        quickSortArr(p, i + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, 7, 3, 2, 9, 1, 10, 23, 28, 99, 33, 199, 65, 16, 36, 111, 17, 27, 83, 21, 5, 8, 9, 11, 19};
       /* int[] arr1 = new int[]{3, 9, 7, 5, 10};
        System.out.println(findKth(arr1, 0, arr1.length - 1, 3));
*/
        quickSortArr(arr, 0, arr.length - 1);

        System.out.println(JSON.toJSONString(arr));
    }
}
