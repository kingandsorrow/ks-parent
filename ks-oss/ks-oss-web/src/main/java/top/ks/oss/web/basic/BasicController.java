package top.ks.oss.web.basic;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.common.util.Strings;
import top.ks.common.util.ToolUtil;
import top.ks.oss.web.util.SpringHelper;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <b>类名称:</b>BasicController<br/>
 * <b>类注释:</b>基础Controller<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2019-10-22
 */

public class BasicController {

    private static final Log log = LogFactory.getLog(BasicController.class);

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 统一调用dubbo服务入口
     * @author : birjc
     * @CreateDate : 2019-10-22 19:58
     */
    protected ResponseEntity invokeDubbo(String serviceIName, String methodName, String methodParam, HttpServletResponse response, boolean b) throws InvocationTargetException, IllegalAccessException {
        if (Strings.hasEmptyStr(serviceIName, methodName)) {
            log.info(LogFormat.formatMsg("BasicController.invokeDubbo", "serviceIName is null|| methodNmae is null..", ""));
            return null;
        }
        Object service = SpringHelper.getBean(serviceIName);
        Class serviceIClass = service.getClass();
        Method method = ToolUtil.getMethod(serviceIClass, methodName);
        Object methodParams = getMethodParams(methodParam, method);
        if (methodParams == null) {
            ResponseEntity responseEntity = (ResponseEntity) method.invoke(service);
            return responseEntity;
        }
        ResponseEntity responseEntity = (ResponseEntity) method.invoke(service, methodParams);
        return responseEntity;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获得方法的参数的值，并转换
     * @author : birjc
     * @CreateDate : 2019-10-23 22:07
     */
    private Object getMethodParams(String methodParam, Method method) {
        // 1.如果没有参数
        if (method.getParameterTypes() == null || method.getParameterTypes().length == 0) {
            return null;
        }
        Class paramClass = method.getParameterTypes()[0];
        //2.判断方法参数类型是否是基本类型或者String类型
        if (ToolUtil.getPrimitive(paramClass) != null) {
            return methodParam;
        }
        //3.转换方法所需要的类
        Object object = JSON.parseObject(methodParam, paramClass);
        return object;
    }


}
