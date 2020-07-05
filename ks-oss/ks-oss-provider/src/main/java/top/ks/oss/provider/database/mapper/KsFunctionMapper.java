package top.ks.oss.provider.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.oss.provider.database.model.KsFunction;
import top.ks.oss.provider.database.model.KsRole;

import java.util.List;

public interface KsFunctionMapper {
    int deleteByPrimaryKey(String functionId);

    int insert(KsFunction record);

    int insertSelective(KsFunction record);

    KsFunction selectByPrimaryKey(String functionId);

    int updateByPrimaryKeySelective(KsFunction record);

    int updateByPrimaryKey(KsFunction record);

    List<KsFunction> selectFunctionsByRoles(@Param("list") List<KsRole> ksRoleList, @Param("projectId") String projectId);

    List<KsFunction> selectAllList(@Param("projectId") String projectId);

    List<KsFunction> noButtonMenu(@Param("projectId") String projectId);

    List<KsFunction> selectRootList(@Param("projectId") String projectId);
}