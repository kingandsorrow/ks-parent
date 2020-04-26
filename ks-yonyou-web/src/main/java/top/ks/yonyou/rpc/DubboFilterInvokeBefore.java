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
        String token = "aHm_lvt_diwork=1571973383; Hm_lpvt_diwork=1574495177; acw_tc=3d950b3d15838302126097559e3a1a1aa2fa7c97e9c8b798bb5e42a18a; locale=zh_CN; PHPSESSID=4ucako4ao1mugg6k996n34c06t; at=d7182dc3-5f92-46b1-8ddc-87e63d78bd0b; yonyou_uid=a8a92298-37ef-4ff7-ae28-44b5f4f905c4; yonyou_uname=%25E5%25BC%25A0%25E4%25BA%259A%25E6%2597%25AD; yht_access_token=bttV1NSVXExdFFRbGpDU2pSN1lWVGc4cWdUSHByZ1pTajRNVW96WVZFbW1ZYjY5ZjkyUndUTGV2MWJwM2pGOXhQaHExUjlrd2hVSGNRL213bFpCbkdHQWJNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__1585565824563; wb_at=LMjqmuvspjSL4lnDCBbPcyIOu8qqpdjdtbkxnmxntbkntckbnl; eudiqz=qyic8c7oa8a92298-37ef-4ff7-ae28-44b5f4f905c4+82068071; YKJ_IS_DIWORK=1; YKJ_DIWORK_DATA=%7B%22data%22%3A%7B%22is_diwork%22%3A1%2C%22cur_qzid%22%3A%225417%22%7D%2C%22key%22%3A%2296312ca9386a1ffc4222c54d8688500f%22%7D; token=bttV1NSVXExdFFRbGpDU2pSN1lWVGc4cWdUSHByZ1pTajRNVW96WVZFbW1ZYjY5ZjkyUndUTGV2MWJwM2pGOXhQaHExUjlrd2hVSGNRL213bFpCbkdHQWJNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__1585565824563; ck_safe_chaoke_csrf_token=a16e814d11e640cc2f441a5711186a50; _DIWORK_SHUNT=prepublish; jwt_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODU2MTk5MTEsInNlc3Npb24iOiJ7XCJjbGllbnRJcFwiOlwiMTIzLjEwMy45LjhcIixcImNyZWF0ZURhdGVcIjoxNTg1NTY1ODI0LFwiZXh0XCI6e1wib3JnU3RhdHVzXCI6XCJtdWx0aVwiLFwieWh0X3VzZXJuYW1lXCI6XCJTVC00MDg5NjMtWlNCbW9LSkljV2R6UFZ2RnJycWUtZXVjLnlvbnlvdWNsb3VkLmNvbV9fYThhOTIyOTgtMzdlZi00ZmY3LWFlMjgtNDRiNWY0ZjkwNWM0XCIsXCJhZG1pblwiOmZhbHNlLFwieWh0X3VzZXJ0b2tlblwiOlwiUC9IUmZYT1JJSk9ES0ZGT1lpR1BDeU00RkxqTVFDYTkyekdHdEFLQURlbGg1RmFIM2xBT3V6THd3VzNhbEkzcE1ZUVRxT013MnlQM2pMc3FUTzFEalE9PVwiLFwieWh0X2FjY2Vzc190b2tlblwiOlwiYnR0VjFOU1ZYRXhkRkZSYkdwRFUycFNOMWxXVkdjNGNXZFVTSEJ5WjFwVGFqUk5WVzk2V1ZaRmJXMVpZalk1WmpreVVuZFVUR1YyTVdKd00ycEdPWGhRYUhFeFVqbHJkMmhWU0dOUkwyMTNiRnBDYmtkSFFXSk5ZVVJaTDJJMGNsRklUM040VlNzeVFsSjBXR0ZVZWxSS1FUTlpRMWd6WVd0cFprWmllaTh5VDJsZlgyVjFZeTU1YjI1NWIzVmpiRzkxWkM1amIyMC5fXzE1ODU1NjU4MjQ1NjNcIn0sXCJqd3RFeHBTZWNcIjo2MCxcImp3dFZhbGlkRGF0ZVwiOjE1ODU2MTk0NTgsXCJsYXN0RGF0ZVwiOjE1ODU2MTk4NTEsXCJsb2NhbGVcIjpcInpoX0NOXCIsXCJwcm9kdWN0TGluZVwiOlwiZGl3b3JrXCIsXCJzZXNzaW9uRXhwTWluXCI6MjE2MCxcInNlc3Npb25JZFwiOlwiTE1qcW11dnNwalNMNGxuRENCYlBjeUlPdThxcXBkamR0Ymt4bm14bnRia250Y2tibmxcIixcInNvdXJjZUlkXCI6XCJkaXdvcmtcIixcInRlbmFudElkXCI6XCJxeWljOGM3b1wiLFwidXNlcklkXCI6XCJhOGE5MjI5OC0zN2VmLTRmZjctYWUyOC00NGI1ZjRmOTA1YzRcIn0iLCJzdWIiOiJkaXdvcmsifQ.Zzc1g-bxriicuYY03DkfoxYSsdPqbNLY3C34jhUXPdU; SERVERID=670968c78ae839df5fa5e1160ca0c3aa|1585619954|1585619421";
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

