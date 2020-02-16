package top.ks.cy.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class Toolkit {


    public static boolean isEmpty(String s) {
        if (null != s && s.trim().length() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyObject(Object s) {
        return (s == null || "".equals(s));
    }

    public static Map<String, Object> setTabMap(String name, String url,
                                                boolean isOpen) {
        Map<String, Object> tab = new HashMap<String, Object>();
        tab.put("name", name);
        tab.put("url", url);
        tab.put("isOpen", isOpen);
        return tab;
    }


    public static Gson getDateFormatGson(String dateFormat) {
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.setDateFormat(dateFormat);
        return gBuilder.create();
    }

    public static String getIpAddr() {

        return "";
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        if (null == v1)
            v1 = new BigDecimal("0");
        if (null == v2)
            v2 = new BigDecimal("0");
        return v1.add(v2);
    }

    public static BigDecimal add(int v1, BigDecimal v2) {
        if (null == v2)
            v2 = new BigDecimal("0");
        return new BigDecimal(v1).add(v2);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        if (null == v1)
            v1 = new BigDecimal("0");
        if (null == v2)
            v2 = new BigDecimal("0");
        return v1.subtract(v2);
    }

    public static BigDecimal sub(int v1, BigDecimal v2) {
        if (null == v2)
            v2 = new BigDecimal("0");
        return new BigDecimal(v1).subtract(v2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        if (null == v1)
            v1 = new BigDecimal("0");
        if (null == v2)
            v2 = new BigDecimal("0");
        return v1.multiply(v2);
    }

    public static BigDecimal mul(int v1, BigDecimal v2) {
        if (null == v2)
            v2 = new BigDecimal("0");
        return new BigDecimal(v1).multiply(v2);
    }

    //除
    public static double divide(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, RoundingMode.HALF_UP).doubleValue();
    }

    public static BigDecimal divide(BigDecimal v1, BigDecimal v2, int scale) {
        if (null == v1) return new BigDecimal("0");
        return v1.divide(v2, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal v1, int v2, int scale) {
        if (null == v1) return new BigDecimal("0");
        return v1.divide(new BigDecimal(v2), scale, RoundingMode.HALF_UP);
    }

    /**
     *
     */
    public static boolean isIpV4Address(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            String[] parts = value.toString().split("[.]");
            if (parts.length != 4) {
                return false;
            }

            for (int i = 0; i < parts.length; i++) {
                int p = Integer.valueOf(parts[i]);
                if (p < 0 || p > 255) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取系统唯一ID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    private static final double EARTH_RADIUS = 6378.137;//赤道半径(单位km)

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于余弦定理求两经纬度距离
     *
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat1 第二点的纬度
     * @return 返回的距离，单位km
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);

        double radLon1 = rad(lon1);
        double radLon2 = rad(lon2);

        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double dist = theta * EARTH_RADIUS;
        return dist;
    }

    /**
     * 提取中括号中内容，忽略中括号中的中括号
     * [采购入库单子表.本币无税单价]*[采购入库单子表.本币含税单价] 提取分割成数组
     * [采购入库单子表.本币无税单价,采购入库单子表.本币含税单价]
     *
     * @param msg
     * @return
     */
    public static List<String> extractMessage(String msg) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '[') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == ']') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        return list;
    }


    /**
     * 1.不让BigDecimal以科学计数法进行了转换
     * 2.格式化数据
     *
     * @param obj
     * @return
     */
    public static String getFormatDecimalValue(Object obj) {
        String value = null;
        if (obj != null) {
            if (obj instanceof BigDecimal) {
                BigDecimal decimal = (BigDecimal) obj;
                if (decimal.compareTo(BigDecimal.ZERO) == 0)
                    value = decimal.stripTrailingZeros().toPlainString();
                else
                    value = obj.toString();
            } else {
                value = obj.toString();
            }
        }
        return value;
    }

    /**
     * 将对象转换为字符串
     *
     * @param obj
     * @return
     */
    public static String getString(Object obj) {
        String str = null;
        try {
            str = obj.toString();
        } catch (Exception e) {

        }
        return str;
    }


    /**
     * 将obj类型转换成Decimal
     *
     * @param obj
     * @return
     */
    public static BigDecimal getBigDecimal(Object obj) {
        if (obj == null)
            return BigDecimal.ZERO;
        if (obj instanceof Long) {
            return new BigDecimal(Long.toString((Long) obj));
        } else if (obj instanceof Integer) {
            return new BigDecimal(Integer.toString((Integer) obj));
        } else if (obj instanceof Double) {
            return new BigDecimal(Double.toString((Double) obj));
        } else if (obj instanceof String) {
            return new BigDecimal((String) obj);
        } else if (obj instanceof LinkedHashMap) {
            return BigDecimal.ZERO;
        } else if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        return new BigDecimal(((BigDecimal) obj).doubleValue(), MathContext.DECIMAL64);
    }

    public static String getPrecentValue(int totalCount, int count) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) count / (float) totalCount * 100);
        return result;
    }

}