package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.yonyou.iuap.bd.pub.excp.BaseDocRestException;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.DataType;
import com.yonyou.iuap.bd.pub.param.Operator;
import com.yonyou.iuap.bd.pub.param.Page;
import com.yonyou.iuap.bd.pub.util.Condition;
import com.yonyou.iuap.bd.pub.util.Sorter;
import com.yonyou.iuap.org.admin.dto.AdminOrg;
import com.yonyou.iuap.org.admin.service.itf.AdminOrgService;
import com.yonyou.iuap.org.base.OrgRestParam;
import com.yonyou.iuap.org.base.OrgRestSingleton;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOrgRestService {
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
    public void testOrgRest4() throws BaseDocRestException {
        AdminOrgService adminOrgService = OrgRestSingleton.getInst("b5c093ds", "diwork",
                "f3ce959a-8e44-4b86-abbc-b06b23bb65c6", OrgRestParam.SOURCE_HR_SIGN).getBdRestService().getAdminOrgService();
        /*Condition condition = new Condition();
        ConditionVO enableCond = new ConditionVO("parentid", "2009621358760192", DataType.STRING, Operator.EQUAL);
        ConditionVO enableCond1 = new ConditionVO("enable", "1", DataType.NUMBER, Operator.EQUAL);
        condition.addCondition(enableCond);
        condition.addCondition(enableCond1);*/

        List<AdminOrg> adminOrgs = adminOrgService.listByIdList(new ArrayList<>(Arrays.asList("2047527984091392","2013585813967104","2013588403507456","2039027296112896","2009621358760192")), new ArrayList<>(Arrays.asList(1)));
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
