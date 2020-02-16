package top.ks.sso.consumer;

import top.ks.sso.consumer.req.OssUserListReq;
import top.ks.sso.consumer.resp.OssUserListResp;

public interface OssUserServiceI {

    OssUserListResp ossUserList(OssUserListReq ossUserListReq);
}
