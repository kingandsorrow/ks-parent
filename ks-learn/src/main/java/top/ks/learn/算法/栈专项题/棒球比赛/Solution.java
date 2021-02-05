package top.ks.learn.算法.栈专项题.棒球比赛;

import java.util.Stack;

public class Solution {
    /*
    * 输入：ops = ["5","-2","4","C","D","9","+","+"]
输出：27
解释：
"5" - 记录加 5 ，记录现在是 [5]
"-2" - 记录加 -2 ，记录现在是 [5, -2]
"4" - 记录加 4 ，记录现在是 [5, -2, 4]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
"D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
"9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
"+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
"+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
    *
    *
    *
    * */
    public int calPoints(String[] ops) {
        // 创建一个空栈
        Stack<Integer> stack = new Stack<>();
        int sum = 0, top = 0, tmp = 0;
        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "+":
                    // 前两次得分的总和
                    top = stack.pop();
                    tmp = top + stack.peek();
                    stack.push(top);
                    sum += stack.push(tmp);
                    break;
                case "D":
                    // 前一次得分的两倍
                    sum += stack.push(2 * stack.peek());
                    break;
                case "C":
                    // 前一次得分无效
                    sum -= stack.pop();
                    break;
                default:
                    // 加数字
                    stack.push(Integer.parseInt(ops[i]));
                    sum += Integer.parseInt(ops[i]);
            }
        }
        return sum;
    }

}
