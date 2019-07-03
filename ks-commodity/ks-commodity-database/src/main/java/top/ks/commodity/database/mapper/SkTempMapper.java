package top.ks.commodity.database.mapper;

import top.ks.commodity.database.model.SkTemp;

public interface SkTempMapper {
    int deleteByPrimaryKey(String tempId);

    int insert(SkTemp record);

    int insertSelective(SkTemp record);

    SkTemp selectByPrimaryKey(String tempId);

    int updateByPrimaryKeySelective(SkTemp record);

    int updateByPrimaryKey(SkTemp record);
}