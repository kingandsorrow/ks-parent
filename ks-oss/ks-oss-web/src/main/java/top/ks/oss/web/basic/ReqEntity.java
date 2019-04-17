package top.ks.oss.web.basic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jason on 2016/12/27.
 */
public class ReqEntity {
    private String serviceIName;
    private String methodName;
    private String content;
    private HttpServletRequest request;

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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
