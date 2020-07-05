package top.ks.config.consumer;

import top.ks.common.util.ResponseEntity;
import top.ks.config.consumer.bean.ConfigBean;
import top.ks.config.consumer.req.ConfigBeanReq;
import top.ks.config.consumer.req.ConfigListReq;
import top.ks.config.consumer.req.ConfigReq;
import top.ks.config.consumer.resp.ConfigListResp;

/**
 * <b>类名称:</b>KsConfigServiceI<br/>
 * <b>类注释:</b>KsConfig<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2020-05-12
 */
public interface KsConfigServiceI {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 获取配置实体
     * @author : birjc
     * @CreateDate : 2020-05-12 00:11
     */
    public ConfigBean configBean(ConfigBeanReq configBeanReq);

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 获取配置实体列表
     * @author : birjc
     * @CreateDate : 2020-05-12 00:11
     */
    public ConfigListResp configList(ConfigListReq configListReq);

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 新增或更新配置
     * @author : birjc
     * @CreateDate : 2020-05-12 00:18
     */
    public ResponseEntity config(ConfigReq configReq);


}
