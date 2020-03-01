package top.ks.sso.consumer.resp;

import lombok.Getter;
import lombok.Setter;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.sso.consumer.bean.KsOssUserBean;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OssUserListResp extends ResponseEntity {

    private List<KsOssUserBean> ksOssUserBeanList;

    public OssUserListResp(ResultStatus resultStatus, List<KsOssUserBean> ksOssUserBeanList) {
        super(resultStatus);
        this.ksOssUserBeanList = ksOssUserBeanList;
    }
}
