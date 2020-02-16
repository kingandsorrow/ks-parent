package top.ks.oss.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.common.util.ToolUtil;
import top.ks.oss.consumer.OperatorServiceI;
import top.ks.oss.consumer.bean.KsFunctionBean;
import top.ks.oss.consumer.bean.KsRoleBean;
import top.ks.oss.consumer.bean.OperatorDeatilBean;
import top.ks.oss.consumer.req.*;
import top.ks.oss.consumer.resp.*;
import top.ks.oss.provider.database.mapper.KsFunctionMapper;
import top.ks.oss.provider.database.mapper.KsOperatorMapper;
import top.ks.oss.provider.database.mapper.KsRoleMapper;
import top.ks.oss.provider.database.model.*;
import top.ks.oss.provider.database.service.KsRoleService;
import top.ks.oss.provider.util.Constant;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

import static top.ks.common.enums.ResultStatus.*;

/**
 * <b>类名称:</b>OperatorServiceIProxy$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/29<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright KS 2018/9/29
 */
@Component
public class OperatorServiceIProxy implements OperatorServiceI {

    private static final Log log = LogFactory.getLog(OperatorServiceIProxy.class);
    @Resource
    private KsOperatorMapper ksOperatorMapper;
    @Resource
    private KsRoleMapper ksRoleMapper;
    @Resource
    private KsRoleService ksRoleService;
    @Resource
    private KsFunctionMapper ksFunctionMapper;
    @Value("${oss.token.expire.time}")
    private String expireTime;
    @Value("${oss.api.key}")
    private String ossAPiKey;

