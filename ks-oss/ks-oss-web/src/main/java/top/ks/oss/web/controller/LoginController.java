package top.ks.oss.web.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.common.constant.Const;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.LogFormat;
import top.ks.common.util.ResponseEntity;
import top.ks.common.util.Strings;
import top.ks.oss.web.basic.BasicController;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.core.help.LoginFactory;
import top.ks.sso.core.util.CookieUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import static top.ks.common.enums.ResultStatus.EXCEPTION;

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
 * Copyright KS 2018/8/1
 */
@RestController
public class LoginController extends BasicController {
    private static final Log log = LogFactory.getLog(LoginController.class);
    @Value("${project.id}")
    private String projectId;


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

    public static void main(String[] args) {
        System.out.println("osskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskey".length());
    }

    @RequestMapping("doLogin")
    public void doLogin(@RequestBody LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) {
        try {
            //1.判断是否登录来源（oss,client）
            int loginFrom = loginReq.getLoginFrom();
            loginReq.setProjectId(projectId);
            //2. 查询登录方式
            LoginServiceI loginServiceI = LoginFactory.getLoginServiceI(loginFrom + "");
            if (loginServiceI == null) {
                ResponseEntity responseEntity = new ResponseEntity(ResultStatus.LOGIN_WAY_ERROR);
                resultString(responseEntity.toJsonStr(), response, true);
                return;
            }
            //3.调用登录接口
            loginReq.setLoginWay(0);
            LoginResp loginResp = loginServiceI.doLogin(loginReq);
            if (!loginResp.respSuc()) {
                resultString(loginResp.toJsonStr(), response, true);
                return;
            }
            //4.往cookie 存token
            String token = loginResp.getToken();
            CookieUtil.set(response, Const.TOKEN, token, false);
            resultString(loginResp.toJsonStr(), response, true);
            return;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("LoginController.dologin", "system error::" + e.getMessage(), ""));
            ResponseEntity responseEntity = new ResponseEntity(EXCEPTION);
            resultString(responseEntity.toJsonStr(), response, true);
            return;
        }
    }

    @RequestMapping("loginOut")
    public void loginOut(HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEntity.SUCCESS);
        resultString(responseEntity.toJsonStr(), response, true);
    }
}
