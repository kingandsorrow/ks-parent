package top.ks.oss.provider.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.oss.provider.database.model.KsOperator;

import java.util.List;

public interface KsOperatorMapper {
    int deleteByPrimaryKey(String operatorId);

    int insert(KsOperator record);

    int insertSelective(KsOperator record);

    KsOperator selectByPrimaryKey(String operatorId);

    int updateByPrimaryKeySelective(KsOperator record);

    int updateByPrimaryKey(KsOperator record);

    KsOperator selectByNameAndPassword(@Param("loginName") String loginName, @Param("password") String password, @Param("projectId") String projectId);

    List<KsOperator> operatorList(@Param("loginName") String loginName, @Param("pageFrom") int pageFrom, @Param("pageSize") int pageSize);

    int selectCount(@Param("loginName") String loginName);
}