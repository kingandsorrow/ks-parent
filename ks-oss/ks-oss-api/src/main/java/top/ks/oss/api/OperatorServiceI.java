package top.ks.oss.api;

import top.ks.oss.api.req.*;
import top.ks.oss.api.resp.*;

/**
 * <b>类名称:</b>OperatorServiceI$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/29<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright 西安创意 2018/9/29
 */
public interface OperatorServiceI {

    LoginResp login(LoginReq loginReq);

    CheckTokenResp checkToken(String token);

    OperatorListResp operatorList(OperatorListReq req);

    RouterMapResp routerMap(RouterMapReq req);

    RoleListResp roleList(RoleListReq req);

    MenuListResp menuList(MenuListReq menuListReq);

    RoleAddResp roleAdd(RoleAddReq roleAddReq);

    NoButtonMenuRes noButtonMenu(NoButtonMenuReq noButtonMenuReq);

    MenuAddResp menuAdd(MenuAddReq menuAddReq);
}
