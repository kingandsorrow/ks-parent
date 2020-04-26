package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.yonyou.diwork.service.auth.IServiceIsolateService;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KsYonyouWebApplication.class)
@WebAppConfiguration
public class TestWebApp {


    @Autowired
    private IServiceIsolateService serviceIsolateService;
    @Autowired
    private FuncOrgDataQryService funcOrgDataQryService;

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    public void testIris() {
        FuncOrg funcOrg = null;
        try {
            funcOrg = funcOrgDataQryService.getById("1603357683749120", "orq5s8og", "diwork", OrgFunc.ADMIN_ORG.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(funcOrg));
    }
}
