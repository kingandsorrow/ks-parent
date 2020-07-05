package top.ks.oss.provider.database.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ks.oss.provider.database.mapper.KsFunctionMapper;
import top.ks.oss.provider.database.mapper.KsRoleFunctionMapper;
import top.ks.oss.provider.database.mapper.KsRoleMapper;
import top.ks.oss.provider.database.model.*;
import top.ks.oss.provider.database.service.KsRoleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>类名称:</b>KsRoleServiceImpl$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/4<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright KS 2018/10/4
 */
@Service
public class KsRoleServiceImpl implements KsRoleService {
    @Resource
    private KsRoleMapper ksRoleMapper;
    @Resource
    private KsFunctionMapper ksFunctionMapper;
    @Resource
    private KsRoleFunctionMapper ksRoleFunctionMapper;

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2018/10/4 21:46
     */
    @Override
    public OperatorDeatil selectOperatorDetail(KsOperator ksOperator) {
        List<KsRole> ksRoleList = ksRoleMapper.selectByOperatorId(ksOperator.getOperatorId(), ksOperator.getProjectId());
        List<KsFunction> ksFunctionList = ksFunctionMapper.selectFunctionsByRoles(ksRoleList, ksOperator.getProjectId());
        OperatorDeatil operatorDeatil = new OperatorDeatil();
        BeanUtil.copyProperties(ksOperator, operatorDeatil);
        operatorDeatil.setKsRoleList(ksRoleList);
        //菜单functionList
        List<KsFunction> menuFunctionList = menuFunctions(ksFunctionList);
        operatorDeatil.setKsFunctionList(menuFunctionList);
        // Permissions
        List<String> permissions = permissionList(ksFunctionList);
        operatorDeatil.setPermissions(permissions);
        return operatorDeatil;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :权限列表
     * @author : birjc
     * @CreateDate : 2020-05-27 11:24
     */
    private List<String> permissionList(List<KsFunction> ksFunctionList) {
        List<String> permissions = new ArrayList<>();
        if (CollectionUtil.isEmpty(ksFunctionList)) {
            return permissions;
        }
        for (int i = 0; i < ksFunctionList.size(); i++) {
            String speator = (i != (ksFunctionList.size() - 1)) ? "," : "";
            if (ksFunctionList.get(i).getType() == 2) {
                permissions.add(ksFunctionList.get(i).getAuthorize() + speator);
            }
        }
        return permissions;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :转换functionList
     * @author : birjc
     * @CreateDate : 2020-05-26 21:11
     */
    private List<KsFunction> menuFunctions(List<KsFunction> ksFunctionList) {
        List<KsFunction> ksFunctions = new ArrayList<>();
        if (CollectionUtil.isEmpty(ksFunctionList)) {
            return ksFunctions;
        }
        for (KsFunction ksFunction : ksFunctionList) {
            if (ksFunction.getType() == 1 || ksFunction.getType() == 0) {
                ksFunctions.add(ksFunction);
            }

        }
        return ksFunctions;
    }

    @Transactional
    @Override
    public int roleAdd(KsRole ksRole, List<KsRoleFunction> ksRoleFunctions) {
        int row1 = ksRoleMapper.insertSelective(ksRole);
        int row2 = ksRoleFunctionMapper.insertksRoleFunctions(ksRoleFunctions);
        return row2;
    }


}
