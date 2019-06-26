package top.ks.common.algorithm.binarysearch;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 1;
        int index = binarySearch(arr, 0, arr.length - 1, target);
        System.out.println(index);
        String str = "";
        str.intern();
    }

    private static int binarySearch(int[] arr, int i, int j, int target) {
        while (i < j) {
            // int m = (i+j)/2 会导致溢出
            int m = i + (j - i) / 2;// 这样计算出来的mid，一定大于low，小于high

            if (target < arr[m]) {
                j = m;
            } else if (target > arr[m]) {
                i = m;
            } else {
                return m;
            }
        }
        return -1;

    }
}
