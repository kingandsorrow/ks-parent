package top.ks.oss.consumer.resp;

import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;

public class AddMenuResp extends ResponseEntity {

    private String functionId;

    private String title;

    public AddMenuResp() {
    }

    public AddMenuResp(String errCode) {
        super(errCode);
    }

    public AddMenuResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public AddMenuResp(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
