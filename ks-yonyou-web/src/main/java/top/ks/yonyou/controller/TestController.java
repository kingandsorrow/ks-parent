package top.ks.yonyou.controller;

import com.yonyou.cloud.mwclient.servmeta.annotation.ApiParam;
import com.yonyou.diwork.exception.BusinessException;
import com.yonyou.diwork.service.auth.IServiceIsolateService;
import com.yonyou.iuap.admin.entity.bo.AdminOrg;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.Order;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.entity.dto.OrgAgg;
import com.yonyou.iuap.data.entity.dto.OrgPermissionDTO;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.data.service.itf.OrgUnitDataQryService;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import com.yonyou.iuap.international.MultiLangText;
import com.yonyou.workbench.model.OrgPermVO;
import com.yonyou.workbench.param.OrgEntryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private IServiceIsolateService serviceIsolateService;
    @Autowired
    private FuncOrgDataQryService funcOrgDataQryService;
    @Autowired
    private OrgUnitDataQryService orgUnitDataQryService;

    @RequestMapping("/test")
    public String test() {
        return "test success";
    }

    @RequestMapping("testAuthService")
    public OrgPermVO testAuthService() {
        OrgEntryParam orgEntryParam = new OrgEntryParam();
        orgEntryParam.setUserId("38d0d16c-9f14-4729-be4b-cbaad1403afa");
        orgEntryParam.setServiceCode("GZTORG001");
        orgEntryParam.setConvertGloble(false);
        orgEntryParam.setTenantId("orq5s8og");
        orgEntryParam.setToken("bttU2R1dHFJbWJYNUZleFhtSE9WZ1NLcFIzYUVLU1J0U21mVk0vWk9pVGZkYXVYSUtnd29IL0luTEM2UzE4N0F4eHQ2aWVLbExXREtiL2NEK2hlalFhcHpHRnk0a0RyQXp5c2FFN21DcDUwbStIUUJEN1c4OFF6cmlCY1RpYmhpbHpfX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__1582960729593");
        OrgPermVO orgPermVO = null;
        try {
            orgPermVO = serviceIsolateService.findUserOrgPermission(orgEntryParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orgPermVO;
    }

    @RequestMapping("testOrgService")
    public FuncOrg testOrgService() {
        FuncOrg funcOrg = null;
        try {
            funcOrg = funcOrgDataQryService.getById("1603357683749120", "orq5s8og", "diwork", OrgFunc.ADMIN_ORG.getCode());
            AdminOrg adminOrg = (AdminOrg) funcOrg;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return funcOrg;
    }

    @RequestMapping("testListByIds")
    public FuncOrg testidsOrgService() {
        FuncOrg funcOrg = null;
        try {
            List<String> ids = new ArrayList<>();
            ids.add("1637021115289856");
            List<FuncOrg> funcOrgs = funcOrgDataQryService.listAllParentOrgByIds(ids, new ArrayList<Integer>(Arrays.asList(0, 1, 2)), "lrqomi2j", "diwork", false, OrgFunc.ADMIN_ORG.getCode());
            AdminOrg adminOrg = null;
            for (FuncOrg funcOrg1 : funcOrgs) {
                adminOrg = (AdminOrg) funcOrg1;

            }
            System.out.println(adminOrg.getLocaleName());
        } catch (Exception e) {

            e.printStackTrace();
        }
        return funcOrg;
    }

    @RequestMapping("testByCondition")
    public List<FuncOrg> testByCondition() {
        List<FuncOrg> funcOrgs = null;
        try {
            List<ConditionVO> conditionVOS = new ArrayList<>();
            List<Order> orderList = new ArrayList<>();
            String tenantId = "mrt16ug3";
            String sysId = "diwork";
            //String funcType = OrgFunc.ADMIN_ORG.getCode();
            String funcType = OrgFunc.ADMIN_ORG.getCode();
            funcOrgs = funcOrgDataQryService.listByCondition(conditionVOS, orderList, tenantId, sysId, funcType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcOrgs;
    }

    @RequestMapping("testByPermission")
    public List<FuncOrg> testByPermission() {
        List<FuncOrg> funcOrgs = null;
        try {
            List<ConditionVO> conditionVOS = new ArrayList<>();
            conditionVOS.add(new ConditionVO("is_biz_unit", 1 + ""));
            List<Order> orderList = new ArrayList<>();
            String tenantId = "qyic8c7o";
            String sysId = "diwork";
            String funcType = OrgFunc.ADMIN_ORG.getCode();
            String userId = "a8a92298-37ef-4ff7-ae28-44b5f4f905c4";
            String serviceCode = "GZTTMP040";
            String token = "bttV1NSVXExdFFRbGpDU2pSN1lWVGc4cWdUSHByZ1pTajRNVW96WVZFbW1ZYjY5ZjkyUndUTGV2MWJwM2pGOXhQaHExUjlrd2hVSGNRL213bFpCbkdHQWJNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__1585565824563";
            long startTime = System.currentTimeMillis();
            OrgPermissionDTO orgPermissionDTO = funcOrgDataQryService.listByConditionWithOrgPermissionUnionParentOrg(userId, serviceCode, token, tenantId, sysId, funcType, conditionVOS, orderList);
            long endTime = System.currentTimeMillis();
            System.out.println("spend time is.." + (endTime - startTime));
            System.out.println(orgPermissionDTO.getPermissionOrgList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcOrgs;
    }

    @RequestMapping("testByPermission1")
    public List<FuncOrg> testByPermission1() {
        List<FuncOrg> funcOrgs = null;
        try {
            List<ConditionVO> conditionVOS = new ArrayList<>();
            conditionVOS.add(new ConditionVO("is_biz_unit", 1 + ""));
            List<Order> orderList = new ArrayList<>();
            String tenantId = "qyic8c7o";
            String sysId = "diwork";
            String funcType = OrgFunc.ADMIN_ORG.getCode();
            String userId = "a8a92298-37ef-4ff7-ae28-44b5f4f905c4";
            String serviceCode = "GZTTMP040";
            String token = "bttV1NSVXExdFFRbGpDU2pSN1lWVGc4cWdUSHByZ1pTajRNVW96WVZFbW1ZYjY5ZjkyUndUTGV2MWJwM2pGOXhQaHExUjlrd2hVSGNRL213bFpCbkdHQWJNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__1585565824563";
            long startTime = System.currentTimeMillis();
            List<String> columns = new ArrayList<>();
            columns.add("id");
            columns.add("innercode");
            columns.add("code");
            columns.add("name");
            OrgPermissionDTO orgPermissionDTO = funcOrgDataQryService.listColumnConditionOrgPermissionAndParent(userId, serviceCode, token, tenantId, sysId, funcType, conditionVOS, orderList, columns);
            long endTime = System.currentTimeMillis();
            System.out.println("testByPermission1 spend time is.." + (endTime - startTime));
            System.out.println(orgPermissionDTO.getPermissionOrgList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcOrgs;
    }

    @RequestMapping("testOrgService1")
    public List<OrgAgg> testOrgService1() {
        List<OrgAgg> orgAggs = null;
        try {
            orgAggs = orgUnitDataQryService.listByIds(new ArrayList<String>(Arrays.asList("1611872463229184")), Arrays.asList(new Integer[]{0, 1, 2}), "ls1wp8cn", "diwork", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orgAggs;
    }


    /*@RequestMapping("testOrgDubboService")
    public List testOrgDubboService() {
        List<Map<String, Object>> list = null;
        try {
            list = this.getDefaultExchange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/

   /* private List<Map<String, Object>> getDefaultExchange() {
        List<Map<String, Object>> list = null;
        IBillQueryService iBillQueryService = DubboReferenceUtils.getReference("ucfbasedoc", null);
        if (iBillQueryService == null) {
            return null;
        }
        QueryConditionGroup group = QueryConditionGroup.and(QueryCondition.name("code").eq("01")).and(QueryCondition.name("isDefault").eq("1")).and(QueryCondition.name("dr").in(Arrays.asList(0, 2)));
        QuerySchema querySchema = QuerySchema.create().addSelect("id,name").addCondition(group);
        try {
            list = iBillQueryService.query("bd.exchangeRate.ExchangeRateTypeVO", querySchema.toString());
            if (com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(list)) {
                return list;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
