package top.ks.yonyou.rpc;

import com.yonyou.cloud.middleware.iris.IBeforeInvoke;
import com.yonyou.cloud.middleware.iris.RemoteInvocation;
import com.yonyou.cloud.middleware.rpc.RPCRequest;
import com.yonyou.cloud.plugin.InvokeChain;
import com.yonyou.cloud.plugin.InvokeRequest;
import com.yonyou.cloud.plugin.InvokeResponse;

public class DubboFilterInvokeBefore implements IBeforeInvoke {
    public DubboFilterInvokeBefore() {
    }

    public void run(InvokeRequest invokeRequest, InvokeResponse invokeResponse, InvokeChain invokeChain) {
        RPCRequest rpcRequest = (RPCRequest) invokeRequest.getRequest(RPCRequest.class);
        RemoteInvocation remoteInvocation = rpcRequest.getRemoteInvocation();
        String token = "bttU2R1dHFJbWJYNUZleFhtSE9WZ1NLcFIzYUVLU1J0U21mVk0vWk9pVGZkYXVYSUtnd29IL0luTEM2UzE4N0F4eHQ2aWVLbExXREtiL2NEK2hlalFhcHpHRnk0a0RyQXp5c2FFN21DcDUwbStIUUJEN1c4OFF6cmlCY1RpYmhpbHpfX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__1582960729593";
        if (null == remoteInvocation.getAttribute("rpcToken") && null != token) {
            remoteInvocation.addAttribute("rpcToken", token);
        }

        if (null == remoteInvocation.getAttribute("dubboFuse")) {
            remoteInvocation.addAttribute("dubboFuse", true);
        }

        invokeChain.run(invokeRequest, invokeResponse, invokeChain);
    }

    public int order() {
        return 100;
    }

    public String getPluginName() {
        return DubboFilterInvokeBefore.class.getName();
    }
}

