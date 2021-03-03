package top.ks.learn.算法.数组.g打乱数组;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Random;

public class Solution {

    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Solution(int[] nums) {
        array = nums;
        original = Arrays.copyOf(nums, nums.length);
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 9};
        Solution solution = new Solution(arr);
        System.out.println(JSON.toJSONString(solution.shuffle()));
        System.out.println(JSON.toJSONString(solution.reset()));
    }
}
