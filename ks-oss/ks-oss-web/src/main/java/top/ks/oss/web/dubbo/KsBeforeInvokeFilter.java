package top.ks.oss.web.dubbo;

import com.alibaba.dubbo.rpc.*;
import top.ks.common.util.RequestEntity;

public class KsBeforeInvokeFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RequestEntity requestEntity = (RequestEntity) invocation.getArguments()[0];
        requestEntity.setProjectId("0");
        Result result = invoker.invoke(invocation);
        return result;
    }
}
