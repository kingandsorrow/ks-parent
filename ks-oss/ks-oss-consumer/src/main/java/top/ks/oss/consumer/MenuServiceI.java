package top.ks.oss.consumer;

import top.ks.oss.consumer.req.MenuListReq;
import top.ks.oss.consumer.resp.MenuListResp;

public interface MenuServiceI {

    public MenuListResp menuList(MenuListReq menuListReq) throws ClassNotFoundException;
}
