package top.ks.yonyou.rpc;


public class DubboFilterInvokeBefore implements IBeforeInvoke {
    public DubboFilterInvokeBefore() {
    }

    public void run(InvokeRequest invokeRequest, InvokeResponse invokeResponse, InvokeChain invokeChain) {
RPCRequest rpcRequest = (RPCRequest) invokeRequest.getRequest(RPCRequest.class);
        RemoteInvocation remoteInvocation = rpcRequest.getRemoteInvocation();
        //token daily
        //String token = "PHPSESSID=76uvmig6fdb2smigcl9q2har24; ck_safe_chaoke_csrf_token=cf1ae806c4c40db80f523bede1417613; YKJ_IS_DIWORK=1; YKJ_DIWORK_DATA=%7B%22data%22%3A%7B%22is_diwork%22%3A1%2C%22cur_qzid%22%3A%2222100%22%7D%2C%22key%22%3A%226e23d8a932b40215b0c18a2b1cd73848%22%7D; at=e1a9de8a-7543-402a-aced-98cb55e7a2ed; yonyou_uid=0dc05721-d2b7-4acf-b779-daf20f341589; yonyou_uname=%25E7%2599%25BD%25E5%25BB%25BA%25E5%25AE%2587; wb_at=LMjnntj8JHZmn8A5MeMi7rNqExSjtubjtrdqjcZhkxkxxtZokbnl; yht_access_token=bttYUwrZndUS3pVM1NudWxVSGNlNFluSUFQRDFQOHBUc09uNVhyMFM1OTdPM0xIb0JtSnZBdVdSTU52aWVVc0t1a2RuaUtEV1RtdFdpdXB5Vkx2ekNBMjlmVFJVZ251YllkNmtnTURTY1crZE9MenFFTGZBTStvajlVWHJkbG1hdldfX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__5d00204ca490f36cd42ff4ec9168c734_1589854021042; ARK_STARTUP=eyJTVEFSVFVQIjp0cnVlLCJTVEFSVFVQVElNRSI6IjIwMjAtMDUtMTkgMTA6MTA6NTUuOTk4In0%3D; locale=en_US; jwt_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODk4NTg1MjMsInNlc3Npb24iOiJ7XCJjbGllbnRJcFwiOlwiMTAuMy4wLjc0XCIsXCJjcmVhdGVEYXRlXCI6MTU4OTg1NDI1MixcImRhdGFmb3JtYXRcIjpcIntcXFwiZGF0ZVRpbWVGb3JtYXRcXFwiOlxcXCJ5eXl5LU1NLWRkIEhIOm1tOnNzXFxcIixcXFwibnVtYmVyRm9ybWF0XFxcIjpcXFwiKyMsIyMjLCMjIywjIyMsIyMjLCMjI1suXSMjIyMjIyMjXFxcIixcXFwiZGF0ZUZvcm1hdFxcXCI6XFxcInl5eXktTU0tZGRcXFwiLFxcXCJ0aW1lRm9ybWF0XFxcIjpcXFwiSEg6bW06c3NcXFwifVwiLFwiZXh0XCI6e1wib3JnU3RhdHVzXCI6XCJtdWx0aVwiLFwieWh0X3VzZXJuYW1lXCI6XCJTVC0xMTctRlFPYW5vRkhDVGZUakVzVTRMeVotdThjLXVzZXItZGFpbHkueXl1YXAuY29tX18wZGMwNTcyMS1kMmI3LTRhY2YtYjc3OS1kYWYyMGYzNDE1ODlcIixcImFkbWluXCI6dHJ1ZSxcInlodF91c2VydG9rZW5cIjpcInc4dmFzS1k4WGVnNXAzb1h6ZUUvdkNpa2lIOEh2V2k0QnV3am10NndDY1hjY2ZWQStxbjd3VEtkcHZLM3ZkbW9nSCtLRncrN2RuU0RPZ3huR2VUVEt3PT1cIixcInlodF9hY2Nlc3NfdG9rZW5cIjpcImJ0dFlVd3JabmRVUzNwVk0xTnVkV3hWU0dObE5GbHVTVUZRUkRGUU9IQlVjMDl1TlZoeU1GTTFPVGRQTTB4SWIwSnRTblpCZFZkU1RVNTJhV1ZWYzB0MWEyUnVhVXRFVjFSdGRGZHBkWEI1Vmt4MmVrTkJNamxtVkZKVloyNTFZbGxrTm10blRVUlRZMWNyWkU5TWVuRkZUR1pCVFN0dmFqbFZXSEprYkcxaGRsZGZYM1U0WXkxemMyOHRaR0ZwYkhrdWVYbDFZWEF1WTI5dF9fNWQwMDIwNGNhNDkwZjM2Y2Q0MmZmNGVjOTE2OGM3MzRfMTU4OTg1NDI1Mjk5MVwifSxcImp3dEV4cFNlY1wiOjYwLFwiand0VmFsaWREYXRlXCI6MTU4OTg1NzkxNSxcImxhc3REYXRlXCI6MTU4OTg1ODQ2MyxcImxvY2FsZVwiOlwiZW5fVVNcIixcInByb2R1Y3RMaW5lXCI6XCJkaXdvcmtcIixcInNlc3Npb25FeHBNaW5cIjoyMTYwLFwic2Vzc2lvbklkXCI6XCJMTWpubnRqOEpIWm1uOEE1TWVNaTdyTnFFeFNqdHVianRyZHFqY1poa3hreHh0Wm9rYm5sXCIsXCJzb3VyY2VJZFwiOlwiZGl3b3JrXCIsXCJ0ZW5hbnRJZFwiOlwibDBmd3Nqc2ZcIixcInRpbWV6b25lXCI6XCJVVEMrMDg6MDBcIixcInVzZXJJZFwiOlwiMGRjMDU3MjEtZDJiNy00YWNmLWI3NzktZGFmMjBmMzQxNTg5XCJ9Iiwic3ViIjoiZGl3b3JrIn0.RemySh8gzZFi3qzp7wq-dKHqpIwqFKJP6v4tlnSvDeg; ARK_ID=JSfd7737073093c4e3135c247ffed33757fd77; FZ_STROAGE.yyuap.com=eyJTRUVTSU9OSUQiOiIwZDE1OTViMzMwMWM5OWYzIiwiU0VFU0lPTkRBVEUiOjE1ODk4NTg0NjUwNTIsIkFOU0FQUElEIjoiYWQ5YjkxMGYwNzEwOTUyZiIsIkFOUyRERUJVRyI6MiwiQU5TVVBMT0FEVVJMIjoiaHR0cHM6Ly9hcnQuZGl3b3JrLmNvbS8iLCJGUklTVERBWSI6IjIwMjAwNTE5IiwiRlJJU1RJTUUiOmZhbHNlLCJBUktfTE9HSU5JRCI6IjBkYzA1NzIxLWQyYjctNGFjZi1iNzc5LWRhZjIwZjM0MTU4OSIsIkFSS19JRCI6IkpTZmQ3NzM3MDczMDkzYzRlMzEzNWMyNDdmZmVkMzM3NTdmZDc3IiwiQVJLRlJJU1RQUk9GSUxFIjoiMjAyMC0wNS0xOSAxMDowNDo1Ny41MTEiLCJBTlNTRVJWRVJUSU1FIjotMjA5fQ%3D%3D";
        //token online
        InvocationInfoProxy.setLocale("en_US");

        String token = InvocationInfoProxy.getToken();

        if (null == remoteInvocation.getAttribute("rpcToken") && null != token) {
            remoteInvocation.addAttribute("rpcToken", token);
        }

        if (null == remoteInvocation.getAttribute("dubboFuse")) {
            remoteInvocation.addAttribute("dubboFuse", true);
        }
        remoteInvocation.addAttribute("locale", "en_US");

        invokeChain.run(invokeRequest, invokeResponse, invokeChain);

    }

    public int order() {
        return 100;
    }

    public String getPluginName() {
        return DubboFilterInvokeBefore.class.getName();
    }
}

