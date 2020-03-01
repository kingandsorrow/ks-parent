package top.ks.sso.provider.oss;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Component;
import top.ks.common.enums.ResultStatus;
import top.ks.sso.consumer.OssUserServiceI;
import top.ks.sso.consumer.bean.KsOssUserBean;
import top.ks.sso.consumer.req.OssUserListReq;
import top.ks.sso.consumer.resp.OssUserListResp;
import top.ks.sso.provider.database.mapper.KsOssUserMapper;
import top.ks.sso.provider.database.model.KsOssUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class OssUserServiceIProxy implements OssUserServiceI {
    @Resource
    private KsOssUserMapper ksOssUserMapper;

    @Override
    public OssUserListResp ossUserList(OssUserListReq ossUserListReq) {
        PageHelper.startPage(ossUserListReq.getPageNo(), ossUserListReq.getPageSize());
        Page<KsOssUser> ksOssUserPage = ksOssUserMapper.selectList(ossUserListReq.getNickName(), ossUserListReq.getOrgId(), ossUserListReq.getProjectId());
        System.out.println(JSON.toJSONString(ksOssUserPage));
        List<KsOssUserBean> ksOssUserBeanList = convertList(ksOssUserPage);
        OssUserListResp ossUserListResp = new OssUserListResp(ResultStatus.SUCCESS, ksOssUserBeanList);
        return ossUserListResp;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 转换list
     * @author : birjc
     * @CreateDate : 2020-02-23 23:34
     */
    private List<KsOssUserBean> convertList(Page<KsOssUser> ksOssUserPage) {
        List<KsOssUserBean> ksOssUserBeans = new ArrayList<>();
        if (ksOssUserPage.getPageSize() == 0) {
            return ksOssUserBeans;
        }
        List<KsOssUser> ksOssUsers = ksOssUserPage.getResult();
        for (KsOssUser ksOssUser : ksOssUsers) {
            KsOssUserBean ossUserBean = new KsOssUserBean();
            BeanUtil.copyProperties(ksOssUser, ossUserBean);
            ksOssUserBeans.add(ossUserBean);
        }
        return ksOssUserBeans;
    }
}
