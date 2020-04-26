package top.ks.oss.consumer;

import top.ks.oss.consumer.req.OrgListReq;
import top.ks.oss.consumer.resp.OrgListResp;

public interface OrgServiceI {

    OrgListResp orgList(OrgListReq orgListReq);

}
