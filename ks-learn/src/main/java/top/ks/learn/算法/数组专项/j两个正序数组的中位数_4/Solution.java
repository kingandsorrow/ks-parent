package top.ks.learn.算法.数组专项.j两个正序数组的中位数_4;

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
 * Copyright KS 2021-03-06
 */
public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 暴力合并数组，寻找中位数
     * @author : birjc
     * @CreateDate : 2021-03-06 10:02
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {

                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 转换为寻找两个数组第k大的数据
     * @author : birjc
     * @CreateDate : 2021-03-06 10:03
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;
        return (findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, left) + findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, right)) * 0.5;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-03-06 10:18
     */
    private static int findKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int l1 = end1 - start1 + 1;
        int l2 = end2 - start2 + 1;
        if (l1 > l2) {
            return findKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (l1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(l1, k / 2) - 1;
        int j = start2 + Math.min(l2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return findKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return findKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        int[] arrs1 = {1, 3};
        int[] arrs2 = {2};
        System.out.println(findMedianSortedArrays2(arrs1, arrs2));
    }


}
