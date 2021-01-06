package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.yonyou.diwork.exception.BusinessException;
import com.yonyou.diwork.service.auth.IServiceIsolateExtensionService;
import com.yonyou.diwork.service.auth.IServiceIsolateService;
import com.yonyou.iuap.bd.common.exception.BaseDocException;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.Operator;
import com.yonyou.iuap.bd.pub.param.Order;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.entity.dto.OrgAgg;
import com.yonyou.iuap.data.entity.dto.OrgPermissionDTO;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.data.service.itf.OrgUnitDataQryService;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import com.yonyou.iuap.org.admin.dto.AdminOrg;
import com.yonyou.iuap.org.entity.bo.Org;
import com.yonyou.workbench.model.OrgDeptVO;
import com.yonyou.workbench.model.OrgPermVO;
import com.yonyou.workbench.param.OrgEntryParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouWebApplication.class)
public class TestOrgService {

    @Autowired
    private FuncOrgDataQryService funcOrgDataQryService;
    @Autowired
    private OrgUnitDataQryService orgUnitDataQryService;
    @Autowired
    private IServiceIsolateService iServiceIsolateService;

    @Test
    public void test1() throws BaseDocException {
        FuncOrg funcOrg = funcOrgDataQryService.getById("1900923657998592", "czqne4bp", "diwork", OrgFunc.ADMIN_ORG.getCode());
        System.out.println(JSON.toJSON(funcOrg));
    }

