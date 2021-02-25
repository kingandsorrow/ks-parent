package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.yonyou.diwork.exception.BusinessException;
import com.yonyou.diwork.service.auth.IServiceIsolateExtensionService;
import com.yonyou.diwork.service.auth.IServiceIsolateService;
import com.yonyou.iuap.bd.common.exception.BaseDocException;
import com.yonyou.iuap.bd.pub.page.PaginationDTO;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.Operator;
import com.yonyou.iuap.bd.pub.param.Order;
import com.yonyou.iuap.context.InvocationInfoProxy;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.entity.dto.OrgAgg;
import com.yonyou.iuap.data.entity.dto.OrgPermissionDTO;
import com.yonyou.iuap.data.service.itf.DelegateServiceApi;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.data.service.itf.OrgUnitDataQryService;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import com.yonyou.iuap.org.admin.dto.AdminOrg;
import com.yonyou.iuap.org.biz.delegate.bo.PurchaseDelegate;
import com.yonyou.iuap.org.entity.bo.Org;
import com.yonyou.ucf.org.common.api.OrgComApi;
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
    @Autowired
    private DelegateServiceApi delegateServiceApi;
    @Autowired
    private OrgComApi orgComApi;
    @Autowired
    private IServiceIsolateExtensionService iServiceIsolateExtensionService;

    private void test16(){
        List<String> list = new ArrayList<>();

        iServiceIsolateExtensionService.calculateOrgDeptByRule("al5whxtd","db78dcad-ffa2-41ea-b6d8-e7ea507eb442","orgdept",list);
    }

    @Test
    public void test1() throws BaseDocException {
        FuncOrg funcOrg = funcOrgDataQryService.getById("1900923657998592", "czqne4bp", "diwork", OrgFunc.ADMIN_ORG.getCode());
        System.out.println(JSON.toJSON(funcOrg));
    }
    @Test
    public void test29() throws BaseDocException {
        OrgFunc.INVENTORY_ORG.getCode();
        List<String> ids = new ArrayList<>(Arrays.asList("303332")); //组织id
        List<Integer> status = new ArrayList<>(Arrays.asList(0, 1, 2));// 状态 0未启用 1启用 2 停用
        List<FuncOrg> funcOrg = funcOrgDataQryService.listByIds(ids,status, "czqne4bp", "diwork", OrgFunc.FINANCE_ORG.getCode());
        System.out.println(JSON.toJSON(funcOrg));
    }

    @Test
    public void test18() throws BaseDocException {
        try {
            List<Integer> statusList = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
            // @ApiParam(name = "id",description = "主键") String var1, @ApiParam(name = "statusList",description = "状态列表，0初始化，1启用，2停用；此参数为空，默认查询启用") List<Integer> var2, @ApiParam(name = "tenantId",description = "租户标识") String var3, @ApiParam(name = "sysId",description = "系统标识") String var4, @ApiParam(name = "includeCurrentNode",description = "是否包含当前节点") boolean var5, @ApiParam(name = "funcType",description = "职能类型编码") String var6) throws BaseDocException;
            List<FuncOrg> funcOrgList = funcOrgDataQryService.listAllParentOrgById("1909113759388160", statusList, "td4z5vmi", "diwork", true, OrgFunc.ADMIN_ORG.getCode());
            System.out.println(JSON.toJSON(funcOrgList));
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test18:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void test20() {
        try {
            List<ConditionVO> conditionVOS = new ArrayList<>();
            List<Order> orders = new ArrayList<>();
            PaginationDTO<FuncOrg> paginationDTO = funcOrgDataQryService.pagination(1, 50, conditionVOS, orders, "x30fk78m", "diwork", OrgFunc.ADMIN_ORG.getCode());
            System.out.println(JSON.toJSONString(paginationDTO));
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test20:: %s, %s", "system error::" + e.getMessage(), e));
        }
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
    public void test11() throws BaseDocException {
        try {
            InvocationInfoProxy.setToken("locale=zh_CN; Hm_lvt_6495ea4f6fbb47d0b926af4228140e91=1611644896; ARK_ID=JS2e3074045c7eabaf36c118d21110d7782e30; eudiqz=lzsrm3iy98b0ac72-9c3e-47ef-a584-d004ec8b01df+78594787; YKJ_IS_DIWORK=1; YKJ_DIWORK_DATA=%7B%22data%22%3A%7B%22is_diwork%22%3A1%2C%22cur_qzid%22%3A%2224161%22%7D%2C%22key%22%3A%227fe3c56e225e8ad74e1ecf1d3c52c276%22%7D; PHPSESSID=abd8fo8c7ijcplg79vh9icl49h; ck_safe_chaoke_csrf_token=68dfe32aab917f481b71eb4694eb2234; ARK_STARTUP=eyJTVEFSVFVQIjp0cnVlLCJTVEFSVFVQVElNRSI6IjIwMjEtMDEtMjYgMTU6MjI6MTIuMTA2In0%3D; orgstatus=true; businessDate=2021-01-26; Hm_lpvt_6495ea4f6fbb47d0b926af4228140e91=1611645929; at=2d5de952-ba89-4643-af96-a2f98cb041e8; yonyou_uid=e84d2e55-48a3-4349-8d5e-c50e1587b793; yonyou_uname=13811085326; JSESSIONID=node0v3fjezfxaz473ev1gapg64p44401.node0; yht_username_diwork=ST-19184-wYpD1Hjy7vnp4Y0Y12B5-u8c-user-daily.yyuap.com__e84d2e55-48a3-4349-8d5e-c50e1587b793; yht_usertoken_diwork=uNoB7DkJbwXx80bULE5XXh%2B3GVUkMWDfD9v47y4DOFY5QNw3wZTzfEddSHBHq5ijkrapn%2BmiYjxVrCWsa%2FhQcQ%3D%3D; wb_at=LMjnvnuqjvRo6nAixtumoqRmRno4rjtubjtrdqjcZhkxkxxtZokbnl; locale=zh_CN; yht_access_token=bttS2c4T0RBeHJOZ3BTUVBUZDhkTXR2bCtnelN4N203OW56WFMwcFR6QXFMTURZZGc1bHhPUGhGUytaMUo4R0QxSDFoTnNNNVdzcytTZVArUVZMd3duMHpHRnk0a0RyQXp5c2FFN21DcDUwbStIUUJEN1c4OFF6cmlCY1RpYmhpbHpfX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__736cf8857d13149cc14fde766127d1ee_1611646074386; tenantid=aodu0hnj; isdiwork=false; acw_tc=2760827416116467082524753e58d072b0ae9481e20d3554da82586a36e052; FZ_STROAGE.yyuap.com=eyJBUktTVVBFUiI6eyJ0ZW5hbnRfaWQiOiJhb2R1MGhuaiIsImNvbXBhbnkiOiIxMDE15pel5bi45LiT5Lia5aSaIiwidXNlcl9pZCI6ImU4NGQyZTU1LTQ4YTMtNDM0OS04ZDVlLWM1MGUxNTg3Yjc5MyIsInVzZXJfbmFtZSI6IjEzODExMDg1MzI2IiwicHJvZHVjdF9pZCI6InU4YzMuMCIsInByb2R1Y3RfbmFtZSI6IllvblN1aXRlIn0sIlNFRVNJT05JRCI6ImE0NjIxMjZkMzFlMjBkZGIiLCJTRUVTSU9OREFURSI6MTYxMTY0ODA0NTk2MCwiQU5TQVBQSUQiOiJhZDliOTEwZjA3MTA5NTJmIiwiQU5TJERFQlVHIjoyLCJBTlNVUExPQURVUkwiOiJodHRwczovL2FydC5kaXdvcmsuY29tLyIsIkZSSVNUREFZIjoiMjAyMTAxMjYiLCJGUklTVElNRSI6ZmFsc2UsIkFSS19MT0dJTklEIjoiZTg0ZDJlNTUtNDhhMy00MzQ5LThkNWUtYzUwZTE1ODdiNzkzIiwiQVJLX0lEIjoiSlMyZTMwNzQwNDVjN2VhYmFmMzZjMTE4ZDIxMTEwZDc3ODJlMzAiLCJBUktGUklTVFBST0ZJTEUiOiIyMDIxLTAxLTI2IDE1OjA4OjIwLjA3NSIsIkFOU1NFUlZFUlRJTUUiOi00NTB9");
            InvocationInfoProxy.setTenantid("aodu0hnj");
            List<String> strings = orgComApi.funcOrgCodes();
            System.out.println(strings);
        } catch (Exception e) {
            log.error(String.format("birjc TestOrgService.test10:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void testdelegate1() throws BaseDocException {
        try {
            List<String> purchaseIds = new ArrayList<>();
            List<String> inventoryIds = new ArrayList<>();
            List<Integer> statusList = new ArrayList<>();
            String tenantId = "lcuz0ebc";
            String sysId = "diwork";
            // @ApiParam(name = "purchaseIds",description = "采购组织主键") List<String> var1, @ApiParam(name = "inventoryIds",description = "库存组织主键") List<String> var2, @ApiParam(name = "statusList",description = "状态列表") List<Integer> var3, @ApiParam(name = "tenantId",description = "租户标识id") String var4, @ApiParam(name = "sysId",description = "系统标识") String var5
            List<PurchaseDelegate> purchaseDelegates = delegateServiceApi.listPurDelegateByPursInvs(purchaseIds, inventoryIds, statusList, tenantId, sysId);
            System.out.println(purchaseDelegates);
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
