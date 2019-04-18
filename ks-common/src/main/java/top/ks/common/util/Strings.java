/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved.
 *
 * @project asura
 * @file StringUtil
 * @package com.asura.framework.base.util
 *
 * @date 2015/3/19 11:28
 */
package top.ks.common.util;

/**
 * <p> 字符串工具类 </P>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author SZL
 * @version 1.0
 * @since 1.0
 */
public class Strings {

	/**
     * 检测字符串是否为空.
     * <p>
     * 1. 未分配内存
     * </p>
     * <p>
     * 2. 字符串剔除掉前后空格后的长度为0
     * </p>
     *
     * @param str
     *         待检测的字符串
     *
     * @return true: 空; false:非空.
     */
    public static boolean isEmpty(final String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isNotEmpty(final String str){

    	return !isEmpty(str);
    }

    public static boolean hasEmptyStr(String...strs){

    	if(strs == null || strs.length == 0){
    		return false;
    	}

    	for (String string : strs) {

    		if(isEmpty(string)){
    			return true;
    		}
		}

    	return false;
    }

    /**
	 * 比较2个字符串，如果都是null或者都是空字符串则认为相同 function:
	 *
	 * @param str1
	 * @param str2
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isEqualIgnoreEmpty(String str1, String str2) {

		if (str1 == null) {

			if (str2 == null) {

				return true;
			}

			return str2.isEmpty();
		}

		if (str2 == null) {

			return str1.isEmpty();
		}

		return str1.trim().equals(str2.trim());
	}

	public static String appendNotEmpty(String s1,String s2){

		if(hasEmptyStr(s1,s2)){

			return null;
		}

		return s1+s2;
	}

    /**
     * 截取目标字符串（即：target）最后一个标识符（即：separator）前的子串
     *
     * @param target
     *         目标字符串
     * @param separator
     *         标识符
     *
     * @return
     */
    public static String substringBeforeLastIgnoreCase(final String target, final String separator) {
        if ("".equals(target))
            return target;
        if ("".equals(separator))
            return target;
        final String tempStr = target.toUpperCase();
        final String tempSeparator = separator.toUpperCase();

        final int index = tempStr.lastIndexOf(tempSeparator);
        if (index == -1)
            return target;
        return target.substring(0, index);
    }

    /**
     * 单引号（“'”）逃逸，用于构建SQL语句时使用
     *
     * @param target
     *         目标字符串
     *
     * @return
     */
    public static String singleQuotoAndEscape(final String target) {
        if (isNotEmpty(target)) {
            final StringBuffer t = new StringBuffer(target.length() + 3);
            t.append("'");
            t.append(target.replaceAll("'", "''"));
            t.append("'");
            return t.toString();
        }
        return "''";
    }

    /**
     * 字符串左补字符到固定长度（如：高位补0到10位）
     *
     * @param input
     *         原字符串
     * @param padCode
     *         补充字符
     * @param toLength
     *         到固定长度
     *
     * @return 补充完字符后字符串
     */
    public static final String lpad(final String input, final String padCode, final int toLength) {
        if (input != null && input.length() < toLength) {
            final StringBuffer sb = new StringBuffer(input);
            while (sb.length() < toLength) {
                sb.insert(0, padCode);
            }
            return sb.toString();
        } else {
            return input;
        }
    }

    /**
     * 字符串右补字符到固定长度（如：地位补0到10位）
     *
     * @param input
     *         原字符串
     * @param padCode
     *         补充字符
     * @param toLength
     *         到固定长度
     *
     * @return 补充完字符后字符串
     */
    public static final String rpad(final String input, final String padCode, final int toLength) {
        if (input != null && input.length() < toLength) {
            final StringBuffer sb = new StringBuffer(input);
            while (sb.length() < toLength) {
                sb.append(padCode);
            }
            return sb.toString();
        } else {
            return input;
        }
    }

    /**
     * 判断字符串长度范围. 默认从0开始.
     *
     * @param str
     *         待判断字符串
     * @param length
     *         最大长度
     *
     * @return
     */
    public static boolean isMeetInExpectLength(final String str, final int length) {
        return isMeetInExpectLength(str, 0, length);
    }

    /**
     * 判断字符串长度范围 (min,max]
     *
     * @param str
     *         待判断字符串
     * @param minLength
     *         最小长度
     * @param maxLength
     *         最大长度
     *
     * @return
     */
    public static boolean isMeetInExpectLength(final String str, final int minLength, final int maxLength) {
        if (str != null && str.length() > minLength) {
            return str.length() < maxLength + 1;
        }
        return false;
    }

    /**
     * 删除空格
     *
     * @param str
     *         目标字符串
     *
     * @return 处理后的字符串
     */
    public static String delSpace(String str) {
        return str = str.replaceAll("\\s*", "");
    }

    /**
     * 截取固定长度字符串
     *
     * @param str
     *         目标字符串
     * @param length
     *         固定长度
     *
     * @return 截取后的字符串
     */
    public static String cutStr(String str, int length) {
        if (isEmpty(str) || str.length() < length) {
            return str;
        }
        return str.substring(0, length);
    }
}
