package top.ks.common.algorithm.subject1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>类名称:</b>Subject$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/3<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright 西安创意 2018/9/3
 */
public class Solution {
    /**
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     * <p>
     * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。sh
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */

    public static void main(String[] args) {
        String str = "hello";
        int[] nums = {3, 5, 7, 6, 2, 3};
        int target = 10;
        System.out.println(Arrays.toString(getTwoSum(nums, target)));
    }

    public static int[] getTwoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)) {
                return new int[]{i, map.get(result)};
            }
            map.put(nums[i], i);
        }
        return null;

    }

}
