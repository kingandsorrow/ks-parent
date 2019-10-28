package top.ks.sso.provider.factory;

import java.util.Map;

/**
 * <b>类名称:</b>LoginFactory$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/6/20<br/>
 * <b>修改备注:</b><br/>
 *
 * <p>
 * Copyright KS 2018/6/20
 */

public class LoginFactory {

    private Map<String, LoginHandler> handlerMap;

    public LoginHandler getLoginHandler(String loginType) {
        return handlerMap.get(loginType);
    }

    public Map<String, LoginHandler> getHandlerMap() {
        return handlerMap;
    }

    public void setHandlerMap(Map<String, LoginHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }
}
