package top.ks.oss.api.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>MenuAddResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/3<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/12/3
 */
public class MenuAddResp extends ResponseEntity {

    private String functionId;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public MenuAddResp() {
    }

    public MenuAddResp(String errCode) {
        super(errCode);
    }

    public MenuAddResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public MenuAddResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
