package top.ks.oss.provider;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Component;
import top.ks.common.enums.ResultStatus;
import top.ks.oss.consumer.OrgServiceI;
import top.ks.oss.consumer.bean.KsOrgBean;
import top.ks.oss.consumer.req.OrgListReq;
import top.ks.oss.consumer.resp.OrgListResp;
import top.ks.oss.provider.database.mapper.KsOrgMapper;
import top.ks.oss.provider.database.model.KsOrg;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrgServiceIProxy implements OrgServiceI {
    @Resource
    private KsOrgMapper ksOrgMapper;

    @Override
    public OrgListResp orgList(OrgListReq orgListReq) {
        KsOrg ksOrg = ksOrgMapper.selectByPrimaryKey("0");
        KsOrgBean ksOrgBean = new KsOrgBean();
        BeanUtil.copyProperties(ksOrg, ksOrgBean);
        List<KsOrgBean> ksOrgBeans = new ArrayList<>();
        ksOrgBeans.add(ksOrgBean);
        OrgListResp orgListResp = new OrgListResp(ResultStatus.SUCCESS);
        orgListResp.setKsOrgBeans(ksOrgBeans);
        return orgListResp;
    }
}
