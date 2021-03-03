package top.ks.learn.算法.数组.d主要元素;

/**
 * <b>类名称:</b>Solution<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b>
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * <br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2021-02-27
 */
public class Solution {

    public static int majorityElement(int[] nums) {
        // 投票算法
        int temp = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == temp) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                temp = nums[i];
                count = 1;
            }
        }
        int t = (nums.length / 2);
        // 验证是否满足要求
        count = 0;
        for (int num : nums) {
            if (num == temp) count++;
            if (count >t) return temp;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 5, 2, 2, 2, 2, 4, 5};
        int i = majorityElement(nums);
        System.out.println(i);
        System.out.println(11 / 2);
    }
}
