package top.ks.common.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InvocationInfo {
    String loglevel;
    String userid;
    String username;
    String locale;
    String theme;
    String timezone;
    String dataformat;
    String token;
    String logints;
    String projectId;
    String appid;
    String providerid;
    String callid;
    Map<Object, Object> extendAttributes = new HashMap();
    Map<String, String> parameters = new HashMap();

    InvocationInfo() {
    }

    public Iterator<Map.Entry<String, String>> getSummry() {
        Map<String, String> map = new HashMap();
        map.putAll(this.parameters);
        map.put("loglevel", this.loglevel);
        map.put("token", this.token);
        map.put("logints", this.logints);
        map.put("locale", this.locale);
        map.put("theme", this.theme);
        map.put("timezone", this.timezone);
        map.put("userid", this.userid);
        map.put("username", this.username);
        map.put("projectId", this.projectId);
        map.put("appid", this.appid);
        map.put("providerid", this.providerid);
        map.put("callid", this.callid);
        return map.entrySet().iterator();
    }
}
