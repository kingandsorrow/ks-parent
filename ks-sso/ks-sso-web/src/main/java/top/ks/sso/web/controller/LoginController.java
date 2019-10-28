package top.ks.sso.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.ks.common.constant.Const;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.req.LoginOutReq;
import top.ks.sso.consumer.req.LoginReq;
import top.ks.sso.consumer.req.SsoUserReq;
import top.ks.sso.consumer.resp.LoginOutResp;
import top.ks.sso.consumer.resp.LoginResp;
import top.ks.sso.consumer.resp.SsoUserResp;
import top.ks.sso.core.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <b>类名称:</b>LoginController$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/12/14<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/12/14
 */
@RestController
public class LoginController {
    @Resource
    private LoginServiceI loginServiceI;

    private static final Log log = LogFactory.getLog(LoginController.class);

    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        // login check
        String token = CookieUtil.getValue(request, Const.TOKEN) == null ? request.getParameter(Const.TOKEN) : CookieUtil.getValue(request, Const.TOKEN);
        String redirectUrl = request.getParameter(Const.REDIRECT_URL);
        if (Strings.isNotEmpty(redirectUrl)) {
            redirectUrl = URLDecoder.decode(request.getParameter(Const.REDIRECT_URL), "UTF-8");
            request.getSession().setAttribute(Const.SESSION_REDIRECT_URL, redirectUrl);
        }
        if (Strings.isNotEmpty(token)) {
            SsoUserReq ssoUserReq = new SsoUserReq();
            ssoUserReq.setToken(token);
            SsoUserResp ssoUserResp = loginServiceI.getUserByToken(ssoUserReq);
            if (ssoUserResp.respSuc()) {
                // success redirect
                if (Strings.isNotEmpty(redirectUrl)) {
                    String redirectUrlFinal = redirectUrl + "?" + Const.TOKEN + "=" + token;
                    return "redirect:" + redirectUrlFinal;
                } else {
                    return "redirect:/";
                }
            }
        }
        CookieUtil.remove(request, response, Const.TOKEN);
        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(Const.REDIRECT_URL, request.getParameter(Const.REDIRECT_URL));
        return "login";
    }

    @RequestMapping("/doLogin")
    public String dologin(LoginReq loginReq, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        //1.调接口做登录
        LoginResp loginResp = loginServiceI.doLogin(loginReq);
        if (!loginResp.respSuc()) {
            redirectAttributes.addAttribute("errorMsg", loginResp.getErrMsg());
            redirectAttributes.addAttribute(Const.REDIRECT_URL, request.getParameter(Const.REDIRECT_URL));
            return "redirect:/toLogin";
        }
        //2.往cookie 存token
        String token = loginResp.getToken();
        CookieUtil.set(response, Const.TOKEN, token, false);
        //3、登录成功，跳转回跳地址
        String redirectUrl = (String) request.getSession().getAttribute(Const.SESSION_REDIRECT_URL);
        if (Strings.isNotEmpty(redirectUrl)) {
            String redirectUrlFinal = redirectUrl + "?" + Const.TOKEN + "=" + loginResp.getToken();
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(LoginReq loginReq, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        //1.从cookie获取token,调用dubbo接口从redis清除用户数据
        String token = CookieUtil.getValue(request, Const.TOKEN);
        if (Strings.isEmpty(token)) {
            log.info(LogFormat.formatMsg("LoginController.loginOut", "token is empty and redirect login..", ""));
            return "redirect:/toLogin";
        }
        LoginOutReq loginOutReq = new LoginOutReq();
        loginOutReq.setToken(token);
        LoginOutResp loginOutResp = loginServiceI.loginOut(loginOutReq);
        if (!loginOutResp.respSuc()) {
            redirectAttributes.addAttribute("errorMsg", loginOutResp.getErrMsg());
            redirectAttributes.addAttribute(Const.REDIRECT_URL, request.getParameter(Const.REDIRECT_URL));
            return "redirect:/toLogin";
        }
        //2.往cookie移除token
        CookieUtil.remove(request, response, loginOutReq.getToken());
        request.getSession().removeAttribute(Const.SESSION_REDIRECT_URL);
        String redirectUrl = request.getParameter(Const.REDIRECT_URL);
        if (Strings.isNotEmpty(redirectUrl)) {
            redirectUrl = URLDecoder.decode(request.getParameter(Const.REDIRECT_URL), "UTF-8");
            request.getSession().setAttribute(Const.SESSION_REDIRECT_URL, redirectUrl);
        }
        return "redirect:/toLogin";
    }

    @RequestMapping("orderList")
    public String orderList(String a) {
        System.out.println("a:" + a);
        return a;
    }
}
