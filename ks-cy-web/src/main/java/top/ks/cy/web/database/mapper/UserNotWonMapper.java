package top.ks.cy.web.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.cy.web.database.model.UserAward;
import top.ks.cy.web.database.model.UserNotWon;

import java.util.List;

public interface UserNotWonMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserNotWon record);

    int insertSelective(UserNotWon record);

    UserNotWon selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserNotWon record);

    int updateByPrimaryKey(UserNotWon record);

    List<UserNotWon> selectListByGetTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
}