package top.ks.commodity.database.mapper;

import top.ks.commodity.database.model.SkProject;

public interface SkProjectMapper {
    int deleteByPrimaryKey(String projectId);

    int insert(SkProject record);

    int insertSelective(SkProject record);

    SkProject selectByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(SkProject record);

    int updateByPrimaryKey(SkProject record);
}