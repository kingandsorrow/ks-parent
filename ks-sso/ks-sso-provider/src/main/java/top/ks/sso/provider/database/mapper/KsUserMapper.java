package top.ks.sso.provider.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.sso.provider.database.model.KsUser;

public interface KsUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(KsUser record);

    int insertSelective(KsUser record);

    KsUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(KsUser record);

    int updateByPrimaryKey(KsUser record);

    KsUser selectByPhoneWay(@Param("phone") String phone);
}