    @Test
    public void test5() throws BaseDocException {
        try {
            List<ConditionVO> var1 = new ArrayList<>();
            List<Order> var2 = new ArrayList<>();
            String tenantId = "s59t51a3";
            String sysId = "diwork";
            String funcType = OrgFunc.ADMIN_ORG.getCode();
            List<FuncOrg> funcOrgs = funcOrgDataQryService.listByCondition(var1, var2, tenantId, sysId, OrgFunc.ADMIN_ORG.getCode());
            List<Org> adminOrgList = new ArrayList<>();
            for (FuncOrg funcOrg : funcOrgs) {
                Org adminOrg = (Org) funcOrg;
                adminOrgList.add(adminOrg);
            }
            System.out.println(JSON.toJSON(adminOrgList));
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test5:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }


    @Test
    public void test10() throws BaseDocException {
        try {
            List<String> ids = new ArrayList<>(Arrays.asList("303332"));
            List<Integer> status = new ArrayList<>(Arrays.asList(0, 1, 2));
            List<OrgAgg> orgAggs = orgUnitDataQryService.listByIds(ids, status, "rmxzr14e", "diwork", true);
            System.out.println(orgAggs);
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test10:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void test6() throws BaseDocException {
        try {
            List<ConditionVO> var1 = new ArrayList<>();
            List<Order> var2 = new ArrayList<>();
            String tenantId = "i2whps0k";
            String sysId = "diwork";
            String serviceCode = "GZTTMP040";
            String userId = "83c1a25a-bb42-48de-885f-cb789cbc1174";
            String token = "bttTUNKeHFlTFpBMkJZL2NCRlEvWTluWWhZS1lUaUNadFNpSnZhV0tYcXVjeElIT2xpbmxsOHJ2UHllT0hybmxvbWVmc09MemhjZkJQM2xqOWJzODBvWXJNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__e09c32e6148a77194ea84f76bef74fee_1605251736452";
            String funcType = OrgFunc.ADMIN_ORG.getCode();
            List<ConditionVO> conditionVOS = new ArrayList<>();
            List<Order> orders = new ArrayList<>();
            List<String> colums = new ArrayList<>();
            colums.add("id");
            colums.add("code");
            colums.add("name");
            //@ApiParam(name = "userId",description = "用户ID") String var1, @ApiParam(name = "serviceCode",description = "服务编码") String var2,
            // @ApiParam(name = "token",description = "友户通token") String var3, @ApiParam(name = "tenantId",description = "租户标识") String var4,
            // @ApiParam(name = "sysId",description = "系统标识") String var5, @ApiParam(name = "funcType",description = "职能类型编码") String var6,
            // @ApiParam(name = "conditionList",description = "条件表达式列表") List<ConditionVO> var7, @ApiParam(name = "orderList",description = "排序列表") List<Order> var8,
            // @ApiParam(name = "columnList",description = "查询字段列表") List<String> var9) throws BaseDocException;
            OrgPermissionDTO funcOrgs = funcOrgDataQryService.listColumnConditionOrgPermissionAndParent(userId, serviceCode, token, tenantId, sysId, OrgFunc.ADMIN_ORG.getCode(), conditionVOS, orders, colums);
            System.out.println(JSON.toJSON(funcOrgs));
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test6:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void test8() throws BaseDocException {
        String tenantId = "ohgfkm6i";
        String sysId = "diwork";
        String serviceCode = "AMC506012";
        String userId = "4c4c966a-b9c1-4fc1-bada-1e2aa7fa98e2";
        String token = "btteFRqbU9hOWY0MmdvaFBXSXEyd3BiK3BkUDVwYUNZOEYzTnJBMjhMZzFqRHVMY2tMaU4ydTlDU1pVY0lIa1lUeE1TK3VQZXVXQm53ZWVZOHljVE5WejM0UVFIbWNBM2Z5Q01hbzNJeXdJMElnT1F2S0tpMFpmOGdBWWE4WG80Vm9fX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__f20133e1b37c4cd485bb862c8283b451_1605253929412";
        OrgEntryParam orgEntryParam = new OrgEntryParam();
        orgEntryParam.setUserId(userId);
        orgEntryParam.setServiceCode(serviceCode);
        orgEntryParam.setConvertGloble(false);
        orgEntryParam.setTenantId(tenantId);
        orgEntryParam.setToken(token);
        OrgPermVO orgPermVO = null;
        long startTime = System.currentTimeMillis();
        try {
            orgPermVO = iServiceIsolateService.findUserOrgPermission(orgEntryParam);
            System.out.println(orgPermVO);
        } catch (BusinessException e) {
            log.error(String.format(">>>>> %s:权限接口服务异常:%s, %s, %s",
                    this.getClass().getSimpleName() + "#listByConditionWithOrgPermission", orgEntryParam.getServiceCode(),
                    orgEntryParam.getToken(), orgEntryParam.getTenantId()));
            throw new BaseDocException("权限接口服务异常，无法获取用户组织权限", e);
        }
    }

    @Test
    public void test7() throws BaseDocException {
        String tenantId = "i2whps0k";
        String sysId = "diwork";
        String serviceCode = "GZTTMP040";
        String userId = "83c1a25a-bb42-48de-885f-cb789cbc1174";
        String token = "bttTUNKeHFlTFpBMkJZL2NCRlEvWTluWWhZS1lUaUNadFNpSnZhV0tYcXVjeElIT2xpbmxsOHJ2UHllT0hybmxvbWVmc09MemhjZkJQM2xqOWJzODBvWXJNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__e09c32e6148a77194ea84f76bef74fee_1605251736452";
        OrgEntryParam orgEntryParam = new OrgEntryParam();
        orgEntryParam.setUserId(userId);
        orgEntryParam.setServiceCode(serviceCode);
        orgEntryParam.setConvertGloble(false);
        orgEntryParam.setTenantId(tenantId);
        orgEntryParam.setToken(token);
        OrgPermVO orgPermVO = null;
        long startTime = System.currentTimeMillis();
        try {
            orgPermVO = iServiceIsolateService.findUserOrgPermission(orgEntryParam);
            System.out.println(orgPermVO);
        } catch (BusinessException e) {
            log.error(String.format(">>>>> %s:权限接口服务异常:%s, %s, %s",
                    this.getClass().getSimpleName() + "#listByConditionWithOrgPermission", orgEntryParam.getServiceCode(),
                    orgEntryParam.getToken(), orgEntryParam.getTenantId()));
            throw new BaseDocException("权限接口服务异常，无法获取用户组织权限", e);
        }
    }

    @Test
    public void test3() throws BaseDocException {
        OrgAgg funcOrg = orgUnitDataQryService.getById("1971037920121344", "tsh7gkxd", "diwork", true);
        System.out.println(JSON.toJSON(funcOrg));
    }

    @Test
    public void test2() throws BaseDocException {
        List<ConditionVO> conditionVOS = new ArrayList<>();
        conditionVOS.add(getCodeConditionVO());
        conditionVOS.add(getDeptConditionVO());
        List<FuncOrg> funcOrgs = funcOrgDataQryService.listByCondition(conditionVOS, null, "en3ova8g", "diwork", OrgFunc.ADMIN_ORG.getCode());
        System.out.println(funcOrgs);
    }

    @Test
    public void test4() throws BaseDocException {
        try {
            List<String> list = new ArrayList<>(Arrays.asList("1984005349216512", "1984005350330624", "1985588670189824", "1985612186439936", "1985731718091008", "1986659092517120"));
            List<Integer> list1 = new ArrayList<>(Arrays.asList(0, 1, 2));
            List<OrgAgg> orgAggs = orgUnitDataQryService.listByIds(list, list1, "u1ddi6qy", "diwork", true);
            System.out.println(orgAggs);
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test4:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }



    private ConditionVO getCodeConditionVO() {
        ConditionVO enableVO = new ConditionVO();
        enableVO.setField("code");
        enableVO.setOperator(Operator.IN);
        List<ConditionVO> values = new ArrayList<>();
        ConditionVO zero = new ConditionVO();
        zero.setValue("03847");
        values.add(zero);
        ConditionVO one = new ConditionVO();
        one.setValue("8785555");
        values.add(one);
        enableVO.setConditionList(values);
        return enableVO;
    }


    private ConditionVO getDeptConditionVO() {
        ConditionVO enableVO = new ConditionVO();
        enableVO.setField("orgtype");
        enableVO.setOperator(Operator.EQUAL);
        enableVO.setValue("2");
        return enableVO;
    }
}
