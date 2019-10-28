package top.ks.bill.consumer;

import top.ks.bill.consumer.base.BillDataDto;

import java.util.List;

public interface BillServiceI {
    List listDetail(BillDataDto billDataDto);
}
