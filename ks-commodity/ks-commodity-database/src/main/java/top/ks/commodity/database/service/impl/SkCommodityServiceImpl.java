package top.ks.commodity.database.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ks.commodity.database.mapper.SkCommodityMapper;
import top.ks.commodity.database.mapper.SkRecordMapper;
import top.ks.commodity.database.model.SkRecord;
import top.ks.commodity.database.service.SkCommodityService;

import javax.annotation.Resource;

/**
 * <b>类名称:</b>SkCommodityServiceImpl$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/9<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/9
 */
@Transactional
@Service
public class SkCommodityServiceImpl implements SkCommodityService {
    @Resource
    private SkCommodityMapper skCommodityMapper;
    @Resource
    private SkRecordMapper skRecordMapper;
    private static final Log log = LogFactory.getLog(SkCommodityServiceImpl.class);

    @Override
    public int deducteCommodity(String userId, String commodityId, String skOrderId) {
        int row1 = skCommodityMapper.deducteCommodity(commodityId);
        if (row1 <= 0) {
            return row1;
        }
        SkRecord skRecord = new SkRecord();
        skRecord.setCommodityId(commodityId);
        skRecord.setSkId(IdUtil.createSnowflake(1, 1).nextId() + "");
        skRecord.setSkOrderId(skOrderId);
        int row2 = skRecordMapper.insert(skRecord);
        return row2;
    }
}
