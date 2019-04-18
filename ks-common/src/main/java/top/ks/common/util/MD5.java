package top.ks.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MD5 {

	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(plainText.getBytes("gbk"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().substring(8, 24).toUpperCase();// 32
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String Md5ForUtf8(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(plainText.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().substring(8, 24).toUpperCase();// 32
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sign32(String preText,String key,String input_charset){

		return Md532(preText + key, input_charset);
	}

	public static boolean verify32(String presignStr,String sign,String key,String input_charset){

		if(sign == null || presignStr == null){

			return false;
		}

		return sign32(presignStr, key, input_charset).equals(sign);
	}

	public static String Md532(String plainText, String charset) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(plainText.getBytes(charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().toUpperCase();// 32
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String Md532(String plainText) {

		return Md532(plainText, "gbk");
	}

	/**
	 * Tests a String to MD5.<br>
	 * Usage: java net.lybbs.util.MD5<br>
	 * or java net.lybbs.util.MD5 "some test String"
	 *
	 * @param args
	 *            input a test String, no input string for default value.
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String args[]) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// B1DF7F0C51F867CE String sha="104<?xml version=\"1.0\"
		// encoding=\"utf-8\"?><msg><head transcode=\"104\"
		// partnerid=\"1001003\" version=\"1.0\" time=\"20100806143006\"
		// /><body><ticketorder gameid=\"SSQ\" ticketsnum=\"1\" totalmoney=\"2\"
		// province=\"hn\"><user userid=\"10000008\" /><tickets><ticket
		// id=\"SSQ_2010024_89A86131\" multiple=\"1\" issue=\"2010024\"
		// playtype=\"0\"
		// money=\"2\"><ball>08,12,13,20,32,33:09</ball></ticket></tickets></ticketorder></body></msg>123456";

		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1));
		list.add(new Long(2));
		Long[] s = (Long[]) list.toArray(new Long[0]);
		System.out.println(s[1]);

		// String ss = "605<?xml version=\"1.0\"
		// encoding=\"UTF-8\"?>\r\t<msg><head transcode=\"605\"
		// partnerid=\"008621\" version=\"1.0\"
		// time=\"201101191726888\"/><body><ticketresult id=\"597921\"
		// gameid=\"SPF\" palmid=\"27973207\" multiple=\"1\" issue=\"10109\"
		// playtype=\"6-1\" money=\"2\" statuscode=\"0002\" msg=\"\"
		// orgserial=\"008054-094843-824062-444150\"
		// orgcheckcode=\"G0RN6VYN5N124\"
		// province=\"bj\"/></body></msg>QEZ90CHQ";
		// // System.out.println(0x67452301L);
		// String sss = "123456abc";
		// String ssss = "008617深圳市广天地科技有限公司168377950-X";
		// String pulian = "008616王玉东0320101197602251033";

	}
}
