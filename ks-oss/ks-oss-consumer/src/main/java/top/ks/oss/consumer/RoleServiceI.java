package top.ks.oss.consumer;

import top.ks.oss.consumer.req.AddRoleReq;
import top.ks.oss.consumer.req.DeleteRoleReq;
import top.ks.oss.consumer.req.RoleListReq;
import top.ks.oss.consumer.req.UpdateRoleReq;
import top.ks.oss.consumer.resp.AddRoleResp;
import top.ks.oss.consumer.resp.DeleteRoleResp;
import top.ks.oss.consumer.resp.RoleListResp;
import top.ks.oss.consumer.resp.UpdateRoleResp;

public interface RoleServiceI {

    RoleListResp roleList(RoleListReq roleListReq);

    AddRoleResp addRole(AddRoleReq addRoleReq);

    UpdateRoleResp updateRole(UpdateRoleReq updateRoleReq);

    DeleteRoleResp deleteRole(DeleteRoleReq deleteRoleReq);
}
