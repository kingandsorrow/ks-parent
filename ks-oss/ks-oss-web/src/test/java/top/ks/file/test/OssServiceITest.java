package top.ks.file.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import top.ks.oss.consumer.OrgServiceI;
import top.ks.oss.consumer.req.OrgListReq;
import top.ks.oss.consumer.resp.OrgListResp;
import top.ks.oss.web.bootstrap.KsWebApplication;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KsWebApplication.class)
@WebAppConfiguration
public class OssServiceITest {
    @Resource
    private OrgServiceI ossServiceI;

    @Test
    public void testOrgList() {
        OrgListReq orgListReq = new OrgListReq();
        OrgListResp orgListResp = ossServiceI.orgList(orgListReq);
        System.out.println(JSON.toJSON(orgListResp));
    }
}
