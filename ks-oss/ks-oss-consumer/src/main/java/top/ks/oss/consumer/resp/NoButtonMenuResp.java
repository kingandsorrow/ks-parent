package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.bean.KsFunctionBean;

import java.util.List;

public class NoButtonMenuResp extends ResponseEntity {

    private List<KsFunctionBean> ksFunctionBeanList;

    public NoButtonMenuResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public NoButtonMenuResp() {
    }

    public List<KsFunctionBean> getKsFunctionBeanList() {
        return ksFunctionBeanList;
    }

    public void setKsFunctionBeanList(List<KsFunctionBean> ksFunctionBeanList) {
        this.ksFunctionBeanList = ksFunctionBeanList;
    }
}
