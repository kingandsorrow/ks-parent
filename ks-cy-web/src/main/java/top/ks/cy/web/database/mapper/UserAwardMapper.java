package top.ks.cy.web.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.cy.web.database.model.UserAward;

import java.util.List;

public interface UserAwardMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserAward record);

    int insertSelective(UserAward record);

    UserAward selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserAward record);

    int updateByPrimaryKey(UserAward record);

    List<UserAward> selectListByTime(@Param("startTime") String startTime, @Param("endTime")String endTime);
}