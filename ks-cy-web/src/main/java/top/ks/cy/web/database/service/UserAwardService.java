package top.ks.cy.web.database.service;

import top.ks.cy.web.database.model.UserAward;
import top.ks.cy.web.database.model.UserNotWon;

/**
 * <b>类名称:</b>UserAwardService$<br/>
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
public interface UserAwardService {
    int handleAward(UserAward userAward, UserNotWon userNotWon);

    int handleSendFail( UserNotWon userNotWon,Boolean flag);
}
