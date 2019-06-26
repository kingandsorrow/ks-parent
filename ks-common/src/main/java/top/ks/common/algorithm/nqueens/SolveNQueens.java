package top.ks.common.algorithm.nqueens;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

public class SolveNQueens {

    public static List<List<String>> solveNQueens(int n) {
        // 下标代表行，值代表列。如result[0] = 3 表示第1行的Q在第3列
        int[] result = new int[n];
        List<List<String>> resultList = new LinkedList<>();
        dfs(resultList, result, 0, n);
        return resultList;
    }

    private static void dfs(List<List<String>> resultList, int[] result, int row, int n) {
        if (row == n) {
            List<String> list = new LinkedList<>();
            for (int x = 0; x < n; ++x) {
                StringBuilder sb = new StringBuilder();
                for (int y = 0; y < n; ++y)
                    sb.append(result[x] == y ? "Q" : ".");
                list.add(sb.toString());
            }
            resultList.add(list);
            return;
        }
        for (int column = 0; column < n; ++column) {
            boolean isValid = true;
            result[row] = column;
            /*
             * 逐行往下考察每一行。同列，result[i] == column
             * 同对角线，row - i == Math.abs(result[i] - column)
             */
            for (int i = row - 1; i >= 0; --i) {
                if (result[i] == column || row - i == Math.abs(result[i] - column)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                dfs(resultList, result, row + 1, n);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);
        System.out.println(JSON.toJSON(lists));
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
    }
}
