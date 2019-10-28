package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>RoleAddResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/1<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/1
 */
public class RoleAddResp extends ResponseEntity {
    public RoleAddResp() {
    }

    public RoleAddResp(String errCode) {
        super(errCode);
    }

    public RoleAddResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public RoleAddResp(ResultStatus paramError) {
        super(paramError);
    }
}
