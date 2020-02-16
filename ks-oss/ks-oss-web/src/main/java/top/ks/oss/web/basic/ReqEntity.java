package top.ks.oss.web.basic;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by jason on 2016/12/27.
 */
public class ReqEntity implements Serializable {
    private String serviceIName;
    private String methodName;
    private String content;
    private String t;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServiceIName() {
        return serviceIName;
    }

    public void setServiceIName(String serviceIName) {
        this.serviceIName = serviceIName;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
