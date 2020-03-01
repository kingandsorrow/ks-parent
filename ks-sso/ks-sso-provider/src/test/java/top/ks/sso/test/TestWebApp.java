package top.ks.sso.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import top.ks.sso.provider.bootstrap.KsSsoProvider;
import top.ks.sso.provider.database.mapper.KsOssUserMapper;
import top.ks.sso.provider.database.model.KsOssUser;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KsSsoProvider.class)
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

    @Resource
    private KsOssUserMapper ksOssUserMapper;

    @Test
    public void testUserMapper() {
        PageHelper.startPage(1, 3);
        Page<KsOssUser> ksOssUsers = ksOssUserMapper.selectList("", "", "1");
        System.out.println(JSON.toJSON(ksOssUsers));
    }
}
