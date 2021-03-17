package top.ks.learn.算法.动态规划专项.c_连续子数组的最大和;

public class Solution {

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int low = 0;//用于记录dp[i-1]的值，对于dp[0]而言，其前面的dp[-1]=0
        int fast = nums[0];//用于记录dp[i]的值
        for (int num : nums) {
            fast = num;
            if (low > 0) {
                fast += low;
            }
            if (fast > max) {
                max = fast;
            }
            low = fast;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-1,-1, -2, -3, -4, -1}));
    }
}
