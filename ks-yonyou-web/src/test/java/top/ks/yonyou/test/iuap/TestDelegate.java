package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.yonyou.diwork.service.IApplicationCacheService;
import com.yonyou.iuap.admin.entity.bo.AdminOrg;
import com.yonyou.iuap.bd.common.exception.BaseDocException;
import com.yonyou.iuap.context.InvocationInfoProxy;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.service.itf.BizDelegateApi;
import com.yonyou.iuap.data.service.itf.OrgDataService;
import com.yonyou.iuap.data.service.itf.TenantStatusApi;
import com.yonyou.iuap.international.MultiLangEnum;
import com.yonyou.iuap.international.MultiLangText;
import com.yonyou.iuap.org.biz.delegate.bo.BizDelegate;
import com.yonyou.iuap.org.biz.delegate.bo.PickingDelegate;
import com.yonyou.iuap.org.biz.delegate.bo.ProductStorageDelegate;
import com.yonyou.iuap.org.dto.BizDelegateQueryParam;
import com.yonyou.iuap.org.dto.TenantMultiOrgInfo;
import com.yonyou.iuap.staff.api.StaffBizApi;
import com.yonyou.iuap.staff.dto.ComparisionTableEnum;
import com.yonyou.iuap.staff.dto.ComparisionTableResult;
import com.yonyou.iuap.staff.dto.ComparisonTableParam;
import com.yonyou.workbench.model.ApplicationVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouWebApplication.class)
public class TestDelegate {

    @Autowired
    private BizDelegateApi bizDelegateApi;

    @Autowired
    private TenantStatusApi tenantStatusApi;
    @Autowired
    private OrgDataService orgDataService;

    @Autowired
    private StaffBizApi staffBizApi;

    /*@Autowired
    private IApplicationCacheService iApplicationCacheService;*/


    /*@Test
    public void test4() {
        String tenantId = "aa16krcl";
        List<ApplicationVO> applicationVOS = iApplicationCacheService.findByTenantId(tenantId);
        System.out.println(JSON.toJSONString(applicationVOS));
    }*/

