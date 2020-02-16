package top.ks.oss.web.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.req.MenuListReq;
import top.ks.oss.web.basic.BasicController;
import top.ks.oss.web.basic.ReqEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <b>类名称:</b>CenterController<br/>
 * <b>类注释:</b>统一处理<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2019-09-08
 */
@RestController
@RequestMapping("/oss/")
public class CenterController extends BasicController {

    private static final Log log = LogFactory.getLog(CenterController.class);

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :统一调用dubbo入口（需要加密）
     * @author : birjc
     * @CreateDate : 2019-10-22 19:53
     */
    @RequestMapping("encrypt/handle")
    public void handle(@RequestBody ReqEntity reqEntity, HttpServletRequest request, HttpServletResponse response) {
        String methodParam = reqEntity.getContent();
        ResponseEntity responseEntity = null;
        try {
            responseEntity = invokeDubbo(reqEntity.getServiceIName(), reqEntity.getMethodName(), methodParam, response, true);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("CenterController.handle", "system error::" + e.getMessage(), ""));
            responseEntity = new ResponseEntity(ResultStatus.EXCEPTION);
        }
        resultString(responseEntity.toJsonStr(), response, true);
    }





    /**
     * @param :
     * @return :
     * @Method :
     * @Description :统一调用dubbo入口（需要加密）
     * @author : birjc
     * @CreateDate : 2019-10-22 19:53
     */
    @RequestMapping("checkToken")
    public void checkToken(String serviceIName, String methodName, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity(ResultStatus.SUCCESS);
        resultString(responseEntity.toJsonStr(), response, true);
    }


    public static void main(String[] args) {
        MenuListReq menuListReq = new MenuListReq();
        menuListReq.setToken("aaaa");
        System.out.println(JSON.toJSONString(menuListReq));
        String param = "{\"token\":\"aaaa\"}";
        MenuListReq req = JSON.parseObject(param, MenuListReq.class);
        System.out.println(req.toJsonStr());
    }
}
