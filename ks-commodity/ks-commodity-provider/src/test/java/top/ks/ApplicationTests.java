package top.ks;

import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.commodity.api.SkCommodityServiceI;
import top.ks.common.util.ResponseEntity;

import javax.annotation.Resource;

@SpringBootApplication
public class ApplicationTests {

    @Resource
    private SkCommodityServiceI commodityServiceI;


    @Test
    public void contextLoads() {
        ResponseEntity responseEntity = commodityServiceI.testAop();
        System.out.println("aaa");
    }


}
