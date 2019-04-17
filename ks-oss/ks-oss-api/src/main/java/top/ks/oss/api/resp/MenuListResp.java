package top.ks.oss.api.resp;

import top.ks.framework.base.entity.ResponseEntity;
import top.ks.oss.api.bean.KsFunctionBean;

import java.util.List;

/**
 * <b>类名称:</b>MenuListResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/30<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2018/11/30
 */
public class MenuListResp extends ResponseEntity {

    private List<KsFunctionBean> ksFunctionBeans;

    public List<KsFunctionBean> getKsFunctionBeans() {
        return ksFunctionBeans;
    }

    public void setKsFunctionBeans(List<KsFunctionBean> ksFunctionBeans) {
        this.ksFunctionBeans = ksFunctionBeans;
    }

    public MenuListResp() {
    }

    public MenuListResp(String errCode) {
        super(errCode);
    }

    public MenuListResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
