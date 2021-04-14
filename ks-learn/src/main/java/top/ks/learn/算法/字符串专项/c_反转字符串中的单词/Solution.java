package top.ks.learn.算法.字符串专项.c_反转字符串中的单词;

public class Solution {
    public static String reverseWords(String s) {
        String[] arr = s.split(" ");
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringbuilder.append(reverse(arr[i]));
            if (i != arr.length - 1) {
                stringbuilder.append(" ");
            }
        }
        return stringbuilder.toString();
    }


    public static String reverse(String s) {
        char[] ss = s.toCharArray();
        int len = ss.length;
        char[] ns = new char[len];

        for (int i = ss.length - 1; i > 0; i--) {
            ns[len - i - 1] = ss[i];
        }
        return new String(ns);
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }
}
