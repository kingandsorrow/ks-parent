package top.ks.oss.consumer.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.bean.KsOrgBean;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrgListResp extends ResponseEntity {

    private List<KsOrgBean> ksOrgBeans;

    public OrgListResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

}
