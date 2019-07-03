package top.ks.commodity.database.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ks.commodity.database.mapper.SkCommodityMapper;
import top.ks.commodity.database.mapper.SkRecordMapper;
import top.ks.commodity.database.model.SkCommodity;
import top.ks.commodity.database.model.SkRecord;
import top.ks.commodity.database.service.SkCommodityService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

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
        skRecord.setSkId(IdUtil.createSnowflake(5, 5).nextId() + "");
        skRecord.setSkOrderId(skOrderId);
        int row2 = skRecordMapper.insert(skRecord);
        return row2;
    }

    @Override
    public int insertData() {
        SkCommodity skCommodity = new SkCommodity();
        skCommodity.setCommodityId(IdUtil.createSnowflake(5, 5).nextId());
        skCommodity.setEndDate(new Date());
        skCommodity.setSkId("123456");
        skCommodity.setSkPrice(new BigDecimal("123"));
        skCommodity.setSkStockCount(10);
        skCommodity.setStartDate(new Date());
        int row1 = skCommodityMapper.insert(skCommodity);
        SkCommodity commodity = skCommodityMapper.selectByPrimaryKey("123456");
        SkRecord skRecord = new SkRecord();
        skRecord.setCommodityId(skCommodity.getCommodityId() + "");
        skRecord.setSkId(IdUtil.createSnowflake(5, 5).nextId() + "");
        skRecord.setSkOrderId("456");
        int row2 = skRecordMapper.insert(skRecord);
        return row2;
    }


}
