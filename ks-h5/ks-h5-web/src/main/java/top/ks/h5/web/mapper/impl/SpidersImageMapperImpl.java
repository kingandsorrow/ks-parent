package top.ks.h5.web.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import top.ks.h5.web.mapper.SpidersImageMapper;
import top.ks.h5.web.model.SpidersImage;
import top.ks.h5.web.mybatis.MyBatisUtil;

@Repository
@Slf4j
public class SpidersImageMapperImpl implements SpidersImageMapper {

    private static final String namespace = "top.ks.h5.web.mapper.SpidersImageMapper.";


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SpidersImage record) {
        return 0;
    }

    @Override
    public int insertSelective(SpidersImage record) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            int row = session.insert(namespace + "insertSelective", record);
            //注意：此处有陷阱，如果做了更新、插入或删除操作，必须使用：
            session.commit();
            return row;
        } catch (Exception e) {
            log.error(String.format("birjc SpidersUrlMapperImpl.insertSelective:: %s, %s", "system error::" + e.getMessage(), e));
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return 0;
    }

    @Override
    public SpidersImage selectByPrimaryKey(Long id) {
        return null;
    }


    @Override
    public int updateByPrimaryKeySelective(SpidersImage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SpidersImage record) {
        return 0;
    }

    @Override
    public SpidersImage selectBySourceUrl(String sourceUrl) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            SpidersImage spidersImage = session.selectOne(namespace + "selectBySourceUrl", sourceUrl);
            return spidersImage;
        } catch (Exception e) {
            log.error(String.format("birjc SpidersUrlMapperImpl.selectBySourceUrl:: %s, %s", "system error::" + e.getMessage(), e));
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return null;
    }
}
