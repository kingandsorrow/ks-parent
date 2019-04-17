package top.ks.oss.provider.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.oss.provider.database.model.KsRoleFunction;

import java.util.List;

public interface KsRoleFunctionMapper {
    int deleteByPrimaryKey(String roleFunctionId);

    int insert(KsRoleFunction record);

    int insertSelective(KsRoleFunction record);

    KsRoleFunction selectByPrimaryKey(String roleFunctionId);

    int updateByPrimaryKeySelective(KsRoleFunction record);

    int updateByPrimaryKey(KsRoleFunction record);

    int insertksRoleFunctions(@Param("list") List<KsRoleFunction> ksRoleFunctions);
}