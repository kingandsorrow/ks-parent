package top.ks.learn.算法.数组专项.k_数组132模式_456;

import java.util.Stack;

public class Solution {
    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        int last = Integer.MIN_VALUE; // 132中的2
        Stack<Integer> stack = new Stack<>();// 用来存储132中的3
        if (nums.length < 3)
            return false;
        for (int i = n - 1; i >= 0; i--) {

            if (nums[i] < last) // 若出现132中的1则返回正确值
                return true;

            // 若当前值大于或等于2则更新2（2为栈中小于当前值的最大元素）
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                last = stack.pop();
            }

            // 将当前值压入栈中
            stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{3, 1, 4, 2}));
        //System.out.println(find132pattern(new int[]{-1, 3, 2, 0}));
    }
}

