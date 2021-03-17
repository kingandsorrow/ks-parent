package top.ks.learn.算法.数字专项.回文数字;

public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-03-07 21:36
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int rev = 0;
        while (rev < x) {
            rev = x % 10 + rev * 10;
            x /= 10;
        }
        return (rev == x || rev / 10 == x);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(120000021));
    }
}
