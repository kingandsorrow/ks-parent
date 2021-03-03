package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.cloud.auth.sdk.client.AuthSDKClient;
import com.yonyou.cloud.auth.sdk.client.utils.http.EnumRequestType;
import com.yonyou.cloud.auth.sdk.client.utils.http.HttpResult;
import com.yonyou.iuap.bd.pub.param.*;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class TestRPCRest {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :分页查询组织列表
     * @author : birjc
     * @CreateDate : 2020-06-04 11:30
     */
    @Test
    public void testPageOrgList() {
        try {
            // daily环境地址
            //String url = "https://u8c-daily.yyuap.com/bd-rpc-adapter/rpc-adapter/gateway";
            // 沙箱环境地质
            String url = "https://bd-dbox.yyuap.com/bd-rpc-adapter/rpc-adapter/gateway";
            // 预发环境地址
            // String url = "https://bd-y3me-pre.diwork.com/bd-rpc-adapter/rpc-adapter/gateway";
            // 生产环境地址
            // String url = "https://bd.diwork.com/bd-rpc-adapter/rpc-adapter/gateway";
            AuthSDKClient client = new AuthSDKClient("4ceHfnmlKbR1OyW9", "Up6wkdGE5w3CgKnJG864U7D9uJRHsH");

            Map<String, List<String>> paramMap = new HashMap<>();
            paramMap.put("serviceName",
                    Collections.singletonList("com.yonyou.iuap.data.service.itf.FuncOrgDataQryService"));
            paramMap.put("methodName", Collections.singletonList("pagination"));
            JSONArray array = new JSONArray();
            int pageIndex = 1;//当前页码，从1开始
            int pageSize = 10;//当前页大小
            List<ConditionVO> conditionVOS = new ArrayList<>();
            ConditionVO enableVO = getEnableConditionVO();
            conditionVOS.add(enableVO);
            ConditionVO orgTypeVO = getOrgTypeConditonVO();
            conditionVOS.add(orgTypeVO);
            // 排序字段
            List<Order> orderList = new ArrayList<>();
            String defaultOrderAttrFirst = "orgtype";
            Order firstOrder = new Order(defaultOrderAttrFirst, Direction.DESC);// BPAAS-2214
            orderList.add(firstOrder);
            String defaultOrderAttr = "displayorder";
            Order order = new Order(defaultOrderAttr, Direction.ASC);
            orderList.add(order);
            String tenantId = "ev3qqwud";// 租户id 友互通
            String sysId = "diwork";// 默认填diwork
            String funcType = OrgFunc.PURCHASE_ORG.getCode(); // 采购组织code purchaseorg 销售组织codesalesorg
            array.add(pageIndex);
            array.add(pageSize);
            array.add(conditionVOS);
            array.add(orderList);
            array.add(tenantId);
            array.add(sysId);
            array.add(funcType);
            paramMap.put("args", Collections.singletonList(array.toJSONString()));
            HttpResult result = client.execute(url, paramMap, null, EnumRequestType.POST);
            System.out.println(result.getResponseString());
        } catch (Exception e) {
            log.error(String.format("birjc TestRPCRest.testPageOrgList:: %s, %s", "system error::" + e.getMessage(), e));
        }
    }

    @Test
    public void testOrgById() {
        // daily环境地址
        // String url = "https://u8c-daily.yyuap.com/bd-rpc-adapter/rpc-adapter/gateway";
        // 预发环境地址
        String url = "https://bd-y3me-pre.diwork.com/bd-rpc-adapter/rpc-adapter/gateway";
        // 生产环境地址
        // String url = "https://bd.diwork.com/bd-rpc-adapter/rpc-adapter/gateway";
        AuthSDKClient client = new AuthSDKClient("4ceHfnmlKbR1OyW9", "Up6wkdGE5w3CgKnJG864U7D9uJRHsH");

        Map<String, List<String>> paramMap = new HashMap<>();
        paramMap.put("serviceName",
                Collections.singletonList("com.yonyou.iuap.data.service.itf.FuncOrgDataQryService"));
        paramMap.put("methodName", Collections.singletonList("getById"));
        JSONArray array = new JSONArray();
        String id = "1746236822491392";
        String tenantId = "ev3qqwud";// 租户id 友互通
        String sysId = "diwork";// 默认填diwork
        String funcType = OrgFunc.PURCHASE_ORG.getCode(); // 采购组织code purchaseorg 销售组织codesalesorg
        array.add(id);
        array.add(tenantId);
        array.add(sysId);
        array.add(funcType);
        paramMap.put("args", Collections.singletonList(array.toJSONString()));
        HttpResult result = client.execute(url, paramMap, null, EnumRequestType.POST);
        System.out.println(result.getResponseString());
    }

    @Test
    public void testOrgGateway() {
        // daily环境地址
        // String url = "https://u8c-daily.yyuap.com/bd-rpc-adapter/rpc-adapter/gateway";
        // 预发环境地址
        String url = "https://bd-y3me-pre.diwork.com/bd-rpc-adapter/rpc-adapter/gateway";
        // 生产环境地址
        // String url = "https://bd.diwork.com/bd-rpc-adapter/rpc-adapter/gateway";
        AuthSDKClient client = new AuthSDKClient("4ceHfnmlKbR1OyW9", "Up6wkdGE5w3CgKnJG864U7D9uJRHsH");

        Map<String, List<String>> paramMap = new HashMap<>();
        paramMap.put("serviceName",
                Collections.singletonList("com.yonyou.iuap.data.service.itf.FuncOrgDataQryService"));
        paramMap.put("methodName", Collections.singletonList("listByCondition"));
        JSONArray array = new JSONArray();
        List<ConditionVO> conditionVOS = new ArrayList<>();
        ConditionVO enableVO = getEnableConditionVO();
        conditionVOS.add(enableVO);
        ConditionVO orgTypeVO = getOrgTypeConditonVO();
        conditionVOS.add(orgTypeVO);
        // 排序字段
        List<Order> orderList = new ArrayList<>();
        String defaultOrderAttrFirst = "orgtype";
        Order firstOrder = new Order(defaultOrderAttrFirst, Direction.DESC);// BPAAS-2214
        orderList.add(firstOrder);
        String defaultOrderAttr = "displayorder";
        Order order = new Order(defaultOrderAttr, Direction.ASC);
        orderList.add(order);
        String tenantId = "ev3qqwud";// 租户id 友互通
        String sysId = "diwork";// 默认填diwork
        String funcType = OrgFunc.PURCHASE_ORG.getCode(); // 采购组织code purchaseorg 销售组织codesalesorg
        array.add(conditionVOS);
        array.add(orderList);
        array.add(tenantId);
        array.add(sysId);
        array.add(funcType);
        paramMap.put("args", Collections.singletonList(array.toJSONString()));

        HttpResult result = client.execute(url, paramMap, null, EnumRequestType.POST);
        System.out.println(result.getResponseString());
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :组织部门过滤条件纯组织单元orgtype值为1是组织单元2是部门
     * @author : birjc
     * @CreateDate : 2020-05-29 11:10
     */
    private ConditionVO getOrgTypeConditonVO() {
        ConditionVO orgtypeVO = new ConditionVO();
        orgtypeVO.setField("orgtype");
        orgtypeVO.setOperator(Operator.EQUAL);
        orgtypeVO.setValue("1");
        orgtypeVO.setDatatype(DataType.NUMBER);
        return orgtypeVO;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 组织启用条件 0未启用 1启用 2停用 in 条件
     * @author : birjc
     * @CreateDate : 2020-05-29 11:08
     */
    private ConditionVO getEnableConditionVO() {
        ConditionVO enableVO = new ConditionVO();
        enableVO.setField("enable");
        enableVO.setOperator(Operator.IN);
        List<ConditionVO> values = new ArrayList<>();
        ConditionVO zero = new ConditionVO();
        zero.setValue("0");
        values.add(zero);
        ConditionVO one = new ConditionVO();
        one.setValue("1");
        values.add(one);
        ConditionVO two = new ConditionVO();
        two.setValue("2");
        values.add(two);
        enableVO.setConditionList(values);
        return enableVO;
    }
}
