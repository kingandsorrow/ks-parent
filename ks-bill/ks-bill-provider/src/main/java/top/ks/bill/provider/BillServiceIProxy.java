package top.ks.bill.provider;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BillServiceIProxy implements BillServiceI {

    @Override
    public List listDetail(BillDataDto billDataDto) {
        System.out.println("aaa");
        return new ArrayList();
    }
}
