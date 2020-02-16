package top.ks.sso.provider.oss;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;
import top.ks.sso.consumer.OssUserServiceI;
import top.ks.sso.consumer.req.OssUserListReq;
import top.ks.sso.consumer.resp.OssUserListResp;
import top.ks.sso.provider.database.mapper.KsOssUserMapper;
import top.ks.sso.provider.database.model.KsOssUser;

import javax.annotation.Resource;

@Component
public class OssUserServiceIProxy implements OssUserServiceI {
    @Resource
    private KsOssUserMapper ksOssUserMapper;

    @Override
    public OssUserListResp ossUserList(OssUserListReq ossUserListReq) {
        PageHelper.startPage(ossUserListReq.getPageNo(), ossUserListReq.getPageSize());
        Page<KsOssUser> ksOssUserPage = ksOssUserMapper.selectList(ossUserListReq.getNickName(), ossUserListReq.getOrgId(), ossUserListReq.getProjectId());
        System.out.println(JSON.toJSONString(ksOssUserPage));
        return null;
    }
}
