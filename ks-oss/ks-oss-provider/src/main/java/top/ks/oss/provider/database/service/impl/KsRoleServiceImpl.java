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
        operatorDeatil.setKsFunctionList(ksFunctionList);
        return operatorDeatil;
    }

    @Transactional
    @Override
    public int roleAdd(KsRole ksRole, List<KsRoleFunction> ksRoleFunctions) {
        int row1 = ksRoleMapper.insertSelective(ksRole);
        int row2 = ksRoleFunctionMapper.insertksRoleFunctions(ksRoleFunctions);
        return row2;
    }


}
