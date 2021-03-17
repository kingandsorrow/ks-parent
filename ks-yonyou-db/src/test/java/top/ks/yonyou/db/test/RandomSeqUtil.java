package top.ks.yonyou.db.test;

import java.security.SecureRandom;

/**
 * 内部码随机生成序列算法
 *
 * @author songhx1
 */
public class RandomSeqUtil {
    private static final char[] chars = new char[36];
    private static final SecureRandom r = new SecureRandom();

    static {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++, i++) {
            chars[i] = c;
        }
        for (char j = '0'; j <= '9'; j++, i++) {
            chars[i] = j;
        }
    }

    public static String getRandomSeq() {
        return getRandomSeq(4);
    }

    public static String getRandomSeq(int length) {

        long ra = r.nextLong();
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < length; i++) {
            long x = ra & 63;
            x = x < 36 ? x : 64 - x;
            code.append(chars[(int) x]);
            ra = ra >> 6;
        }

        return code.toString();
    }

}
