package top.ks.learn.算法.数组专项.b快速排序;

import com.alibaba.fastjson.JSON;

public class Solution1 {

    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quickSort(int[] nums, int low, int high) {
        while (low >= high) {
            return;
        }
        int p = partion(nums, low, high);
        quickSort(nums, low, p - 1);
        quickSort(nums, p + 1, high);
    }

    public static int partion(int[] nums, int start, int end) {
        int index = nums[start];
        int low = start;
        int high = end;
        while (low != high) {
            while (low < high && nums[high] > index) {
                high--;
            }
            while (low < high && nums[low] <= index) {
                low++;
            }

            if (low < high) {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
            }
        }
        int temp = nums[low];
        nums[low] = nums[start];
        nums[start] = temp;
        return low;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(sortArray(new int[]{5, 2, 3, 1})));
    }
}
