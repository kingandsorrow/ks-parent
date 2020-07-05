package top.ks.oss.consumer.resp;

import lombok.*;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.bean.KsFunctionBean;

import java.util.List;

/**
 * <b>类名称:</b>FunctionListResp$<br/>
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
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FunctionListResp extends ResponseEntity {

    private List<KsFunctionBean> ksFunctionBeanList;

    public FunctionListResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public FunctionListResp(String errCode, List<KsFunctionBean> ksFunctionBeanList) {
        super(errCode);
        this.ksFunctionBeanList = ksFunctionBeanList;
    }

    public FunctionListResp(String errCode, String errMsg, List<KsFunctionBean> ksFunctionBeanList) {
        super(errCode, errMsg);
        this.ksFunctionBeanList = ksFunctionBeanList;
    }

    public FunctionListResp(ResultStatus resultStatus, List<KsFunctionBean> ksFunctionBeanList) {
        super(resultStatus);
        this.ksFunctionBeanList = ksFunctionBeanList;
    }
}
