package top.ks.oss.provider.database.mapper;

import top.ks.oss.provider.database.model.KsOrg;

public interface KsOrgMapper {
    int deleteByPrimaryKey(String orgId);

    int insert(KsOrg record);

    int insertSelective(KsOrg record);

    KsOrg selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(KsOrg record);

    int updateByPrimaryKey(KsOrg record);
}