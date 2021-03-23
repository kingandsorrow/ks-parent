package top.ks.oss.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.common.util.ToolUtil;
import top.ks.oss.consumer.RoleServiceI;
import top.ks.oss.consumer.bean.KsRoleBean;
import top.ks.oss.consumer.req.AddRoleReq;
import top.ks.oss.consumer.req.DeleteRoleReq;
import top.ks.oss.consumer.req.RoleListReq;
import top.ks.oss.consumer.req.UpdateRoleReq;
import top.ks.oss.consumer.resp.AddRoleResp;
import top.ks.oss.consumer.resp.DeleteRoleResp;
import top.ks.oss.consumer.resp.RoleListResp;
import top.ks.oss.consumer.resp.UpdateRoleResp;
import top.ks.oss.provider.database.mapper.KsRoleFunctionMapper;
import top.ks.oss.provider.database.mapper.KsRoleMapper;
import top.ks.oss.provider.database.model.KsRole;
import top.ks.oss.provider.database.model.KsRoleFunction;
import top.ks.oss.provider.database.service.KsRoleService;
import top.ks.sso.consumer.bean.KsOssUserBean;

import java.util.*;

@Component
public class RoleServiceIProxy implements RoleServiceI {
    @Autowired
    private KsRoleMapper ksRoleMapper;
    @Autowired
    private KsRoleFunctionMapper ksRoleFunctionMapper;
    @Autowired
    private KsRoleService ksRoleService;

    public final static String INSERT_SIGN = "insert";
    public final static String UPDATE_SIGN = "update";

    @Override
    public RoleListResp roleList(RoleListReq roleListReq) {
        PageHelper.startPage(roleListReq.getPageIndex(), roleListReq.getPageSize());
        Page<KsRole> ksRoles = ksRoleMapper.roleList(roleListReq.getRoleName(), roleListReq.getProjectId());
        List<KsRoleBean> ksRoleBeans = convertBeanList(ksRoles);
        RoleListResp roleListResp = new RoleListResp(ResultStatus.SUCCESS, ksRoleBeans, ksRoles.getTotal());
        return roleListResp;
    }

    @Override
    public AddRoleResp addRole(AddRoleReq addRoleReq) {
        KsRole ksRole = convertKsRole(addRoleReq);
        List<KsRoleFunction> ksRoleFunctions = convertFunctions(ksRole, addRoleReq);
        int row = ksRoleService.roleAdd(ksRole, ksRoleFunctions);
        if (row <= 0) {
            return new AddRoleResp(ResultStatus.INSERT_FAIL);
        }
        AddRoleResp addRoleResp = new AddRoleResp(ResponseEntity.SUCCESS);
        addRoleResp.setRoleId(ksRole.getRoleId());
        addRoleResp.setRoleName(ksRole.getRoleName());
        return addRoleResp;
    }

    @Override
    public UpdateRoleResp updateRole(UpdateRoleReq updateRoleReq) {
        KsRole ksRole = convertKsRole(updateRoleReq);
        Map<String, List<KsRoleFunction>> functionsMap = convertUpdateFunc(ksRole, updateRoleReq);
        int row = ksRoleService.updateRole(ksRole, functionsMap.get(UPDATE_SIGN), functionsMap.get(INSERT_SIGN));
        if (row <= 0) {
            return new UpdateRoleResp(ResultStatus.INSERT_FAIL);
        }
        UpdateRoleResp addRoleResp = new UpdateRoleResp(ResponseEntity.SUCCESS);
        addRoleResp.setRoleId(ksRole.getRoleId());
        addRoleResp.setRoleName(ksRole.getRoleName());
        return addRoleResp;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :转换update
     * @author : birjc
     * @CreateDate : 2021-03-23 00:10
     */
    private Map<String, List<KsRoleFunction>> convertUpdateFunc(KsRole ksRole, UpdateRoleReq updateRoleReq) {
        Map<String, List<KsRoleFunction>> map = new HashMap<>();
        List<KsRoleFunction> ksRoleFunctions = ksRoleFunctionMapper.selectByRoleId(ksRole.getRoleId(), updateRoleReq.getProjectId());
        List<String> menuIds = updateRoleReq.getMenuIdList();
        return map;
    }

    @Override
    public DeleteRoleResp deleteRole(DeleteRoleReq deleteRoleReq) {
        return null;
    }

    /**
     * @param :
     * @param ksRole
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-03-22 22:49
     */
    private List<KsRoleFunction> convertFunctions(KsRole ksRole, AddRoleReq addRoleReq) {
        List<KsRoleFunction> ksRoleFunctions = new ArrayList<>();
        List<String> functionIds = addRoleReq.getMenuIdList();
        for (String functionId : functionIds) {
            KsRoleFunction ksRoleFunction = new KsRoleFunction();
            ksRoleFunction.setFunctionId(functionId);
            ksRoleFunction.setProjectId(addRoleReq.getProjectId());
            ksRoleFunction.setRoleFunctionId(ToolUtil.generateId());
            ksRoleFunction.setRoleId(ksRole.getRoleId());
            ksRoleFunctions.add(ksRoleFunction);
        }
        return ksRoleFunctions;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-03-22 22:44
     */
    private KsRole convertKsRole(AddRoleReq roleListReq) {
        KsRole ksRole = new KsRole();
        BeanUtil.copyProperties(roleListReq, ksRole);
        ksRole.setRoleId(ToolUtil.generateId());
        ksRole.setCreateTime(new Date());
        return ksRole;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-03-22 22:44
     */
    private KsRole convertKsRole(UpdateRoleReq updateRoleReq) {
        KsRole ksRole = new KsRole();
        BeanUtil.copyProperties(updateRoleReq, ksRole);
        ksRole.setUpdateTime(new Date());
        return ksRole;
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
            ksRoleBean.setCreateTime(ToolUtil.getDateStr(ksRole.getCreateTime()));
            ksRoleBeans.add(ksRoleBean);
        }
        return ksRoleBeans;
    }
}
