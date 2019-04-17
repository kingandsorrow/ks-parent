package top.ks.common;

import org.junit.Test;
import top.ks.framework.client.HttpClientHelper;

import java.util.HashMap;
import java.util.Map;

public class TestDZQ {


    /*
     * method=SCZX_BG_10000104&format=json&busiSerial=12&appId=501193&appkey=5f22b2fbd3aaee3bebc475dfd0321ea5&operId=100231302&openId=e57d0276-cac3-4fcc-9c1a-aebd59693a89&timestamp=20160328091800&sign=AF6A2245E0A7CC41F697EE1C5AEC6F1A7BF31B713CC0BBC3DA3A8766E8F524DB&accessToken=c25789c8-8403-4f89-92d4-e92005e64069&RegionId=100000000045&version=1.0
     *
     *
     */
    @Test
    public void test() {
        Map<String, String> headers = new HashMap<String, String>();
//         headers.put("Content-Type", "application/x-www-form-urlencoded"); 
        headers.put("Cache-Control", "no-cache");
        // headers.put("Postman-Token", "2e3ed9d6-b2aa-6dba-3bd4-9405a49e71f6"); 
        //String result = HttpClientHelper.sendHttp(this.url, map, "", headers);

    }

    @Test
    public void testHashMap() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
