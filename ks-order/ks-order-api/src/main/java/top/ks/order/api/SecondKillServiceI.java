package top.ks.order.api;

import top.ks.order.api.req.SecondKillReq;
import top.ks.order.api.req.SkOrderReq;
import top.ks.order.api.resp.SecondKillResp;
import top.ks.order.api.resp.SkOrderResp;

/**
 * <b>类名称:</b>SecondKillServiceI$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/2/28<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/2/28`
 */
public interface SecondKillServiceI {

    public SecondKillResp secondKillOrder(SecondKillReq secondKillReq);

    public SkOrderResp skOrder(SkOrderReq skOrderReq);
}
