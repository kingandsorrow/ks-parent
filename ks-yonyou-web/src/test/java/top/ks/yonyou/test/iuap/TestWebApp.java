package top.ks.yonyou.test.iuap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KsYonyouWebApplication.class)
@WebAppConfiguration
public class TestWebApp {

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
