package top.ks.oss.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Func;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.common.enums.ResultStatus;
import top.ks.common.user.SsoUser;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.oss.consumer.MenuServiceI;
import top.ks.oss.consumer.bean.KsFunctionBean;
import top.ks.oss.consumer.bean.OperatorDeatilBean;
import top.ks.oss.consumer.req.AddMenuReq;
import top.ks.oss.consumer.req.FunctionListReq;
import top.ks.oss.consumer.req.MenuListReq;
import top.ks.oss.consumer.resp.AddMenuResp;
import top.ks.oss.consumer.resp.FunctionListResp;
import top.ks.oss.consumer.resp.MenuListResp;
import top.ks.oss.provider.database.mapper.KsFunctionMapper;
import top.ks.oss.provider.database.model.KsFunction;
import top.ks.oss.provider.database.model.KsOperator;
import top.ks.oss.provider.database.model.OperatorDeatil;
import top.ks.oss.provider.database.service.KsRoleService;
import top.ks.oss.provider.util.FunctionUtil;
import top.ks.oss.provider.util.TreeUtil;
import top.ks.oss.provider.util.TreeUtils;
import top.ks.sso.consumer.LoginServiceI;
import top.ks.sso.consumer.req.SsoUserReq;
import top.ks.sso.consumer.resp.SsoUserResp;
import top.ks.sso.core.help.LoginFactory;

import javax.annotation.Resource;
import java.util.*;

@Component
public class MenuServiceIProxy implements MenuServiceI {
    private static final Log log = LogFactory.getLog(MenuServiceIProxy.class);
    @Resource
    private KsRoleService ksRoleService;
    @Resource
    private KsFunctionMapper ksFunctionMapper;

    @Override
    public MenuListResp menuList(MenuListReq menuListReq) throws ClassNotFoundException {
        if (Strings.isEmpty(menuListReq.getToken())) {
            log.info(LogFormat.formatMsg("MenuServiceIProxy.menuList", "", ""));
            return new MenuListResp(ResultStatus.PARAMS_NULL);
        }
        SsoUserReq ssoUserReq = new SsoUserReq();
        ssoUserReq.setToken(menuListReq.getToken());
        LoginServiceI loginServiceI = LoginFactory.getLoginServiceI(menuListReq.getToken());
        SsoUserResp ssoUserResp = loginServiceI.getUserByToken(ssoUserReq);
        if (!ssoUserResp.respSuc()) {
            return new MenuListResp(ResultStatus.CODE_FAIL);
        }
        SsoUser ssoUser = ssoUserResp.getSsoUser();
        KsOperator ksOperator = new KsOperator();
        ksOperator.setOperatorId(ssoUser.getUserId());
        ksOperator.setProjectId(ssoUser.getProjectId());
        OperatorDeatil operatorDeatil = ksRoleService.selectOperatorDetail(ksOperator);
        OperatorDeatilBean operatorDeatilBean = new OperatorDeatilBean();
        BeanUtil.copyProperties(operatorDeatil, operatorDeatilBean);
        operatorDeatilBean.setKsFunctionList(initMenuList(operatorDeatil.getKsFunctionList(), new HashMap<String, String>()));
        MenuListResp menuListResp = new MenuListResp(ResultStatus.SUCCESS);
        menuListResp.setOperatorDeatilBean(operatorDeatilBean);
        return menuListResp;
    }

    @Override
    public FunctionListResp functionList(FunctionListReq functionListReq) throws ClassNotFoundException {
        try {
            // 查询菜单列表
            List<KsFunction> ksFunctions = ksFunctionMapper.selectAllList(functionListReq.getProjectId());
            //List<KsFunction> rootFunction = ksFunctionMapper.selectRootList("0");
            List<KsFunctionBean> functionBeans = new ArrayList<>();
            ksFunctions.forEach(s -> {
                KsFunctionBean ksFunctionBean = FunctionUtil.convertFunctionBean(s);
                functionBeans.add(ksFunctionBean);
            });
            FunctionListResp functionListResp = new FunctionListResp(ResultStatus.SUCCESS);
            functionListResp.setKsFunctionBeanList(functionBeans);
            return functionListResp;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("MenuServiceIProxy.functionList", "system error::" + e.getMessage(), ""));
            return new FunctionListResp(ResultStatus.SYSTEM_ERROR);
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2020-05-28 21:49
     */
    @Override
    public AddMenuResp addMenu(AddMenuReq addMenuReq) {
        
        return null;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :初始化菜单
     * @author : brj
     * @CreateDate : 2018/11/5 15:37
     */
    private List<KsFunctionBean> initMenuList(List<KsFunction> ksFunctionList, Map<String, String> map) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<KsFunctionBean>();
        if (CollectionUtil.isEmpty(ksFunctionList)) {
            return ksFunctionBeans;
        }
        for (KsFunction ksFunction : ksFunctionList) {
            KsFunctionBean ksFunctionBean = FunctionUtil.convertFunctionBean(ksFunction);
            ksFunctionBean.setList(FunctionUtil.getChildMap(ksFunction.getFunctionId(), ksFunctionList, map));
            if (!map.containsKey(ksFunction.getFunctionId())) {
                ksFunctionBeans.add(ksFunctionBean);
            }
        }
        return ksFunctionBeans;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获得菜单实体bean
     * @author : brj
     * @CreateDate : 2018/11/5 21:20
     */
    private KsFunctionBean getKsFunctionBean(KsFunction ksFunction) {
        KsFunctionBean ksFunctionBean = new KsFunctionBean();
        ksFunctionBean.setMenuId(Long.parseLong(ksFunction.getFunctionId()));
        ksFunctionBean.setName(ksFunction.getTitle());
        ksFunctionBean.setUrl(ksFunction.getUrl());
        ksFunctionBean.setPerms(ksFunction.getAuthorize());
        ksFunctionBean.setType(ksFunction.getType());
        ksFunctionBean.setIcon(ksFunction.getIcon());
        //ksFunctionBean.setOrderNum((int) ksFunction.getOrderNum());
        ksFunctionBean.setOpen(false);
        return ksFunctionBean;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :获得这个子菜单
     * @author : brj
     * @CreateDate : 2018/11/5 21:20
     */
    private List<KsFunctionBean> getThisSubMenu(KsFunction ksFunction, List<KsFunction> ksFunctionList) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<KsFunctionBean>();
        for (KsFunction ks : ksFunctionList) {
            if (Strings.isNotEmpty(ksFunction.getFunctionId()) && ksFunction.getFunctionId().equals(ks.getParentId())) {
                KsFunctionBean ksFunctionBean = getKsFunctionBean(ks);
                ksFunctionBean.setParentId(Long.parseLong(ksFunction.getFunctionId()));
                ksFunctionBean.setParentName(ksFunction.getTitle());
                ksFunctionBeans.add(ksFunctionBean);
            }
        }
        return ksFunctionBeans;
    }
}
