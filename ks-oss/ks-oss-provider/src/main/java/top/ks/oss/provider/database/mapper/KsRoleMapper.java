package top.ks.oss.provider.database.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.ks.oss.provider.database.model.KsRole;

import java.util.List;

public interface KsRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(KsRole record);

    int insertSelective(KsRole record);

    KsRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(KsRole record);

    int updateByPrimaryKey(KsRole record);

    List<KsRole> selectByOperatorId(@Param("operatorId") String operatorId, @Param("projectId") String projectId);

    Page<KsRole> roleList(@Param("roleName") String roleName, @Param("projectId") String projectId);

}