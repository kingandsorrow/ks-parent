package top.ks.common.算法;

public class array {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 0, 2, 5};
        int[] b = new int[1];
        System.out.println(duplicate(arr, arr.length, b));
        System.out.println();
    }

    public static boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
