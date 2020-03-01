package top.ks.oss.consumer;

import top.ks.oss.consumer.req.RoleListReq;
import top.ks.oss.consumer.resp.RoleListResp;

public interface RoleServiceI {

    public RoleListResp roleList(RoleListReq roleListReq);
}
