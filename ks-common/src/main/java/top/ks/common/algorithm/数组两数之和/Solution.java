package top.ks.common.algorithm.数组两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>类名称:</b>Solution<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2020-12-09
 */
public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * @author : birjc
     * @CreateDate : 2020-12-09 22:09
     */
    public int[] twoSums(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;

    }
}
