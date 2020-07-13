package top.ks.oss.consumer.req;

import top.ks.common.util.RequestEntity;

public class DeleteMenuReq extends RequestEntity {

    private String functionId;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
