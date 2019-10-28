package top.ks.bill.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ks.bill.consumer.BillServiceI;
import top.ks.bill.consumer.base.BillDataDto;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")
public class TestBill {
    @Resource
    private BillServiceI serviceI;

    @Test
    public void test1() {
        BillDataDto billDataDto = new BillDataDto();
        List list = serviceI.listDetail(billDataDto);
    }
}
