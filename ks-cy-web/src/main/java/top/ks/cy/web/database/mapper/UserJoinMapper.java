package top.ks.cy.web.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.cy.web.database.model.UserJoin;

import java.util.List;

public interface UserJoinMapper {
    int deleteByPrimaryKey(String joinId);

    int insert(UserJoin record);

    int insertSelective(UserJoin record);

    UserJoin selectByPrimaryKey(String joinId);

    int updateByPrimaryKeySelective(UserJoin record);

    int updateByPrimaryKey(UserJoin record);

    List<UserJoin> selectListByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    String selectOneName(@Param("id")String id);
}
