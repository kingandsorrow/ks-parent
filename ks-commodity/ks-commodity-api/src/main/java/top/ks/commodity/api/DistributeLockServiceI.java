package top.ks.commodity.api;

import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.common.util.ResponseEntity;

/**
 * 场景 假设该服务在两台服务器部署了服务，同时去扣减库存
 */
public interface DistributeLockServiceI {

    public ResponseEntity reduce(DeducteCommodityReq deducteCommodityReq);
}
