package top.ks.oss.consumer.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.ks.common.util.BaseEntity;

import java.util.Date;

@Getter
@Setter
@ToString
public class KsOrgBean extends BaseEntity {
    private String orgId;

    private String parentId;

    private String orgCode;

    private String orgName;

    private String shortname;

    private Integer orgType;

    private String principal;

    private Integer enable;

    private Date effectivedate;

    private Date expirationdate;

    private String locationid;

    private String innercode;

    private String language;

    private String contact;

    private String telephone;

    private String address;

    private String description;

    private String creator;

    private String companytype;

    private String sourceid;

    private Integer displayorder;

    private Integer dr;

    private Date pubts;

    private Integer isend;

    private Integer level;

    private String parentOrgId;

    private String creatorId;

    private Date createTime;

    private Date updateTime;

    private String otherInfo;

    private String projectId;

}
