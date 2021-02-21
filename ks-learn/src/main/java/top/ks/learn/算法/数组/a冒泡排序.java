package top.ks.learn.算法.数组;

public class a冒泡排序 {
    //6 1 2 7 9 3 4 5 10 8
    public static void main(String[] args) {
        Integer[] arrs = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        //bubbleSort1(arrs);
        bubbleSort2(arrs);
        System.out.println(arrs);
    }

    private static void bubbleSort1(Integer[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 1; j < arrs.length - i; j++) {
                if (arrs[j] < arrs[j - 1]) {
                    int temp = arrs[j - 1];
                    arrs[j - 1] = arrs[j];
                    arrs[j] = temp;
                }
            }
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-02-18 12:08
     */
    public static void bubbleSort2(Integer[] arrs) {
        boolean flag = true;
        int len = arrs.length;
        int k = 0;
        while (flag) {
            flag = false;
            for (int i = 1; i < len - k; i++) {
                if (arrs[i - 1] > arrs[i]) {
                    int temp = arrs[i];
                    arrs[i] = arrs[i - 1];
                    arrs[i - 1] = temp;
                    flag = true;
                }
            }
            k++;
        }
    }
}
