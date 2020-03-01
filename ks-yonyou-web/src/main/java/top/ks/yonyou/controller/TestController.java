package top.ks.yonyou.controller;

import com.yonyou.diwork.exception.BusinessException;
import com.yonyou.diwork.service.auth.IServiceIsolateService;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import com.yonyou.workbench.model.OrgPermVO;
import com.yonyou.workbench.param.OrgEntryParam;
import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.dubbo.DubboReferenceUtils;
import org.imeta.orm.schema.QueryCondition;
import org.imeta.orm.schema.QueryConditionGroup;
import org.imeta.orm.schema.QuerySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    /*@Autowired
    private IServiceIsolateService serviceIsolateService;
    @Autowired
    private FuncOrgDataQryService funcOrgDataQryService;*/

    @RequestMapping("/test")
    public String test() {
        return "test success";
    }

    /*@RequestMapping("testAuthService")
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcOrg;
    }*/

    @RequestMapping("testOrgDubboService")
    public List testOrgDubboService() {
        List<Map<String, Object>> list = null;
        try {
            list = this.getDefaultExchange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Map<String, Object>> getDefaultExchange() {
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
    }
}
