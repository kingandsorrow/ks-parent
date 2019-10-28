package top.ks.cy.web.database.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ks.common.util.LogFormat;
import top.ks.cy.web.database.mapper.UserAwardMapper;
import top.ks.cy.web.database.mapper.UserNotWonMapper;
import top.ks.cy.web.database.model.UserAward;
import top.ks.cy.web.database.model.UserNotWon;
import top.ks.cy.web.database.service.UserAwardService;

import javax.annotation.Resource;

/**
 * <b>类名称:</b>UserAwardServiceImpl$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/15<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/11/15
 */
@Transactional
@Service
public class UserAwardServiceImpl implements UserAwardService {
    @Resource
    private UserAwardMapper userAwardMapper;
    @Resource
    private UserNotWonMapper userNotWonMapper;

    private static final Log log = LogFactory.getLog(UserAwardServiceImpl.class);

    @Override
    public int handleAward(UserAward userAward, UserNotWon userNotWon) {
        if (userNotWon != null) {
            int row1 = userNotWonMapper.deleteByPrimaryKey(userNotWon.getUserId());
        }
        int row2 = userAwardMapper.insertSelective(userAward);
        return row2;
    }

    @Override
    public int handleSendFail(UserNotWon userNotWon, Boolean flag) {
        int row1 = userAwardMapper.deleteByPrimaryKey(userNotWon.getUserId());
        if (!flag) {
            log.info(LogFormat.formatMsg("UserAwardServiceImpl.handleSendFail", "", ""));
            int row2 = userNotWonMapper.insertSelective(userNotWon);
        }
        return row1;
    }
}
