package top.ks.commodity.database.advisor;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ks.commodity.database.mapper.SkProjectMapper;
import top.ks.commodity.database.mapper.SkTempMapper;
import top.ks.commodity.database.model.SkProject;
import top.ks.commodity.database.model.SkTemp;

import javax.annotation.Resource;

@Aspect
@Component
public class CommodityAspect {


    @Resource
    private SkTempMapper skTempMapper;
    @Resource
    private SkProjectMapper skProjectMapper;

    @Pointcut("execution(* top.ks.commodity.database.service.impl.SkCommodityServiceImpl.insert*(..))")
    public void pointCut() {
    }

    @After(value = "pointCut()")
    public void after() throws Exception {
        SkTemp skTemp = new SkTemp();
        skTemp.setTempId(IdUtil.createSnowflake(5, 5).nextId() + "");
        skTemp.setTempName("abc");
        int row = skTempMapper.insert(skTemp);
        SkProject skProject = new SkProject();
        skProject.setProjectId(IdUtil.createSnowflake(5, 5).nextId() + "");
        skProject.setProjectName("bbb");
        int row1 = skProjectMapper.insert(skProject);
        System.out.println("row is.." + row1);
    }
}
