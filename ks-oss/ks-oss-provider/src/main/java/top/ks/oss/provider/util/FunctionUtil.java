package top.ks.oss.provider.util;

import cn.hutool.core.collection.CollectionUtil;
import top.ks.oss.consumer.bean.KsFunctionBean;
import top.ks.oss.provider.database.model.KsFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionUtil {

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :构造
     * @author : birjc
     * @CreateDate : 2020-05-02 15:07
     */
    public static List<KsFunctionBean> createTree(List<KsFunction> ksFunctions, List<KsFunction> rootFunction) {
        if (CollectionUtil.isEmpty(rootFunction)) {
            List<KsFunctionBean> ksFunctionBeans = convenrtBeanList(ksFunctions);
            return ksFunctionBeans;

        }
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<>();
        for (KsFunction root : rootFunction) {
            KsFunctionBean ksFunctionBean = convertFunctionBean(root);
            ksFunctionBean.setList(getChild(root.getFunctionId(), ksFunctions));
            ksFunctionBeans.add(ksFunctionBean);
        }
        return ksFunctionBeans;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 获取child
     * @author : birjc
     * @CreateDate : 2020-05-02 15:16
     */
    public static List<KsFunctionBean> getChild(String functionId, List<KsFunction> ksFunctions) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<>();
        ksFunctions.forEach(ksFunction -> {
            if (functionId.equals(ksFunction.getParentId())) {
                KsFunctionBean ksFunctionBean = convertFunctionBean(ksFunction);
                ksFunctionBean.setList(getChild(ksFunction.getFunctionId(), ksFunctions));
                ksFunctionBeans.add(ksFunctionBean);
            }
        });

        return ksFunctionBeans;
    }

    /**
     * @param :
     * @param map
     * @return :
     * @Method :
     * @Description : 获取child
     * @author : birjc
     * @CreateDate : 2020-05-02 15:16
     */
    public static List<KsFunctionBean> getChildMap(String functionId, List<KsFunction> ksFunctions, Map<String, String> map) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<>();
        ksFunctions.forEach(ksFunction -> {
            if (functionId.equals(ksFunction.getParentId())) {
                map.put(ksFunction.getFunctionId(), ksFunction.getParentId());
                KsFunctionBean ksFunctionBean = convertFunctionBean(ksFunction);
                ksFunctionBean.setList(getChildMap(ksFunction.getFunctionId(), ksFunctions, map));
                ksFunctionBeans.add(ksFunctionBean);
            }
        });

        return ksFunctionBeans;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2020-05-01 21:44
     */
    public static KsFunctionBean convertFunctionBean(KsFunction ksFunction) {
        if (ksFunction == null) {
            return null;
        }
        KsFunctionBean ksFunctionBean = new KsFunctionBean();
        ksFunctionBean.setMenuId(Long.parseLong(ksFunction.getFunctionId()));
        ksFunctionBean.setIcon(ksFunction.getIcon());
        ksFunctionBean.setName(ksFunction.getTitle());
        ksFunctionBean.setUrl(ksFunction.getUrl());
        if (ksFunction.getParentId() != null) {
            ksFunctionBean.setParentId(Long.parseLong(ksFunction.getParentId()));
        }
        ksFunctionBean.setType(ksFunction.getType());
        if (ksFunction.getOrderNum() != null) {
            ksFunctionBean.setOrderNum(Integer.parseInt(ksFunction.getOrderNum() + ""));
        }
        return ksFunctionBean;
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description : bean之间的转换
     * @author : birjc
     * @CreateDate : 2020-05-02 14:56
     */
    private static List<KsFunctionBean> convenrtBeanList(List<KsFunction> ksFunctions) {
        List<KsFunctionBean> ksFunctionBeans = new ArrayList<>();
        if (CollectionUtil.isEmpty(ksFunctions)) {
            return ksFunctionBeans;
        }
        for (KsFunction ksFunction : ksFunctions) {
            KsFunctionBean bean = convertFunctionBean(ksFunction);
            if (bean != null) {
                ksFunctionBeans.add(bean);
            }
        }
        return ksFunctionBeans;
    }
}
