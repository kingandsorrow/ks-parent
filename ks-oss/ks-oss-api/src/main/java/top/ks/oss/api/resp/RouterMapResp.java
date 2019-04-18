package top.ks.oss.api.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

/**
 * <b>类名称:</b>RouterMapResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/2<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/11/2
 */
public class RouterMapResp extends ResponseEntity {

    private String serverRouterMap;

    public String getServerRouterMap() {
        return serverRouterMap;
    }

    public void setServerRouterMap(String serverRouterMap) {
        this.serverRouterMap = serverRouterMap;
    }

    public RouterMapResp() {
    }

    public RouterMapResp(String errCode) {
        super(errCode);
    }

    public RouterMapResp(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
