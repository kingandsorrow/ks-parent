package top.ks.order.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.order.database.model.SkOrder;

public interface SkOrderMapper {
    int deleteByPrimaryKey(String skOrderId);

    int insert(SkOrder record);

    int insertSelective(SkOrder record);

    SkOrder selectByPrimaryKey(String skOrderId);

    int updateByPrimaryKeySelective(SkOrder record);

    int updateByPrimaryKey(SkOrder record);

    SkOrder selectByUserCommodity(@Param("commodityId") String commodityId, @Param("userId") String userId);
}
