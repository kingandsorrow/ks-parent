package top.ks.h5.web.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import top.ks.h5.web.mapper.SpidersUrlMapper;
import top.ks.h5.web.model.SpidersUrl;
import top.ks.h5.web.mybatis.MyBatisUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SpidersUrlMapperImpl implements SpidersUrlMapper {

    private static final String namespace = "top.ks.h5.web.mapper.SpidersUrlMapper.";

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SpidersUrl record) {
        return 0;
    }

    @Override
    public int insertSelective(SpidersUrl record) {
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
    public SpidersUrl selectByPrimaryKey(Long id) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            SpidersUrl spidersUrl = session.selectOne(namespace + "selectByPrimaryKey", id);
            return spidersUrl;
        } catch (Exception e) {
            log.error(String.format("birjc SpidersUrlMapperImpl.selectByPrimaryKey:: %s, %s", "system error::" + e.getMessage(), e));
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SpidersUrl record) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            return session.update(namespace + "updateByPrimaryKeySelective", record);
        } catch (Exception e) {
            log.error(String.format("birjc SpidersUrlMapperImpl.updateByPrimaryKeySelective:: %s, %s", "system error::" + e.getMessage(), e));
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SpidersUrl record) {
        return 0;
    }

    @Override
    public SpidersUrl selectByUrl(String url) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            SpidersUrl spidersUrl = session.selectOne(namespace + "selectByUrl", url);
            return spidersUrl;
        } catch (Exception e) {
            log.error(String.format("birjc SpidersUrlMapperImpl.selectByUrl:: %s, %s", "system error::" + e.getMessage(), e));
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return null;
    }

    @Override
    public SpidersUrl selectByStatus(Integer urlStatus, Integer imgStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("urlStatus", urlStatus);
            map.put("imgStatus", imgStatus);
            return session.selectOne(namespace + "selectByStatus", map);
        } catch (Exception e) {
            log.error(String.format("birjc SpidersUrlMapperImpl.selectByUrl:: %s, %s", "system error::" + e.getMessage(), e));
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return null;
    }
}
