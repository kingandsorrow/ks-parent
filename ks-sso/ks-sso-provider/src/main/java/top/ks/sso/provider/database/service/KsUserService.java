package top.ks.sso.provider.database.service;

import top.ks.sso.provider.database.model.KsUser;
import top.ks.sso.provider.database.model.KsUserAuths;

/**
 * <b>类名称:</b>KsUserService$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/16<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/16
 */
public interface KsUserService {
    int registerUser(KsUser ksUser);
}
