package top.ks.oss.provider;


import org.springframework.stereotype.Component;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.PermissionServiceI;
import top.ks.oss.consumer.req.LoginReq;
import top.ks.oss.consumer.resp.LoginResp;

/**
 * <b>类名称:</b>PermissionServiceIProxy$<br/>
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
@Component
public class PermissionServiceIProxy implements PermissionServiceI {

    public LoginResp test(LoginReq loginReq) {
        System.out.println("test success..");
        LoginResp loginResp = new LoginResp(ResultStatus.SUCCESS);
        loginResp.setToken("token");
        return loginResp;
    }
}
