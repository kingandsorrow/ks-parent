package top.ks.oss.consumer.bean;

import lombok.Getter;
import lombok.Setter;
import top.ks.common.util.BaseEntity;

import java.util.List;

@Setter
@Getter
public class KsFunctionBean extends BaseEntity {
    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private String parentId;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * ztree属性
     */
    private Boolean open;

    /**
     * 子节点
     */
    private List<KsFunctionBean> list;


}
