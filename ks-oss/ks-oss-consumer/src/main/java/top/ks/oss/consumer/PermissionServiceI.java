package top.ks.oss.consumer;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.req.LoginReq;
import top.ks.oss.consumer.resp.LoginResp;

/**
 * <b>类名称:</b>PermissionServiceI$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/2<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/2
 */
public interface PermissionServiceI {

    //    FunctionListResp functionList(FunctionListReq functionListReq);
    public LoginResp test(LoginReq loginReq);

}
