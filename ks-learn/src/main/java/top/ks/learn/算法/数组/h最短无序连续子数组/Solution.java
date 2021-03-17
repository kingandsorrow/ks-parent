package top.ks.learn.算法.数组.h最短无序连续子数组;

/**
 * <b>类名称:</b>Solution<br/>
 * <b>类注释:</b><br/>
 * 示例 1：
 * <p>
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 *
 *
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2021-03-04
 */
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
