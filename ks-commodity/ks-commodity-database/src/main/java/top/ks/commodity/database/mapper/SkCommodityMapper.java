package top.ks.commodity.database.mapper;

import top.ks.commodity.database.model.SkCommodity;

public interface SkCommodityMapper {
    int deleteByPrimaryKey(String skId);

    int insert(SkCommodity record);

    int insertSelective(SkCommodity record);

    SkCommodity selectByPrimaryKey(String skId);

    int updateByPrimaryKeySelective(SkCommodity record);

    int updateByPrimaryKey(SkCommodity record);

    SkCommodity selectByCommodityId(String commodityId);

    int deducteCommodity(String commodityId);
}
