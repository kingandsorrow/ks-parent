package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

public class DeleteMenuResp extends ResponseEntity {

    public DeleteMenuResp() {
    }

    public DeleteMenuResp(String errCode) {
        super(errCode);
    }

    public DeleteMenuResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public DeleteMenuResp(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
