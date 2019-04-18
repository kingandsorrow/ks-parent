package top.ks.oss.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.oss.api.OperatorServiceI;
import top.ks.oss.api.req.*;
import top.ks.oss.api.resp.*;
import top.ks.oss.web.util.HttpUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import static top.ks.common.enums.ResultStatus.SUCCESS;

//import com.google.code.kaptcha.Producer;

/**
 * <b>类名称:</b>MainController$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/1<br/>
 * <b>修改备注:</b><br/>
 *
 * <p>
 * Copyright 西安创意 2018/8/1
 */
@RestController
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);
    @Reference(version = "${demo.service.version}", url = "dubbo://localhost:9093")
    OperatorServiceI operatorServiceI;
    @Value("${project.id}")
    private String projectId;

    @RequestMapping("userMap")
    public OperatorListResp userMap(OperatorListReq operatorListReq) throws Exception {
        return operatorServiceI.operatorList(operatorListReq);
    }

    @RequestMapping("login")
    public LoginResp login(@RequestBody LoginReq loginReq) {
        loginReq.setProjectId(projectId);
        return operatorServiceI.login(loginReq);
    }

    @RequestMapping("operatorList")
    public OperatorListResp operatorList(OperatorListReq operatorListReq) {
        return operatorServiceI.operatorList(operatorListReq);
    }

    @RequestMapping("roleList")
    public RoleListResp roleList(RoleListReq roleListReq) {
        return operatorServiceI.roleList(roleListReq);
    }

    @RequestMapping("checkToken")
    public CheckTokenResp checkToken(HttpServletRequest request) {
        Map<String, String> headersMap = HttpUtil.getHeadersInfo(request);
        String token = headersMap.get("token");
        return operatorServiceI.checkToken(token);
    }


    @RequestMapping("loginOut")
    public LoginOutResp loginOut(LoginOutReq req) {
        return new LoginOutResp(SUCCESS);
    }

    @RequestMapping("routerMap")
    public RouterMapResp loginOut(RouterMapReq req) {
        RouterMapResp resp = operatorServiceI.routerMap(req);
        return resp;
    }

    @RequestMapping("menuList")
    public MenuListResp menuList(MenuListReq menuListReq) {
        menuListReq.setProjectId(this.projectId);
        MenuListResp menuListResp = operatorServiceI.menuList(menuListReq);
        return menuListResp;
    }

    @RequestMapping("roleAdd")
    public RoleAddResp roleAdd(@RequestBody RoleAddReq roleAddReq) {
        roleAddReq.setProjectId(this.getProjectId());
        RoleAddResp roleAddResp = operatorServiceI.roleAdd(roleAddReq);
        return roleAddResp;
    }

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @RequestMapping("noButtonMenu")
    public NoButtonMenuRes noButtonMenu(NoButtonMenuReq noButtonMenuReq) {
        noButtonMenuReq.setProjectId(this.getProjectId());
        NoButtonMenuRes noButtonMenuRes = operatorServiceI.noButtonMenu(noButtonMenuReq);
        return noButtonMenuRes;
    }

    @RequestMapping("menuAdd")
    public MenuAddResp menuAdd(@RequestBody MenuAddReq menuAddReq) {
        menuAddReq.setProjectId(this.getProjectId());
        MenuAddResp menuAddResp = operatorServiceI.menuAdd(menuAddReq);
        return menuAddResp;
    }


    /**
     * 获取method
     *
     * @param cls
     * @param methodName
     * @return
     */
    private Method getMethod(Class cls, String methodName) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }


    public void resultString(String json, HttpServletResponse response, boolean des) {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try {
            /*if (des) {
                json = DesUtil.encrypt(json);
            }*/
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("osskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskey".length());
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
