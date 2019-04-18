package top.ks.common.util;


/**
 *
 * 生成唯一序列号工具类
 *
 * @author shahuaitao 2010-03-14
 *
 */
public class SequenceHelper {

	// 格式化时间生成sequence的开头部分
	private static java.text.DateFormat df = new java.text.SimpleDateFormat(
			"yyMMddHHmmssSSS");

	private static int jvm_seq = 9000;//ConfigHelper.getInt("jvm_seq_init", 10000);

	private static int init_seq = jvm_seq;

	private final static int interval = 10000;
	// 有前缀的interval
	private final static int preInterval = 1000;
	// 有前缀的seq
	private static int preSeq = 0;

	public static synchronized String getNextSequence(String prefix){

		StringBuilder sb = new StringBuilder(prefix);
		sb.append(System.currentTimeMillis());

		preSeq ++;
		if(preSeq >= preInterval){

			preSeq = 0;
		}

		if(preSeq < 10){
			sb.append("00");
		} else if(preSeq < 100){
			sb.append("0");
		}

		sb.append(preSeq);

		return sb.toString();
	}

	public static synchronized String getNextSequence() {
		StringBuffer sb = new StringBuffer();
		sb.append(df.format(new java.util.Date()));
		jvm_seq++;
		if (jvm_seq >= interval){
			jvm_seq = 0;
		}

		if(jvm_seq < 10){
			sb.append("000");
		} else if(jvm_seq < 100){
			sb.append("00");
		} else if(jvm_seq < 10){
			sb.append("0");
		}

		sb.append(jvm_seq);

		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Long.MAX_VALUE);
		System.out.println(df.format(new java.util.Date()));
		for (int i = 0; i < 1000; i++){

			System.out.println(getNextSequence());
			System.out.println(getNextSequence("641"));
		}
	}

	public static int getJvm_seq() {
		return jvm_seq;
	}

	public static void setJvm_seq(int jvm_seq) {
		SequenceHelper.jvm_seq = jvm_seq;
		SequenceHelper.init_seq=jvm_seq;
	}

}
