package top.ks.learn.算法.数组专项.i使数组唯一的最小增量_945;

import java.util.Arrays;

/**
 * <b>类名称:</b>Solution<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b>
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * <p>
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * <p>
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * <p>
 * <p>
 * <br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2021-03-02
 */
public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :计数排序 O(N)O(N)
     * @author : birjc
     * @CreateDate : 2021-03-02 01:03
     */
    public static int minIncrementForUnique(int[] A) {
        // counter数组统计每个数字的个数。
        //（这里为了防止下面遍历counter的时候每次都走到40000，所以设置了一个max，这个数据量不设也行，再额外设置min也行）
        int[] counter = new int[40001];
        int max = -1;
        for (int num : A) {
            counter[num]++;
            max = Math.max(max, num);
        }

        // 遍历counter数组，若当前数字的个数cnt大于1个，则只留下1个，其他的cnt-1个后移
        int move = 0;
        for (int num = 0; num <= max; num++) {
            if (counter[num] > 1) {
                int d = counter[num] - 1;
                move += d;
                counter[num + 1] += d;
            }
        }
        // 最后, counter[max+1]里可能会有从counter[max]后移过来的，counter[max+1]里只留下1个，其它的d个后移。
        // 设 max+1 = x，那么后面的d个数就是[x+1,x+2,x+3,...,x+d],
        // 因此操作次数是[1,2,3,...,d],用求和公式求和。
        int d = counter[max + 1] - 1;
        move += (1 + d) * d / 2;
        return move;
    }

    /**
     * @Method :
     * @Description :线性探测法 O(N)O(N) （含路径压缩）
     * @param :
     * @return :
     * @author : birjc
     * @CreateDate : 2021-03-02 01:03
     */
    static int[] pos = new int[80000];

    public static int minIncrementForUnique1(int[] A) {
        Arrays.fill(pos, -1); // -1表示空位
        int move = 0;
        // 遍历每个数字a对其寻地址得到位置b, b比a的增量就是操作数。
        for (int a : A) {
            int b = findPos(a);
            move += b - a;
        }
        return move;
    }

    // 线性探测寻址（含路径压缩）
    private static int findPos(int a) {
        int b = pos[a];
        // 如果a对应的位置pos[a]是空位，直接放入即可。
        if (b == -1) {
            pos[a] = a;
            return a;
        }
        // 否则向后寻址
        // 因为pos[a]中标记了上次寻址得到的空位，因此从pos[a]+1开始寻址就行了（不需要从a+1开始）。
        b = findPos(b + 1);
        pos[a] = b; // 寻址后的新空位要重新赋值给pos[a]哦，路径压缩就是体现在这里。
        return b;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 2, 1, 7};
        System.out.println(minIncrementForUnique(a));
    }
}
