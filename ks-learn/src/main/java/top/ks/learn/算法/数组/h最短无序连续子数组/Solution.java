package top.ks.learn.算法.数组.h最短无序连续子数组;

public class Solution {
    public static int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        if (len <= 1) return 0;
        int high = 0, low = len - 1, max = nums[0], min = nums[len - 1];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[len - 1 - i]);
            if (nums[i] < max) high = i;
            if (nums[len - 1 - i] > min) low = len - 1 - i;
        }
        return high > low ? high - low + 1 : 0;
    }


    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 4, 9, 15};
        System.out.println(findUnsortedSubarray(arr));
    }
}
