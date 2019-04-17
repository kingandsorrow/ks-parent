package top.ks.sso.provider.database.mapper;

import top.ks.sso.provider.database.model.KsUser;
import top.ks.sso.provider.database.model.KsUserAuths;

public interface KsUserAuthsMapper {
    int deleteByPrimaryKey(String authsId);

    int insert(KsUserAuths record);

    int insertSelective(KsUserAuths record);

    KsUserAuths selectByPrimaryKey(String authsId);

    int updateByPrimaryKeySelective(KsUserAuths record);

    int updateByPrimaryKey(KsUserAuths record);

    KsUser selectByPhoneWay(String loginName);
}