package top.ks.yonyou.db.test;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author songhx
 */
public class InnerCodeUtil {

    public static int INNERCODELENGTH = 4;

    public static String getInnerCode() {
        return RandomSeqUtil.getRandomSeq(INNERCODELENGTH);
    }

    /**
     * 已存在的范围内部发范围
     *
     * @param existedItemList
     * @return
     */
    public static String getUniqueInnerCode(List<String> existedItemList) {
        return getUniqueInnerCode(existedItemList, null);
    }

    /**
     * 已经存在的内部码范围
     *
     * @param existedItemList
     * @param prefix
     * @return
     */
    public static String getUniqueInnerCode(List<String> existedItemList, String prefix) {
        String innerCode = null;
        if (!StringUtils.isEmpty(prefix)) {
            innerCode = prefix + InnerCodeUtil.getInnerCode();
        }

        do {
            innerCode = InnerCodeUtil.getInnerCode();
            if (!StringUtils.isEmpty(prefix)) {
                innerCode = prefix + innerCode;
            }
        } while (existedItemList.contains(innerCode));

        return innerCode;
    }
}
