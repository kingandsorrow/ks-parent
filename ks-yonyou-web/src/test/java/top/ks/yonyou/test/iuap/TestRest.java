package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.iuap.bd.pub.util.AuthHttpClientUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestRest {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :重新初始化组织开通
     * @author : birjc
     * @CreateDate : 2021年03月03日14:56:47
     */
    @Test
    public void initOrgTest() {
        try {
            Map map = new ConcurrentHashMap<>();
            //String url = "http://orgcenter-u8c.pre.app.yyuap.com/orgcenter-u8c/rest/v1/erp/callback/init/org";
            //String url = "http://u8cbd-daily.yyuap.com/orgcenter-u8c/rest/v1/erp/callback/init/org";
            String url = " https://u8cbd-daily.yyuap.com/orgcenter-u8c/rest/v1/erp/callback/init/org/oa";
            //String url = "http://u8cbd-daily.yyuap.com/orgcenter-u8c/ref/func/financeorgs/";
            //String url = "http://ks.yyuap.com:8080/rest/v1/erp/callback/init/org";
            //String url = "http://workbench.yyuap.com/orgcenter/rest/v1/erp/callback/init/multiorg";
            String userId = "1838579e-6b2a-40e0-8c36-9d64817ba53c";
            String yht_access_token =
                    "btteFVNcVd1QjRqbFRnKzVDYmdpUXRIYmdndEs2SW9IZ01jZkk2MWc5L0FKRWsyMUdYMWo3Y0N0bHdvWkF3aG1Dd0lsRXRFRnRQWDBhN3FKQWpMZUN0alg0UVFIbWNBM2Z5Q01hbzNJeXdJMElnT1F2S0tpMFpmOGdBWWE4WG80Vm9fX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__dd3178c4bf0c95413eaf7703ef2759fc_1614847631627";
            JSONObject initParam = new JSONObject();
            initParam.put("tenantId", "itmfw0ft");
            initParam.put("sysId", "diwork");
            initParam.put("userId", userId);
            initParam.put("orgName", "kstest");

            Map<String, String> queryParam = new HashMap<>();
            queryParam.put("userId", userId);
            queryParam.put("tenantId", "itmfw0ft");

            Map<String, String> header = new HashMap<>(2);
            header.put("yht_access_token", yht_access_token);

            String post = AuthHttpClientUtils.execPost(url, queryParam, header, initParam.toJSONString());
            System.err.println(post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
