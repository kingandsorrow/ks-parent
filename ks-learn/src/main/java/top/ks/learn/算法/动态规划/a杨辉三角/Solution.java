package top.ks.learn.算法.动态规划.a杨辉三角;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 输出一个杨辉三角的数组
     * @author : birjc
     * @CreateDate : 2021-03-01 00:24
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            if (i == 0) {
                temp.add(1);
            } else {
                List<Integer> list = ans.get(i - 1);
                int size = list.size() + 1;
                for (int j = 0; j < size; j++) {
                    if (j == 0 || j == size - 1) {
                        temp.add(1);
                    } else {
                        temp.add(list.get(j - 1) + list.get(j));
                    }
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 输出杨辉三角的第几行
     * @author : birjc
     * @CreateDate : 2021-03-01 00:25
     */

    public static List<Integer> getRow(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        Arrays.fill(dp, 1);
        for (int i = 2; i < dp.length; i++) {
            for (int j = i - 1; j > 0; j--)
                dp[j] = dp[j] + dp[j - 1];
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }


    public static void main(String[] args) {
        //System.out.println(generate(5));
        System.out.println(getRow(5));
    }
}
