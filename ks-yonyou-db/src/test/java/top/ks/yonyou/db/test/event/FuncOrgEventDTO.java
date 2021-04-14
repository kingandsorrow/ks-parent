package top.ks.yonyou.db.test.event;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class FuncOrgEventDTO extends BaseEventDTO {
    private static final long serialVersionUID = 3830116662978262764L;

    /**
     * 主键
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 英文
     */
    private String name2;

    /**
     * 中文繁体
     */
    private String name3;

    /**
     * 法文
     */
    private String name4;

    /**
     * 备用
     */
    private String name5;

    /**
     * 备用
     */
    private String name6;


    /*
     * 多语name
     * */
    private Map<String, String> multiLangName;

    /**
     * 简称
     */
    private String shortname;

    /**
     * 上级节点
     */
    private String parentid;

    /**
     * 租户标识
     */
    private String tenantid;

    /**
     * 系统标识
     */
    private String sysid;

    /**
     * 时间戳
     */
    private Date ts;

    /**
     * 逻辑删除标识
     */
    private Integer dr;

    /**
     * 启用状态
     */
    private Integer enable;

    /**
     * 生效日期
     */
    private Date effectivedate;

    /**
     * 失效日期
     */
    private Date expirationdate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date creationtime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifiedtime;

    /**
     * 显示顺序
     */
    private Integer displayorder;

    /**
     * 组织形态
     */
    private String orgform;

    /**
     * 组织单元主键
     */
    private String orgid;

    /**
     * 对应合同主体
     */
    private String corpid;

    /**
     * 所在地点
     */
    private String locationid;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 分管领导
     */
    private String branchleader;

    /**
     * 纳锐人识别号
     */
    private String taxpayerid;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 描述
     */
    private String description;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 组织类型
     */
    private Integer orgtype;

    /**
     * 组织级别
     */
    private String orglevel;

    /**
     * 部门性质
     */
    private String depttype;

    /**
     * 组织ID
     */
    private String parentorgid;

    /**
     * 国家地区
     */
    private String region;

    /**
     * 时区
     */
    private String timezone;

    /**
     * 深度
     */
    private Integer depth;

    /**
     * 祖先路径
     */
    private String ancestorpath;

    /*汇率类型*/
    private String exchangerate;

    /*语言*/
    private String language;

    /*0 代表纯部门 1代表组织单元*/
    private Integer isbizunit;

    /**
     * 外部组织标志
     */
    private Integer externalOrg;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

    public String getName6() {
        return name6;
    }

    public void setName6(String name6) {
        this.name6 = name6;
    }

    public Map<String, String> getMultiLangName() {
        return multiLangName;
    }

    public void setMultiLangName(Map<String, String> multiLangName) {
        this.multiLangName = multiLangName;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Integer getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    public String getOrgform() {
        return orgform;
    }

    public void setOrgform(String orgform) {
        this.orgform = orgform;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getBranchleader() {
        return branchleader;
    }

    public void setBranchleader(String branchleader) {
        this.branchleader = branchleader;
    }

    public String getTaxpayerid() {
        return taxpayerid;
    }

    public void setTaxpayerid(String taxpayerid) {
        this.taxpayerid = taxpayerid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(Integer orgtype) {
        this.orgtype = orgtype;
    }

    public String getOrglevel() {
        return orglevel;
    }

    public void setOrglevel(String orglevel) {
        this.orglevel = orglevel;
    }

    public String getDepttype() {
        return depttype;
    }

    public void setDepttype(String depttype) {
        this.depttype = depttype;
    }

    public String getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(String parentorgid) {
        this.parentorgid = parentorgid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getAncestorpath() {
        return ancestorpath;
    }

    public void setAncestorpath(String ancestorpath) {
        this.ancestorpath = ancestorpath;
    }

    public String getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(String exchangerate) {
        this.exchangerate = exchangerate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getIsbizunit() {
        return isbizunit;
    }

    public void setIsbizunit(Integer isbizunit) {
        this.isbizunit = isbizunit;
    }

    public Integer getExternalOrg() {
        return externalOrg;
    }

    public void setExternalOrg(Integer externalOrg) {
        this.externalOrg = externalOrg;
    }
}
