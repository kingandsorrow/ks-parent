package top.ks.oss.provider.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import top.ks.oss.consumer.bean.KsFunctionBean;
import top.ks.oss.provider.database.model.KsFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>类名称:</b>TreeUtil<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2020-04-29
 */
@Setter
@Getter
public class TreeUtil {

    private List<KsFunction> rootList; //根节点对象存放到这里

    private List<KsFunction> bodyList; //其他节点存放到这里，可以包含根节点

    public TreeUtil(List<KsFunction> rootList, List<KsFunction> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    public List<KsFunctionBean> getTree() {   //调用的方法入口
        if (bodyList != null && !bodyList.isEmpty()) {
            //声明一个map，用来过滤已操作过的数据
            Map<String, String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            List<KsFunctionBean> functionBeans = new ArrayList<>();
            rootList.forEach(beanTree -> getChild(beanTree, map, functionBeans));
            return functionBeans;
        }
        return null;
    }

    public void getChild(KsFunction beanTree, Map<String, String> map, List<KsFunctionBean> functionBeans) {
        List<KsFunctionBean> childList = Lists.newArrayList();
        KsFunctionBean ksFunctionBean = convertFunctionBean(beanTree);
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getFunctionId()))
                .filter(c -> beanTree.getFunctionId().equals(c.getParentId()))
                .forEach(c -> {
                    map.put(c.getFunctionId(), c.getParentId());
                    getChild(c, map, functionBeans);
                    KsFunctionBean childBean = convertFunctionBean(c);
                    if (childBean != null) {
                        childList.add(childBean);
                    }
                });
        ksFunctionBean.setList(functionBeans);
        functionBeans.add(ksFunctionBean);
//        beanTree.setList(childList);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2020-05-01 21:44
     */
    private KsFunctionBean convertFunctionBean(KsFunction ksFunction) {
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
        ksFunctionBean.setOrderNum(Integer.parseInt(ksFunction.getOrderNum() + ""));
        return ksFunctionBean;
    }

    public static void main(String[] args) {

    }
}

