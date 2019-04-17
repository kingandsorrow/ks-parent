package top.ks.common.algorithm.subject3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>类名称:</b>Solution$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/4<br/>
 * <b>修改备注:</b><br/>
 * Copyright 西安创意 2018/9/4
 */
public class Solution {
    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 无重复字符的最长子串是 "b"，其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 无重复字符的最长子串是 "wke"，其长度为 3。
     * 请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        List result = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            List list = new ArrayList();
            char a = s.charAt(i);
            list.add(a);
            for (int j = i + 1; j < s.length(); j++) {
                char b = s.charAt(j);
                if (!list.contains(b)) {
                    list.add(b);
                } else {
                    break;
                }
            }
            if (list.size() > result.size()) {
                result = list;
            }
        }
        return result.size();
    }

    /**
     * 优化
     * abcabcbb
     *
     * @param s
     * @return
     */
    public static int goodLengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(goodLengthOfLongestSubstring("abcbdefg"));
    }
}
