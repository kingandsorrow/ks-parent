package top.ks.oss.consumer.req;


import com.alibaba.fastjson.JSON;
import top.ks.common.util.RequestEntity;

/**
 * <b>类名称:</b>LoginReq$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/29<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright KS 2018/9/29
 */
public class LoginReq extends RequestEntity {

    private String loginName;

    private String password;

    private String projectId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public static void main(String[] args) {
        LoginReq loginReq = new LoginReq();
        loginReq.setLoginName("a");
        loginReq.setPassword("b");
        loginReq.setProjectId("c");
        System.out.println(JSON.toJSON(loginReq));
        
    }
}