package top.ks.sso.provider.database.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.ks.sso.provider.database.model.KsOssUser;

public interface KsOssUserMapper {
    int deleteByPrimaryKey(String ossUserId);

    int insert(KsOssUser record);

    int insertSelective(KsOssUser record);

    KsOssUser selectByPrimaryKey(String ossUserId);

    int updateByPrimaryKeySelective(KsOssUser record);

    int updateByPrimaryKey(KsOssUser record);

    KsOssUser selectByLogin(@Param("loginPassword") String loginPassword, @Param("loginNo") String loginNo, @Param("projectId") String projectId, @Param("email") String email);

    Page<KsOssUser> selectList(@Param("nickName") String nickName, @Param("orgId") String orgId, @Param("projectId") String projectId);
}
