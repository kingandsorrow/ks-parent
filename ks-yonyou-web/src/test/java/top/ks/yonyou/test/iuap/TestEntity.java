package top.ks.yonyou.test.iuap;

import com.yonyou.iuap.bd.common.exception.BaseDocException;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import org.junit.Test;

import javax.annotation.Resource;

public class TestEntity extends TestWebApp {
    @Resource
    FuncOrgDataQryService funcOrgDataQryService;

    @Test
    public void test1() throws BaseDocException {
        String id = "1555117240733952";
        String tenantId = "g3qagrrz";
        String sysId = "diwork";
        String funcType = "adminorg";
        FuncOrg funcOrg = funcOrgDataQryService.getById(id, tenantId, sysId, funcType);
        System.out.println(funcOrg.toString());
    }
}
