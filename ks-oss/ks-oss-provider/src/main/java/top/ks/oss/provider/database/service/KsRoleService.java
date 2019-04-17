package top.ks.oss.provider.database.service;

import top.ks.oss.provider.database.model.KsOperator;
import top.ks.oss.provider.database.model.KsRole;
import top.ks.oss.provider.database.model.KsRoleFunction;
import top.ks.oss.provider.database.model.OperatorDeatil;

import java.util.List;

/**
 * <b>类名称:</b>KsRoleService$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/10/4<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br               />
 * Copyright 西安创意 2018/10/4
 */
public interface KsRoleService {
    OperatorDeatil selectOperatorDetail(KsOperator ksOperator);

    int roleAdd(KsRole ksRole, List<KsRoleFunction> ksRoleFunctions);
}
