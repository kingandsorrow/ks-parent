package top.ks.commodity.provider;


import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import top.ks.commodity.api.SkCommodityServiceI;
import top.ks.common.util.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestCommidity {
    @Reference(version = "1.0.0")
    private SkCommodityServiceI commodityServiceI;

    @Test
    public void contextLoads() {
        ResponseEntity responseEntity = commodityServiceI.testAop();
        System.out.println("aaa");
    }

}
