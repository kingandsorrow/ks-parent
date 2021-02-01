package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.iuap.bd.pub.excp.BaseDocRestException;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.DataType;
import com.yonyou.iuap.bd.pub.param.Operator;
import com.yonyou.iuap.bd.pub.param.Page;
import com.yonyou.iuap.bd.pub.util.AuthHttpClientUtils;
import com.yonyou.iuap.bd.pub.util.Condition;
import com.yonyou.iuap.bd.pub.util.Sorter;
import com.yonyou.iuap.org.admin.dto.AdminOrg;
import com.yonyou.iuap.org.admin.service.itf.AdminOrgService;
import com.yonyou.iuap.org.base.OrgRestParam;
import com.yonyou.iuap.org.base.OrgRestSingleton;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class TestOrgRestService {
    @Test
    public void testOrgOne() {
        try {
            //String url = "http://orgcenter-u8c.pre.app.yyuap.com/orgcenter-u8c/rest/v1/erp/callback/init/org";

            // String url = "http://workbench.yyuap.com/orgcenter/rest/v1/erp/callback/init/multiorg";
            String userId = "5251a6a8-afba-42de-b004-10f331734566";
            String yht_access_token =
                    "bttOTRPdGI2NzBidTJzbXJBRnpYVFREQ04yUHpsWXA5SjdUNXBCRStDbjFGOE5iaUlSYlVFcVNLdTBpc2dQb2JXOWJCcXkralVLS1BScEJvM2ZSbndJdlg0UVFIbWNBM2Z5Q01hbzNJeXdJMElnT1F2S0tpMFpmOGdBWWE4WG80Vm9fX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__360b92e63954a9badb76e9efa949a631_1597221758954";
            JSONObject initParam = new JSONObject();
            initParam.put("type", "multi");
            initParam.put("tenantId", "o5bpg5zh");
            initParam.put("sysId", "diwork");
            initParam.put("userId", userId);
            //String url = "http://u8cbd-daily.yyuap.com/orgcenter-u8c/rest/v1/infoLogLevel?userId="+userId+"&sysId=diwork&tenantId=o5bpg5zh";
            String url = "http://orgcenter-u8c.pre.app.yyuap.com/orgcenter-u8c/rest/v1/infoLogLevel?userId=" + userId + "&sysId=diwork&tenantId=o5bpg5zh";
            //String url = "http://u8cbd-daily.yyuap.com/orgcenter-u8c/rest/v1/resetLogLevel?userId="+userId+"&sysId=diwork&tenantId=o5bpg5zh";
            Map<String, String> queryParam = new HashMap<>();
            queryParam.put("userId", userId);

            Map<String, String> header = new HashMap<>(2);
            header.put("yht_access_token", yht_access_token);

            String post = AuthHttpClientUtils.execGet(url, queryParam, header);
            System.err.println(post);
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgRestService.testOrgOne:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void testOrgRest1() throws BaseDocRestException {
        AdminOrgService adminOrgService = OrgRestSingleton.getInst("czqne4bp", "diwork",
                "f3ce959a-8e44-4b86-abbc-b06b23bb65c6", OrgRestParam.SOURCE_HR_SIGN).getBdRestService().getAdminOrgService();
        Condition condition = new Condition();
        ConditionVO enableCond = new ConditionVO("enable", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(enableCond);
        ConditionVO bizunitCond = new ConditionVO("is_biz_unit", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(bizunitCond);
        int pageIndex = 1;
        int pageSize = 10;
        Page<AdminOrg> page = adminOrgService.pagination(condition, new Sorter(), pageIndex, pageSize);
        System.out.println(JSON.toJSONString(page));
    }

    @Test
    public void testOrgRest3() throws BaseDocRestException {
        AdminOrgService adminOrgService = OrgRestSingleton.getInst("b5c093ds", "diwork",
                "f3ce959a-8e44-4b86-abbc-b06b23bb65c6", OrgRestParam.SOURCE_HR_SIGN).getBdRestService().getAdminOrgService();
        Condition condition = new Condition();
        ConditionVO enableCond = new ConditionVO("parentid", "2009621358760192", DataType.STRING, Operator.EQUAL);
        ConditionVO enableCond1 = new ConditionVO("enable", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(enableCond);
        condition.addCondition(enableCond1);
        List<AdminOrg> adminOrgs = adminOrgService.list(condition, new Sorter());
        System.out.println(JSON.toJSONString(adminOrgs));
    }

    @Test
    public void testOrgRest9() throws BaseDocRestException {
        try {
            AdminOrgService adminOrgService = OrgRestSingleton.getInst("x30fk78m", "diwork",
                    "f3ce959a-8e44-4b86-abbc-b06b23bb65c6", OrgRestParam.SOURCE_HR_SIGN).getBdRestService().getAdminOrgService();
            List<ConditionVO> conditions = JSON.parseArray("[{\"dataType\":\"STRING\",\"operator\":\"EQUAL\",\"fieldName\":\"enable\",\"type\":\"VALUE\",\"validate\":true,\"value\":1},{\"dataType\":\"STRING\",\"operator\":\"EQUAL\",\"fieldName\":\"dr\",\"type\":\"VALUE\",\"validate\":true,\"value\":0},{\"dataType\":\"STRING\",\"operator\":\"EQUAL\",\"fieldName\":\"orgtype\",\"type\":\"VALUE\",\"validate\":true,\"value\":\"2\"},{\"expressions\":[{\"dataType\":\"STRING\",\"operator\":\"ISNULL\",\"fieldName\":\"parentid\",\"type\":\"VALUE\",\"validate\":true},{\"dataType\":\"STRING\",\"operator\":\"EQUAL\",\"fieldName\":\"parentid\",\"type\":\"VALUE\",\"validate\":true,\"value\":\"\"}],\"or\":true,\"type\":\"CHAIN\"}]", ConditionVO.class);
            Condition condition = new Condition();
            condition.setConditionList(conditions);
            List<AdminOrg> adminOrgs = adminOrgService.list(condition, new Sorter());
            System.out.println(JSON.toJSONString(adminOrgs));
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgRestService.testOrgRest9:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void testOrgRest4() throws BaseDocRestException {
        AdminOrgService adminOrgService = OrgRestSingleton.getInst("b5c093ds", "diwork",
                "f3ce959a-8e44-4b86-abbc-b06b23bb65c6", OrgRestParam.SOURCE_HR_SIGN).getBdRestService().getAdminOrgService();
        /*Condition condition = new Condition();
        ConditionVO enableCond = new ConditionVO("parentid", "2009621358760192", DataType.STRING, Operator.EQUAL);
        ConditionVO enableCond1 = new ConditionVO("enable", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(enableCond);
        condition.addCondition(enableCond1);*/

        List<AdminOrg> adminOrgs = adminOrgService.listByIdList(new ArrayList<>(Arrays.asList("2047527984091392", "2013585813967104", "2013588403507456", "2039027296112896", "2009621358760192")), new ArrayList<>(Arrays.asList(1)));
        System.out.println(JSON.toJSONString(adminOrgs));
    }

    @Test
    public void testOrgRest2() throws BaseDocRestException {
        AdminOrgService adminOrgService = OrgRestSingleton.getInst("czqne4bp", "diwork",
                "f3ce959a-8e44-4b86-abbc-b06b23bb65c6", OrgRestParam.SOURCE_HR_SIGN).getBdRestService().getAdminOrgService();
        Condition condition = new Condition();
        ConditionVO enableCond = new ConditionVO("enable", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(enableCond);
        ConditionVO bizunitCond = new ConditionVO("is_biz_unit", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(bizunitCond);
        int pageIndex = 1;
        int pageSize = 10;
        Page<AdminOrg> page = adminOrgService.pagination(condition, new Sorter(), pageIndex, pageSize);
        System.out.println(JSON.toJSONString(page));
    }
}
