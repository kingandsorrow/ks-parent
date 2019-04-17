package top.ks.commodity.database.mapper;

import org.apache.ibatis.annotations.Param;
import top.ks.commodity.database.model.SkRecord;

public interface SkRecordMapper {
    int deleteByPrimaryKey(String skId);

    int insert(SkRecord record);

    int insertSelective(SkRecord record);

    SkRecord selectByPrimaryKey(String skId);

    int updateByPrimaryKeySelective(SkRecord record);

    int updateByPrimaryKey(SkRecord record);

    SkRecord selectByOrderId(@Param("skOrderId") String skOrderId);
}
