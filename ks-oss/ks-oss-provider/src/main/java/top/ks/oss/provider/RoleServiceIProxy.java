package top.ks.oss.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ks.common.enums.ResultStatus;
import top.ks.oss.consumer.RoleServiceI;
import top.ks.oss.consumer.bean.KsRoleBean;
import top.ks.oss.consumer.req.RoleListReq;
import top.ks.oss.consumer.resp.RoleListResp;
import top.ks.oss.provider.database.mapper.KsRoleMapper;
import top.ks.oss.provider.database.model.KsRole;
import top.ks.sso.consumer.bean.KsOssUserBean;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleServiceIProxy implements RoleServiceI {
    @Autowired
    private KsRoleMapper ksRoleMapper;

    @Override
    public RoleListResp roleList(RoleListReq roleListReq) {
        PageHelper.startPage(roleListReq.getPageIndex(), roleListReq.getPageSize());
        Page<KsRole> ksRoles = ksRoleMapper.roleList(roleListReq.getRoleName(), roleListReq.getProjectId());
        List<KsRoleBean> ksRoleBeans = convertBeanList(ksRoles);
        RoleListResp roleListResp = new RoleListResp(ResultStatus.SUCCESS, ksRoleBeans, ksRoles.getTotal());
        return roleListResp;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2020-02-24 23:23
     */
    private List<KsRoleBean> convertBeanList(Page<KsRole> ksRoles) {
        List<KsRoleBean> ksRoleBeans = new ArrayList<>();
        if (CollectionUtil.isEmpty(ksRoles.getResult())) {
            return ksRoleBeans;
        }
        List<KsRole> roles = ksRoles.getResult();
        for (KsRole ksRole : roles) {
            KsRoleBean ksRoleBean = new KsRoleBean();
            BeanUtil.copyProperties(ksRole, ksRoleBean);
            ksRoleBeans.add(ksRoleBean);
        }
        return ksRoleBeans;
    }
}
