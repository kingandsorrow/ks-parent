package top.ks.learn.算法.数组.b快速排序;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Integer[] arrs = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        //bubbleSort1(arrs);
        //quickSort1(arrs, 0, arrs.length - 1);
        quickSort2(arrs, 0, arrs.length - 1);
        System.out.println(arrs);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :非递归方式
     * @author : birjc
     * @CreateDate : 2021-02-13 11:32
     */
    private static void quickSort2(Integer[] arrs, int startIndex, int endIndex) {

        Stack<Map<String, Integer>> stack = new Stack<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", startIndex);
        map.put("endIndex", endIndex);
        stack.push(map);
        while (!stack.isEmpty()) {
            Map<String, Integer> params = stack.pop();
            int pIndex = partition(arrs, params.get("startIndex"), params.get("endIndex"));
            if (params.get("startIndex") < pIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", params.get("startIndex"));
                leftParam.put("endIndex", pIndex - 1);
                stack.push(leftParam);
            }
            if (params.get("endIndex") > pIndex + 1) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pIndex + 1);
                rightParam.put("endIndex", params.get("endIndex"));
                stack.push(rightParam);
            }
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 快速排序-递归
     * @author : birjc
     * @CreateDate : 2021-02-13 11:31
     */
    public static void quickSort1(Integer[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pIndex = partition(arr, startIndex, endIndex);
        quickSort1(arr, startIndex, pIndex - 1);
        quickSort1(arr, pIndex + 1, endIndex);
    }

    // 获取中间位置
    private static int partition(Integer[] arr, int startIndex, int endIndex) {
        int left = startIndex;
        int parr = arr[startIndex];
        int right = endIndex;
        while (left != right) {

            while (left < right && arr[right] > parr) {
                right--;
            }
            while (left < right && arr[left] <= parr) {
                left++;
            }
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        int temp = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = temp;
        return left;
    }
}
