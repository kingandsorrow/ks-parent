package com.crack;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.ks.common.util.LogFormat;
import top.ks.common.util.MD5;
import top.ks.common.util.ToolUtil;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class test {

    public static String getSign01(String pubStr, String busStr) throws Exception {
        Map<String, String> sysParam = new HashMap<String, String>();
        String[] arr = pubStr.split("&");
        for (String temp : arr)
            sysParam.put(temp.split("=")[0], temp.split("=")[1]);

        sysParam.remove("sign");
        sysParam.put("content", busStr);
        byte[] key = sysParam.get("appkey").getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key);
        return mac.digestHex(pubStr + "&content=" + busStr);
        //return SignUtil.sign(sysParam, SignUtil.SIGN_METHOD_HMACSHA256, sysParam.get("appkey"));
    }

    public static void main(String[] args) throws Exception {
        //aaatest();
        getPhone();
    }

    private static void aaatest() {
        String baseUrl = "http://www.sxunicom.cn/oppf";//能力平台地址

        Map<String, String> headers = new HashMap<>();
        headers.put("Cache-Control", "no-cache");
        Map<String, String> pubParam = buildPubParam();
        //build sign
        StringBuilder sb = new StringBuilder();
        for (String key : pubParam.keySet()) {
            sb.append(key + "=" + pubParam.get(key) + "&");
        }
        UniBSS uniBSS = new UniBSS();
        uniBSS.setOrigDomain("FLOW");
        uniBSS.setHomeDomain("UCRM");
        uniBSS.setbIPCode("FLW00001");
        uniBSS.setbIPVer("0100");
        uniBSS.setActivityCode("OPR00002");
        uniBSS.setActionCode("0");
        uniBSS.setActionRelation("0");
        Routing routing = new Routing();
        routing.setRouteType("00");
        routing.setRouteValue("19");
        uniBSS.setRouting(routing);
        uniBSS.setProcID("MP03512017060111400001");
        uniBSS.setTransIDO("MP03512017060111400001");
        uniBSS.setProcessTime(getTimeStamp());
        uniBSS.setTestFlag("1");
        uniBSS.setMsgSender("1900");
        uniBSS.setMsgReceiver("1901");
        uniBSS.setSvcContVer("0100");
        SvcCont svcCont = new SvcCont();

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setSerialNo("LLJY13466868998");
        requestInfo.setShoppingId("24242424");
        requestInfo.setFlowNum("100");
        requestInfo.setValidNum("2");
        requestInfo.setValidUnit("1");
        requestInfo.setShoppingType("1");
        requestInfo.setDesc("22222");
        requestInfo.setCardName("代缴代扣");
        requestInfo.setPrice("5");
        requestInfo.setTorderid("242424");
        requestInfo.setChannel("fxs");
        requestInfo.setPayMode("2");
        requestInfo.setTradeChannelId("0");
        requestInfo.setTradeStaffId("24323424234");
        requestInfo.setFlowPackagePrice("5");

        svcCont.setRequestInfo(requestInfo);
        uniBSS.setSvcCont(svcCont);
        Map<String, UniBSS> map = new HashMap<>();
        map.put("UniBSS", uniBSS);
        String sign = null;

        try {
            sign = getSign01(sb.toString().substring(0, sb.toString().length() - 1), JSON.toJSONString(map));
            pubParam.put("sign", sign);
            for (String key : pubParam.keySet()) {
                sb.append(key + "=" + pubParam.get(key) + "&");
            }
            String url = baseUrl + "?" + sb.toString().substring(0, sb.toString().length() - 1);
            String result = HttpClientHelper.sendHttp(url, null, JSON.toJSONString(map), headers);
            System.out.println("请求+++++++++++++++++" + JSON.toJSONString(map));
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject result1 = jsonObject.getJSONObject("result");
            JSONObject response = result1.getJSONObject("Response");
            String rspDesc = response.getString("RspDesc");
            String rspCode = response.getString("RspCode");
            System.out.println(rspCode + "+++++++++++++++++" + URLDecoder.decode(rspDesc, "UTF-8"));
            System.out.println(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getPhone() throws Exception {
        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Cache-Control", "no-cache");
            headers.put("Content-Type", "application/json; charset=UTF-8");
            headers.put("Accept", "application/json");
            headers.put("Accept-Encoding", "");
            Map<String, String> pubParam = buildPubParam();
            StringBuilder sb = new StringBuilder();
            for (String key : pubParam.keySet()) {
                sb.append(key + "=" + pubParam.get(key) + "&");
            }
            String baseUrl = "http://www.sxunicom.cn/oppf";
            QRYCONTROLLER_DEMO_REQ qrycontroller_demo_req = new QRYCONTROLLER_DEMO_REQ();
            qrycontroller_demo_req.setAppkey("cbsp.sub");
            qrycontroller_demo_req.setApptx(ToolUtil.getDateStr(new Date(), "yyyyMMddHHmmss") + ToolUtil.randomNums(11));
            Map<String, Object> params = new HashMap<String, Object>();
            MSG msg = new MSG();
            msg.setChannel_id("BFD35101");// BFD35101
            msg.setChannel_type("BFD35101");
            msg.setOp_city("BFD35101");
            msg.setOp_province("BFD35101");
            msg.setOp_district("BFD35101");
            msg.setOperator_id("TYZY0959");// BFD35101 TYZY0959
            msg.setSerial_number("17663748077");
            qrycontroller_demo_req.setMsg(msg);
            params.put("MSG", msg);
            UNI_BSS_ATTACHED uni_bss_attached = new UNI_BSS_ATTACHED();
            uni_bss_attached.setMedia_info("");
            UNI_BSS_HEAD uni_bss_head = new UNI_BSS_HEAD();
            uni_bss_head.setaPP_ID("z6Vssgvzdi");
            uni_bss_head.setReserved(new String[]{});
            uni_bss_head.settIMESTAMP(getTimeStamp());
            uni_bss_head.settRANS_ID(qrycontroller_demo_req.getApptx());
            String appId = uni_bss_head.getaPP_ID();
            String timeStamp = uni_bss_head.gettIMESTAMP();
            String trans_id = uni_bss_head.gettRANS_ID();
            String app_secret = "2uL68JPODMhFtUGEoR3wJwXElxxPaA89";
            String token = getToken(appId, timeStamp, trans_id, app_secret);
            uni_bss_head.settOKEN(token);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("UNI_BSS_HEAD", uni_bss_head);
            Map<String, Object> bodyMap = new HashMap<String, Object>();
            bodyMap.put("QRYCONTROLLER_DEMO_REQ", qrycontroller_demo_req);
            map.put("UNI_BSS_BODY", bodyMap);
            map.put("UNI_BSS_ATTACHED", uni_bss_attached);
            String aa = JSON.toJSONString(map);
            System.out.println("aa is.." + aa);
            String sign = getSign01(sb.toString().substring(0, sb.toString().length() - 1), JSON.toJSONString(map));
            pubParam.put("sign", sign);
            for (String key : pubParam.keySet()) {
                sb.append(key + "=" + pubParam.get(key) + "&");
            }
            String url = baseUrl + "?" + sb.toString().replaceAll(" ", "").substring(0, sb.toString().replaceAll(" ", "").length() - 1);
            log.info(LogFormat.formatMsg("OnlineDGCPServiceImpl.selectUserTwoiSetMeal", "HttpClientHelper url is23:", url));
            String result = HttpClientHelper.sendHttp(url, null, JSON.toJSONString(map), headers);
            log.info(LogFormat.formatMsg("OnlineDGCPServiceImpl.selectUserTwoiSetMeal", "HttpClientHelper sendHttp res:", result));
        } catch (Exception e) {
            log.error(String.format("birjc test.getPhone:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    // 获取token
    private static String getToken(String appId, String timeStamp, String trans_id, String app_secret) {
        String tokenBefore = "APP_ID" + appId + "TIMESTAMP" + timeStamp + "TRANS_ID" + trans_id + app_secret;
        String token = MD5.Md5(tokenBefore);
        return token;
    }

    private static Map<String, String> buildPubParam() {
        Map<String, String> pubParam = new HashMap<String, String>();
        pubParam.put("method", "WFW_CX_A_18000549");
        pubParam.put("format", "json");
        pubParam.put("appId", "581430");
        pubParam.put("version", "3.8");
        pubParam.put("accessToken", "703c944d-bd2b-4962-ae9d-6594350260b8"); // 山西令牌
        pubParam.put("timestamp", getTimeStamp());
        pubParam.put("busiSerial", "12121212"); // 唯一的业务流水号
        pubParam.put("operId", "180000000272");
        pubParam.put("RegionId", "180000000272");
        pubParam.put("appkey", "f146fd8bfa297776857ea4b55bd3d24a");
        return pubParam;
    }

    public static String getPropery(String propName) {
        Properties prop = Resources.readAsProperties("configOnlineDGCP.properties");
        String value = prop.getProperty(propName);
        return value;
    }

    // 获取当前时间戳
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
