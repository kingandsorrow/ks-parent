package top.ks.client.web.helper.vo;

import top.ks.framework.base.entity.ResponseEntity;

/**
 * <b>类名称:</b>SkResponseEntity$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/10<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/10
 */
public class SkResponseEntity extends ResponseEntity {

    private String skOrderId;

    public String getSkOrderId() {
        return skOrderId;
    }

    public void setSkOrderId(String skOrderId) {
        this.skOrderId = skOrderId;
    }

    public SkResponseEntity() {
    }

    public SkResponseEntity(String errCode) {
        super(errCode);
    }

    public SkResponseEntity(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
