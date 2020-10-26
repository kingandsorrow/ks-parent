package top.ks.h5.web.mapper;

import top.ks.h5.web.model.SpidersUrl;

public interface SpidersUrlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpidersUrl record);

    int insertSelective(SpidersUrl record);

    SpidersUrl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpidersUrl record);

    int updateByPrimaryKey(SpidersUrl record);

    SpidersUrl selectByUrl(String url);

    SpidersUrl selectByStatus(Integer urlStatus, Integer imgStatus);
}