    @Override
    public LoginResp login(LoginReq loginReq) {
        try {
            if (Strings.hasEmptyStr(loginReq.getLoginName(), loginReq.getPassword())) {
                log.info(LogFormat.formatMsg("OperatorServiceIProxy.login", "params has null ::" + loginReq.toJsonStr(), ""));
                return new LoginResp(PARAM_ERROR);
            }
            KsOperator ksOperator = ksOperatorMapper.selectByNameAndPassword(loginReq.getLoginName(), loginReq.getPassword(), loginReq.getProjectId());
            if (ksOperator == null) {
                log.info(LogFormat.formatMsg("OperatorServiceIProxy.login", "query operator is null::" + loginReq.toJsonStr(), ""));
                return new LoginResp(LOGIN_FAIL);
            }
            //查询该用户的权限
            OperatorDeatil operatorDeatil = ksRoleService.selectOperatorDetail(ksOperator);
            // 生成token
            long expireTime = Long.parseLong(this.getExpireTime());
            String jwt = createJWT(ToolUtil.getStringID(), ksOperator.getOperatorId(), JSON.toJSONString(operatorDeatil), expireTime);
            LoginResp loginResp = new LoginResp(SUCCESS);
            OperatorDeatilBean operatorDeatilBean = new OperatorDeatilBean();
            BeanUtil.copyProperties(operatorDeatil, operatorDeatilBean);
            loginResp.setOperatorDeatilBean(operatorDeatilBean);
            loginResp.setToken(jwt);
            return loginResp;
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.login", "system error::" + e.getMessage(), ""));
            return new LoginResp(SYSTEM_ERROR);
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :初始化菜单
     * @author : brj
     * @CreateDate : 2018/11/5 15:37
     */
    private List<KsFunctionBean> initMenuList(List<KsFunction> ksFunctionList) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<KsFunctionBean>();
        for (KsFunction ksFunction : ksFunctionList) {
            if ("0".equals(ksFunction.getParentId())) {
                KsFunctionBean ksFunctionBean = getKsFunctionBean(ksFunction);
                ksFunctionBean.setList(getThisSubMenu(ksFunction, ksFunctionList));
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

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :校验token
     * @author : brj
     * @CreateDate : 2018/10/6 15:40
     */
    @Override
    public CheckTokenResp checkToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(this.getOssAPiKey()))
                    .parseClaimsJws(token).getBody();
            String subject = claims.getSubject();
            OperatorDeatil operatorDeatil = JSON.parseObject(subject, OperatorDeatil.class);
            CheckTokenResp checkTokenResp = new CheckTokenResp(SUCCESS);
            OperatorDeatilBean operatorDeatilBean = new OperatorDeatilBean();
            BeanUtil.copyProperties(operatorDeatil, operatorDeatilBean);
            checkTokenResp.setOperatorDeatilBean(operatorDeatilBean);
            List<KsFunctionBean> menuList = initMenuList(operatorDeatil.getKsFunctionList());
            Set<String> permissions = initPermissions(operatorDeatil.getKsFunctionList());
            checkTokenResp.setPermissions(permissions);
            return checkTokenResp;
        } catch (ExpiredJwtException ex) {
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.checkToken", "token is expire::" + token, ""));
            return new CheckTokenResp(TOKEN_EXPIRE);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.checkToken", "system error::" + e.getMessage(), ""));
            return new CheckTokenResp(SYSTEM_ERROR);
        }
    }

    private Set<String> initPermissions(List<KsFunction> ksFunctionList) {
        Set<String> perms = new HashSet<>();
        for (KsFunction ksFunction : ksFunctionList) {
            if (Strings.isEmpty(ksFunction.getAuthorize())) {
                continue;
            }
            for (String authorize : ksFunction.getAuthorize().split(",")) {
                perms.add(authorize);
            }
        }
        return perms;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2018/10/6 18:48
     */
    @Override
    public OperatorListResp operatorList(OperatorListReq req) {
        List<KsOperator> ksOperatorList = ksOperatorMapper.operatorList(req.getLoginName(), req.getPageIndex() == 0 ? 0 : (req.getPageIndex() - 1) * req.getPageSize(), req.getPageSize());
        int count = ksOperatorMapper.selectCount(req.getLoginName());
        if (CollUtil.isNotEmpty(ksOperatorList)) {
            List<OperatorDeatilBean> operatorDeatilBeans = new ArrayList<OperatorDeatilBean>();
            for (KsOperator ksOperator : ksOperatorList) {
                OperatorDeatilBean operatorDeatilBean = new OperatorDeatilBean();
                BeanUtil.copyProperties(ksOperator, operatorDeatilBean);
                operatorDeatilBeans.add(operatorDeatilBean);
            }
            OperatorListResp operatorListResp = new OperatorListResp(SUCCESS);
            operatorListResp.setOperatorDeatilBeanList(operatorDeatilBeans);
            operatorListResp.setCount(count);
            return operatorListResp;
        }
        return new OperatorListResp(SUCCESS);
    }

    @Override
    public RouterMapResp routerMap(RouterMapReq req) {
        RouterMapResp routerMapResp = new RouterMapResp(SUCCESS);
        return routerMapResp;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :roleList
     * @author : brj
     * @CreateDate : 2018/11/28 0:01
     */
    @Override
    public RoleListResp roleList(RoleListReq req) {
        List<KsRole> ksRoles = ksRoleMapper.roleList(req.getRoleName(), req.getPageIndex() == 0 ? 0 : (req.getPageIndex() - 1) * req.getPageSize(), req.getPageSize());
        if (CollUtil.isEmpty(ksRoles)) {
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.roleList", "ksRoles is empty..", ""));
            return new RoleListResp(SUCCESS);
        }
        int totalCount = ksRoleMapper.selectCount(req.getRoleName());
        RoleListResp roleListResp = new RoleListResp(SUCCESS);
        roleListResp.setRoleList(converRoleList(ksRoles));
        roleListResp.setTotalCount(totalCount);
        return roleListResp;
    }

    @Override
    public MenuListResp menuList(MenuListReq menuListReq) {
       /* List<KsFunction> ksFunctionList = ksFunctionMapper.selectAllList(menuListReq.getProjectId());
        if (CollUtil.isEmpty(ksFunctionList)) {
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.menuList", "ksFunctionList is empty..", ""));
            return new MenuListResp(SUCCESS);
        }
        List<KsFunctionBean> ksFunctionBeans = convertMenuList(ksFunctionList);*/
        MenuListResp menuListResp = new MenuListResp(SUCCESS);
        /*menuListResp.setKsFunctionBeans(ksFunctionBeans);*/
        return menuListResp;
    }

    @Override
    public RoleAddResp roleAdd(RoleAddReq roleAddReq) {
        if (Strings.hasEmptyStr(roleAddReq.getProjectId(), roleAddReq.getRoleName())) {
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.roleAdd", "roleAddReq is.." + JSON.toJSONString(roleAddReq), ""));
            return new RoleAddResp(PARAM_ERROR);
        }
        KsRole ksRole = new KsRole();
        ksRole.setCreateTime(new Date());
        ksRole.setDescription(roleAddReq.getDescription());
        ksRole.setProjectId(roleAddReq.getProjectId());
        ksRole.setRoleName(roleAddReq.getRoleName());
        ksRole.setRoleId(IdUtil.simpleUUID());
        List<KsRoleFunction> ksRoleFunctions = initKsRoles(ksRole, roleAddReq.getMenuIdList());
        int row = ksRoleService.roleAdd(ksRole, ksRoleFunctions);
        RoleAddResp roleAddResp = new RoleAddResp(SUCCESS);
        return roleAddResp;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 查询没有按钮的菜单
     * @author : brj
     * @CreateDate : 2018/12/2 23:32
     */
    @Override
    public NoButtonMenuRes noButtonMenu(NoButtonMenuReq noButtonMenuReq) {
        List<KsFunction> ksFunctionList = ksFunctionMapper.noButtonMenu(noButtonMenuReq.getProjectId());
        if (CollUtil.isEmpty(ksFunctionList)) {
            log.info(LogFormat.formatMsg("OperatorServiceIProxy.menuList", "ksFunctionList is empty..", ""));
            return new NoButtonMenuRes(SUCCESS);
        }
        List<KsFunctionBean> ksFunctionBeans = convertMenuList(ksFunctionList);
        //添加顶级菜单
        KsFunctionBean root = new KsFunctionBean();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        ksFunctionBeans.add(root);
        NoButtonMenuRes noButtonMenuRes = new NoButtonMenuRes(SUCCESS);
        noButtonMenuRes.setKsFunctionBeans(ksFunctionBeans);
        return noButtonMenuRes;
    }

    @Override
    public MenuAddResp menuAdd(MenuAddReq menuAddReq) {
        MenuAddResp menuAddResp = new MenuAddResp(SUCCESS);
        KsFunction ksFunction = new KsFunction();
        ksFunction.setAuthorize(menuAddReq.getPerms());
        ksFunction.setCreateTime(new Date());
        ksFunction.setDescription(menuAddReq.getName());
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        ksFunction.setFunctionId(snowflake.nextId() + "");
        ksFunction.setParentId(menuAddReq.getParentId());
        ksFunction.setProjectId(menuAddReq.getProjectId());
        ksFunction.setIcon(menuAddReq.getIcon());
        ksFunction.setUrl(menuAddReq.getUrl());
        ksFunction.setTitle(menuAddReq.getName());
        ksFunction.setOrderNum(Byte.parseByte(menuAddReq.getOrderNum() + ""));
        int row = ksFunctionMapper.insertSelective(ksFunction);
        menuAddResp.setFunctionId(ksFunction.getFunctionId());
        return menuAddResp;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :生成角色权限列表
     * @author : brj
     * @CreateDate : 2018/12/2 16:55
     */
    private List<KsRoleFunction> initKsRoles(KsRole ksRole, String[] menuIdList) {
        List<KsRoleFunction> ksRoleFunctions = new ArrayList<>();
        for (String menuId : menuIdList) {
            KsRoleFunction ksRoleFunction = new KsRoleFunction();
            ksRoleFunction.setFunctionId(menuId);
            ksRoleFunction.setProjectId(ksRole.getProjectId());
            ksRoleFunction.setRoleId(ksRole.getRoleId());
            ksRoleFunction.setRoleFunctionId(IdUtil.simpleUUID());
            ksRoleFunctions.add(ksRoleFunction);
        }
        return ksRoleFunctions;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :转换菜单列表
     * @author : brj
     * @CreateDate : 2018/11/30 23:57
     */
    private List<KsFunctionBean> convertMenuList(List<KsFunction> ksFunctionList) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<>();
        for (KsFunction ksFunction : ksFunctionList) {
            KsFunctionBean ksFunctionBean = new KsFunctionBean();
            BeanUtil.copyProperties(ksFunction, ksFunctionBean);
            ksFunctionBean.setName(ksFunction.getTitle());
            ksFunctionBean.setMenuId(Long.parseLong(ksFunction.getFunctionId()));
            if (Constant.IS_PARENT_MENU.equals(ksFunction.getFunctionId())) {
                continue;
            }
            for (KsFunction function : ksFunctionList) {
                if (function.getFunctionId().equals(ksFunction.getParentId())) {
                    ksFunctionBean.setParentName(function.getTitle());
                }
            }
            ksFunctionBeans.add(ksFunctionBean);
        }
        return ksFunctionBeans;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2018/11/28 9:38
     */
    private List<KsRoleBean> converRoleList(List<KsRole> ksRoles) {
        List<KsRoleBean> ksRoleList = new ArrayList<>();
        for (KsRole role : ksRoles) {
            KsRoleBean ksRoleBean = new KsRoleBean();
            BeanUtil.copyProperties(role, ksRoleBean);
            ksRoleList.add(ksRoleBean);
        }
        return ksRoleList;
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2018/10/4 19:28
     */
    private String createOpertorDetail(KsOperator ksOperator) {
        return null;
    }

    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

//We will sign our JWT with our ApiKey secret
        String ossApiKey = this.getOssAPiKey();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(ossApiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey, signatureAlgorithm);

//if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

//Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getOssAPiKey() {
        return ossAPiKey;
    }

    public void setOssAPiKey(String ossAPiKey) {
        this.ossAPiKey = ossAPiKey;
    }
}
