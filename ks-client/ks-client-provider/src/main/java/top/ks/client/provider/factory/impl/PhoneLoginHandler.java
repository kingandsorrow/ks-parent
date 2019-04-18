package top.ks.sso.provider.factory.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.common.constant.Const;
import top.ks.common.user.SsoUser;
import top.ks.sso.provider.database.mapper.KsUserAuthsMapper;
import top.ks.sso.provider.database.model.KsUser;
import top.ks.sso.provider.database.model.KsUserAuths;
import top.ks.sso.provider.database.service.KsUserService;
import top.ks.sso.provider.factory.LoginHandler;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <b>类名称:</b>PhoneLoginHandler$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b>手机号登录实现<br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/6/20<br/>
 * <b>修改备注:</b><br/>
 *
 * <p>
 * Copyright 西安创意 2018/6/20
 */
@Component
public class PhoneLoginHandler extends LoginHandler {

    private static final Log log = LogFactory.getLog(PhoneLoginHandler.class);

    @Resource
    private KsUserAuthsMapper ksUserAuthsMapper;
    @Resource
    private KsUserService ksUserService;


}
