package top.ks.oss.consumer.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.ks.common.enums.ResultStatus;
import top.ks.common.util.ResponseEntity;
import top.ks.oss.consumer.bean.KsRoleBean;

import java.util.List;

/**
 * <b>类名称:</b>RoleListResp$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/27<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/11/27
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleListResp extends ResponseEntity {

    private List<KsRoleBean> roleList;

    private long totalCount;


    public RoleListResp(List<KsRoleBean> roleList, long totalCount) {
        this.roleList = roleList;
        this.totalCount = totalCount;
    }

    public RoleListResp(ResultStatus resultStatus, List<KsRoleBean> roleList, long totalCount) {
        super(resultStatus);
        this.roleList = roleList;
        this.totalCount = totalCount;
    }
}
