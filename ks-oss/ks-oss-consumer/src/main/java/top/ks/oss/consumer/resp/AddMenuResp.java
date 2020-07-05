package top.ks.oss.consumer.resp;

import top.ks.common.util.ResponseEntity;

public class AddMenuResp extends ResponseEntity {

    private String functionId;

    private String title;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