    @Test
    public void test3() {
        ComparisonTableParam comparisonTableParam = new ComparisonTableParam();
        List<String> orgPermissionList = new ArrayList<>();
        orgPermissionList.add("0005780bf9854c3e903fd0ae63084de2");
        List<ComparisionTableEnum> docCodes = new ArrayList<>();
        ComparisionTableEnum comparisionTableEnum = ComparisionTableEnum.STAFFTYPE;
        docCodes.add(comparisionTableEnum);
        List<Integer> statusCodes = new ArrayList<>();
        statusCodes.add(1);
        comparisonTableParam.setDocCodes(docCodes);
        comparisonTableParam.setOrgPermissionList(orgPermissionList);
        comparisonTableParam.setStatusList(statusCodes);
        InvocationInfoProxy.setTenantid("q6ftkh61");
        InvocationInfoProxy.setLocale(MultiLangEnum.CN.getCode());
        InvocationInfoProxy.setToken("bttSklYVE9VdlJxbkRSRC9WcTBPVGo1eGVuM1FuTFFhb2w5U280WVhnYmhoQzJKUjRTNXFJclBSaFF5TndER25YZXhCLzRMSTZFNGdFRDlqeHFUOGd1SkRHRnk0a0RyQXp5c2FFN21DcDUwbStIUUJEN1c4OFF6cmlCY1RpYmhpbHpfX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__20d1834b9b2896f86d0fe047cf7b8c88_1594734707123");

        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(staffBizApi.getComparisonTable(comparisonTableParam, "q6ftkh61", "diwork"));
            }
            System.out.println("");
        } catch (Exception e) {
            log.error(String.format("birjc TestDelegate.test3:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void testSave() throws BaseDocException {
        try {
            AdminOrg adminOrg = new AdminOrg();
            adminOrg.setChildList(new ArrayList<>());
            adminOrg.setCode("cgs-test");
            adminOrg.setCompanytype("45ebda24614f424abe5dfb04e00f737c");
            adminOrg.setDisplayorder(0);
            adminOrg.setEffectivedate(new Date());
            adminOrg.setEnable(1);
            adminOrg.setHassub(false);
            adminOrg.setInnercode("SHGOXPZY11");
            adminOrg.setIsdefault(0);
            MultiLangText multiLangText = new MultiLangText();
            multiLangText.addText(MultiLangEnum.CN, "陈光升-测试");
            adminOrg.setName(multiLangText);
            adminOrg.setNamespace("org");
            adminOrg.setOrgid("1738968356229376");
            adminOrg.setOrgtype(1);
            adminOrg.setStatus(0);
            adminOrg.setSubEntity(false);
            adminOrg.setSysid("diwork");
            adminOrg.setTaxpayertype(1);
            FuncOrg funcOrg = orgDataService.save("adminorg", adminOrg, "v9wyylox", "diwork");
            System.out.println(JSON.toJSON(funcOrg));
        } catch (Exception e) {
            log.error(String.format("birjc TestDelegate.testSave:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void testTenantStatus() throws BaseDocException {
        long start = System.currentTimeMillis();
        TenantMultiOrgInfo tenantMultiOrgInfo = tenantStatusApi.getTenantMultiOrgInfo("dsxxla86", "diwork");
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
        System.err.println("---------------------");
    }

    @Test
    public void testDelegateList() throws BaseDocException {
        try {
            String tenantId = "ev3qqwud";//友互通租户id
            String sysId = "diwork"; // 默认填diwork
            List<String> orgIds = new ArrayList<>();
            orgIds.add("1746246833410304");//填组织id
            BizDelegateQueryParam bizDelegateQueryParam = new BizDelegateQueryParam();
            bizDelegateQueryParam.setIdList(orgIds);
            bizDelegateQueryParam.setDefault(false);// 如果为true则只查默认的一条数据，否则根据组织id查所有
            bizDelegateQueryParam.setStatusList(new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
            //根据工厂组织id查询生产领料委托关系列表
            List<BizDelegate> bizDelegates = bizDelegateApi.listPickDeglistByFacId(bizDelegateQueryParam, tenantId, sysId);
            /*//根据库存组织id查询生产领料委托关系列表
            List<BizDelegate> bizDelegates1 = bizDelegateApi.listPickDeglistByInvId(bizDelegateQueryParam, tenantId, sysId);
            // 根据
            List<BizDelegate> bizDelegates2 = bizDelegateApi.listProdStDeglistByFacId(bizDelegateQueryParam, tenantId, sysId);
            List<BizDelegate> bizDelegates3 = bizDelegateApi.listProdStDeglistByInvId(bizDelegateQueryParam, tenantId, sysId);*/
            System.out.println(bizDelegates.size());
            //查询生产领料委托关系实体类
            for (BizDelegate bizDelegate : bizDelegates) {
                PickingDelegate pickingDelegate = (PickingDelegate) bizDelegate;
                System.out.println(JSON.toJSONString(pickingDelegate));
            }
            // 查询生产领料委托关系详情
            /*for (BizDelegate bizDelegate : bizDelegates2) {
                 ProductStorageDelegate productStorageDelegate = (ProductStorageDelegate) bizDelegate;
                System.out.println(JSON.toJSONString(productStorageDelegate));
            }*/
        } catch (Exception e) {
            log.error("system exception:", e);
            log.error(String.format("birjc TestDelegate.testDelegateList:: %s, %s", "system error::" + e.getMessage(), ""));
        }
    }

    @Test
    public void testDelegateIterList() throws BaseDocException {
        try {
            String tenantId = "x30fk78m";//友互通租户id
            String sysId = "diwork"; // 默认填diwork
            List<String> orgIds = new ArrayList<>();
            orgIds.add("1620032455381504");//填组织id
            BizDelegateQueryParam bizDelegateQueryParam = new BizDelegateQueryParam();
            bizDelegateQueryParam.setIdList(orgIds);
            bizDelegateQueryParam.setDefault(false);// 如果为true则只查默认的一条数据，否则根据组织id查所有
            bizDelegateQueryParam.setStatusList(new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
            //根据工厂组织id查询生产领料委托关系列表
            List<BizDelegate> bizDelegates = bizDelegateApi.listPickDeglistByFacId(bizDelegateQueryParam, tenantId, sysId);
            /*//根据库存组织id查询生产领料委托关系列表
            List<BizDelegate> bizDelegates1 = bizDelegateApi.listPickDeglistByInvId(bizDelegateQueryParam, tenantId, sysId);
            // 根据
            List<BizDelegate> bizDelegates2 = bizDelegateApi.listProdStDeglistByFacId(bizDelegateQueryParam, tenantId, sysId);
            List<BizDelegate> bizDelegates3 = bizDelegateApi.listProdStDeglistByInvId(bizDelegateQueryParam, tenantId, sysId);*/
            System.out.println(bizDelegates.size());
            //查询生产领料委托关系实体类
            for (BizDelegate bizDelegate : bizDelegates) {
                PickingDelegate pickingDelegate = (PickingDelegate) bizDelegate;
                System.out.println(JSON.toJSONString(pickingDelegate));
            }
            // 查询生产领料委托关系详情
            /*for (BizDelegate bizDelegate : bizDelegates2) {
                 ProductStorageDelegate productStorageDelegate = (ProductStorageDelegate) bizDelegate;
                System.out.println(JSON.toJSONString(productStorageDelegate));
            }*/
        } catch (Exception e) {
            log.error("system exception:", e);
            log.error(String.format("birjc TestDelegate.testDelegateList:: %s, %s", "system error::" + e.getMessage(), ""));
        }
    }

}
