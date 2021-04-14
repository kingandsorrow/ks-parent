package top.ks.yonyou.db.test.event;

import top.ks.yonyou.db.test.basetree.BaseTreeAO;

import java.util.Map;

public class AdminAO extends BaseTreeAO {

    private String code;

    private String name;

    private String parentorgid;

    private Integer orgtype;

    private String innercode;

    private String tenantid;

    private Map<String, Object> multiLangName;

    private Long creationtime;

    private String creator;

    private Integer depth;

    private Integer displayorder;

    private Integer dr;

    private Long effectivedate;

    private Integer enable;

    private String exchangerate;

    private Long expirationdate;

    private Integer externalOrg;

    private Integer isbizunit;

    private Long modifiedtime;

    private String modifier;

    private String name2;

    private String name3;

    private String orgid;

    private String sysid;

    private Long ts;

    private String shortname;
    private String region;
    private String countryzone;
    private String timezone;
    private String principal;
    private String path;
    private String currency;

    private String parent;

    private Long synchts;

    private String objid;

    private Integer isdefault;

    private Long pubts;

    private String tenant;

    private String sourceid;

    private Integer level;

    private Integer isEnd;

    private String companytype;

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

    public String getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(String parentorgid) {
        this.parentorgid = parentorgid;
    }

    public Integer getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(Integer orgtype) {
        this.orgtype = orgtype;
    }

    public String getInnercode() {
        return innercode;
    }

    public void setInnercode(String innercode) {
        this.innercode = innercode;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public Map<String, Object> getMultiLangName() {
        return multiLangName;
    }

    public void setMultiLangName(Map<String, Object> multiLangName) {
        this.multiLangName = multiLangName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
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

    public String getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(String exchangerate) {
        this.exchangerate = exchangerate;
    }

    public Integer getExternalOrg() {
        return externalOrg;
    }

    public void setExternalOrg(Integer externalOrg) {
        this.externalOrg = externalOrg;
    }

    public Integer getIsbizunit() {
        return isbizunit;
    }

    public void setIsbizunit(Integer isbizunit) {
        this.isbizunit = isbizunit;
    }


    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
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

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryzone() {
        return countryzone;
    }

    public void setCountryzone(String countryzone) {
        this.countryzone = countryzone;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }


    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public Long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Long creationtime) {
        this.creationtime = creationtime;
    }

    public Long getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Long effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Long getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Long expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Long getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Long getSynchts() {
        return synchts;
    }

    public void setSynchts(Long synchts) {
        this.synchts = synchts;
    }

    public Long getPubts() {
        return pubts;
    }

    public void setPubts(Long pubts) {
        this.pubts = pubts;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
