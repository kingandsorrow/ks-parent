package top.ks.oss.provider.database.mapper;

import top.ks.oss.provider.database.model.KsOperatorRole;

public interface KsOperatorRoleMapper {
    int deleteByPrimaryKey(String operatorRoleId);

    int insert(KsOperatorRole record);

    int insertSelective(KsOperatorRole record);

    KsOperatorRole selectByPrimaryKey(String operatorRoleId);

    int updateByPrimaryKeySelective(KsOperatorRole record);

    int updateByPrimaryKey(KsOperatorRole record);
}