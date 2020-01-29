package top.ks.oss.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.web.basic.BasicController;

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
    public void handle(String serviceIName, String methodName, HttpServletRequest request, HttpServletResponse response) {
        String methodParam = request.getParameter("content");
        ResponseEntity responseEntity = null;
        try {
            responseEntity = invokeDubbo(serviceIName, methodName, methodParam, response, true);
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
     * @Description : 处理响应结果
     * @author : birjc
     * @CreateDate : 2019-10-28 20:25
     */
    public void resultString(String json, HttpServletResponse response, boolean des) {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try {
            if (des) {
                //TODO 处理响应结果加密的操作
            }
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
