package top.ks.oss.consumer;

import top.ks.oss.consumer.req.AddMenuReq;
import top.ks.oss.consumer.req.FunctionListReq;
import top.ks.oss.consumer.req.MenuListReq;
import top.ks.oss.consumer.req.NoButtonMenuReq;
import top.ks.oss.consumer.resp.AddMenuResp;
import top.ks.oss.consumer.resp.FunctionListResp;
import top.ks.oss.consumer.resp.MenuListResp;
import top.ks.oss.consumer.resp.NoButtonMenuResp;

public interface MenuServiceI {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 菜单列表
     * @author : birjc
     * @CreateDate : 2020-04-29 10:32
     */
    public MenuListResp menuList(MenuListReq menuListReq) throws ClassNotFoundException;

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获取功能列表
     * @author : birjc
     * @CreateDate : 2020-05-01 22:43
     */
    public FunctionListResp functionList(FunctionListReq functionListReq) throws ClassNotFoundException;

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :新增菜单
     * @author : birjc
     * @CreateDate : 2020-05-28 21:48
     */
    public AddMenuResp menuAdd(AddMenuReq addMenuReq);

    public NoButtonMenuResp noButtonMenu(NoButtonMenuReq noButtonMenuReq);
}
