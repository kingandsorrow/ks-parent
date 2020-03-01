package top.ks.sso.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import org.junit.Test;
import top.ks.sso.provider.database.mapper.KsOssUserMapper;
import top.ks.sso.provider.database.model.KsOssUser;

import javax.annotation.Resource;

public class TestService {
    @Resource
    private KsOssUserMapper ksOssUserMapper;

    @Test
    public void testUserMapper() {
        Page<KsOssUser> ksOssUsers = ksOssUserMapper.selectList("", "", "0");
        System.out.println(JSON.toJSON(ksOssUsers));
    }
}
