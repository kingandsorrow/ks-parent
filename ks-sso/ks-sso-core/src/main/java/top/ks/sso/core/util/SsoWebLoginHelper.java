package top.ks.sso.core.util;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import top.ks.common.constant.Const;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * @author xuxueli 2018-04-03
 */
public class SsoWebLoginHelper {


    /**
     * client logout, cookie only
     *
     * @param request
     * @param response
     */
    public static void removeSessionIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, Const.SSO_TOKEN);
    }

    /**
     * get sessionid by cookie
     *
     * @param request
     * @return
     */
    public static String getSessionIdByCookie(HttpServletRequest request) {
        String cookieSessionId = CookieUtil.getValue(request, Const.SSO_TOKEN);
        return cookieSessionId;
    }

}
