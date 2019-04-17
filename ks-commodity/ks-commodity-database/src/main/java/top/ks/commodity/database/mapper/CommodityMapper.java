package top.ks.commodity.database.mapper;

import top.ks.commodity.database.model.Commodity;

public interface CommodityMapper {
    int deleteByPrimaryKey(String commodityId);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(String commodityId);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKeyWithBLOBs(Commodity record);

    int updateByPrimaryKey(Commodity record);
}