package top.ks.oss.consumer.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.ks.common.util.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KsRoleBean extends BaseEntity {
    private String roleId;

    private String roleName;

    private String projectId;

    private String description;

    private String createTime;

    private String updateTime;

}
