package top.ks.common.context;

import java.util.Iterator;
import java.util.Map;

public class InvocationInfoProxy {
    private static final ThreadLocal<InvocationInfo> threadLocal = new ThreadLocal<InvocationInfo>() {
        protected InvocationInfo initialValue() {
            return new InvocationInfo();
        }
    };

    public InvocationInfoProxy() {
    }

    public static void reset() {
        threadLocal.remove();
    }

    public static String getProjectId() {
        return ((InvocationInfo) threadLocal.get()).projectId;
    }

    public static void setProjectId(String projectId) {
        ((InvocationInfo) threadLocal.get()).projectId = projectId;
    }

    public static String getUserid() {
        return ((InvocationInfo) threadLocal.get()).userid;
    }

    public static void setUserid(String userid) {
        ((InvocationInfo) threadLocal.get()).userid = userid;
    }

    public static String getLoglevel() {
        return ((InvocationInfo) threadLocal.get()).loglevel;
    }

    public static void setLoglevel(String loglevel) {
        ((InvocationInfo) threadLocal.get()).loglevel = loglevel;
    }

    public static String getToken() {
        return ((InvocationInfo) threadLocal.get()).token;
    }

    public static void setToken(String token) {
        ((InvocationInfo) threadLocal.get()).token = token;
    }

    public static String getLogints() {
        return ((InvocationInfo) threadLocal.get()).logints;
    }

    public static void setLogints(String logints) {
        ((InvocationInfo) threadLocal.get()).logints = logints;
    }

    public static String getTheme() {
        return ((InvocationInfo) threadLocal.get()).theme;
    }

    public static void setTheme(String theme) {
        ((InvocationInfo) threadLocal.get()).theme = theme;
    }

    public static String getUsername() {
        return ((InvocationInfo) threadLocal.get()).username;
    }

    public static void setUsername(String username) {
        ((InvocationInfo) threadLocal.get()).username = username;
    }

    public static String getAppid() {
        return ((InvocationInfo) threadLocal.get()).appid;
    }

    public static void setAppid(String appid) {
        ((InvocationInfo) threadLocal.get()).appid = appid;
    }

    public static String getProviderid() {
        return ((InvocationInfo) threadLocal.get()).providerid;
    }

    public static void setProviderid(String providerid) {
        ((InvocationInfo) threadLocal.get()).providerid = providerid;
    }

    public static String getLocale() {
        return ((InvocationInfo) threadLocal.get()).locale;
    }

    public static void setLocale(String locale) {
        ((InvocationInfo) threadLocal.get()).locale = locale;
    }

    public static Object getExtendAttribute(String key) {
        return ((InvocationInfo) threadLocal.get()).extendAttributes.get(key);
    }

    public static <T> T getExtendAttribute(Class<T> cls) {
        return (T) ((InvocationInfo) threadLocal.get()).extendAttributes.get(cls);
    }

    public static void setExtendAttribute(Object key, Object value) {
        ((InvocationInfo) threadLocal.get()).extendAttributes.put(key, value);
    }

    public static Map<String, String> getParamters() {
        return ((InvocationInfo) threadLocal.get()).parameters;
    }

    public static String getParameter(String parameter) {
        return (String) ((InvocationInfo) threadLocal.get()).parameters.get(parameter);
    }

    public static String setParameter(String parameter, String value) {
        return (String) ((InvocationInfo) threadLocal.get()).parameters.put(parameter, value);
    }

    public static Iterator<Map.Entry<String, String>> getSummry() {
        return ((InvocationInfo) threadLocal.get()).getSummry();
    }

    public static String getTimeZone() {
        return ((InvocationInfo) threadLocal.get()).timezone;
    }

    public static void setTimeZone(String timeZone) {
        if (timeZone != null) {
            ((InvocationInfo) threadLocal.get()).timezone = timeZone;
        }

    }

    public static String getDataFormat() {
        return ((InvocationInfo) threadLocal.get()).dataformat;
    }

    public static void setDataFormat(String dataFormat) {
        ((InvocationInfo) threadLocal.get()).dataformat = dataFormat;
    }
}
