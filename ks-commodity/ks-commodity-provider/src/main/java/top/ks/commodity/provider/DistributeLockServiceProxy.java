package top.ks.commodity.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.ks.commodity.api.DistributeLockServiceI;
import top.ks.commodity.api.req.DeducteCommodityReq;
import top.ks.common.util.ResponseEntity;


@Service(
        version = "${dubbo.application.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        retries = -1,
        timeout = 30000

)
@Component
public class DistributeLockServiceProxy implements DistributeLockServiceI {
    @Override
    public ResponseEntity reduce(DeducteCommodityReq deducteCommodityReq) {

        return null;
    }
}
