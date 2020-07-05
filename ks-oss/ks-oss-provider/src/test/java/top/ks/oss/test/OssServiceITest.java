/*
package top.ks.oss.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.oss.consumer.MenuServiceI;
import top.ks.oss.consumer.OrgServiceI;
import top.ks.oss.consumer.req.FunctionListReq;
import top.ks.oss.consumer.req.OrgListReq;
import top.ks.oss.consumer.resp.FunctionListResp;
import top.ks.oss.consumer.resp.OrgListResp;
import top.ks.oss.provider.bootstrap.KsOssProvider;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsOssProvider.class)
public class OssServiceITest {
    @Resource
    private OrgServiceI orgServiceI;

    @Resource
    private MenuServiceI menuServiceI;

    @Test
    public void testOrgList() {
        OrgListReq orgListReq = new OrgListReq();
        OrgListResp orgListResp = orgServiceI.orgList(orgListReq);
        System.err.println(JSON.toJSON(orgListResp));
    }


    @Test
    public void testFunctionList() throws ClassNotFoundException {
        FunctionListReq functionListReq = new FunctionListReq();
        functionListReq.setProjectId("0");
        FunctionListResp functionListResp = menuServiceI.functionList(functionListReq);
        System.err.println(JSON.toJSONString(functionListResp));
    }

}
*/
