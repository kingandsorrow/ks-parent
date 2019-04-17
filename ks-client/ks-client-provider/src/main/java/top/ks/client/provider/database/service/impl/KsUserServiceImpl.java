package top.ks.sso.provider.database.service.impl;

import org.springframework.stereotype.Service;
import top.ks.sso.provider.database.mapper.KsUserAuthsMapper;
import top.ks.sso.provider.database.mapper.KsUserMapper;
import top.ks.sso.provider.database.model.KsUser;
import top.ks.sso.provider.database.model.KsUserAuths;
import top.ks.sso.provider.database.service.KsUserService;

import javax.annotation.Resource;

/**
 * <b>类名称:</b>KsUserServiceImpl$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/16<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/16
 */
@Service
public class KsUserServiceImpl implements KsUserService {
    @Resource
    private KsUserMapper ksUserMapper;
    @Resource
    private KsUserAuthsMapper ksUserAuthsMapper;

    @Override
    public int registerUser(KsUser ksUser, KsUserAuths ksUserAuths) {
        int row1 = ksUserMapper.insertSelective(ksUser);
        int row2 = ksUserAuthsMapper.insertSelective(ksUserAuths);
        return row2;
    }
}
