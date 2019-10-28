package top.ks.common.algorithm.arrayrepeat;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

//查询数组重复两次的元素
public class ArrayRepeat {

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 2};
        System.out.println(findRepeatArray(a));

        System.out.println(Integer.reverse(3));
    }

    /**
     * 查询数组重复两次的元素
     * 给定一个包含 n + 1 个整数的数组 nums，
     * 其数字都在 1 到 n 之间（包括 1 和 n），
     * 可知至少存在一个重复的整数。假设只有一个重复的整数，
     * 找出这个重复的数。
     */
    private static int findRepeatArray(int[] nums) {
        int length = nums.length;
        if (length > 1) {
            // 找到快慢指针相遇的地方
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (fast != slow) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            // 用一个新指针从头开始，直到和慢指针相遇
            fast = 0;
            while (fast != slow) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return fast;
        }
        return -1;

    }

}
