package top.ks.h5.web.mapper;

import top.ks.h5.web.model.SpidersImage;

public interface SpidersImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpidersImage record);

    int insertSelective(SpidersImage record);

    SpidersImage selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(SpidersImage record);

    int updateByPrimaryKey(SpidersImage record);

    SpidersImage selectBySourceUrl(String sourceUrl);
}