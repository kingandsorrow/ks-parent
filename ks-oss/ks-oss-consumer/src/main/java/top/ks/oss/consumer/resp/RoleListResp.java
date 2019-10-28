package top.ks.oss.consumer.resp;

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
public class RoleListResp extends ResponseEntity {

    private List<KsRoleBean> roleList;

    private int totalCount;


    public List<KsRoleBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<KsRoleBean> roleList) {
        this.roleList = roleList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public RoleListResp() {
    }

    public RoleListResp(String errCode) {
        super(errCode);
    }

    public RoleListResp(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public RoleListResp(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
