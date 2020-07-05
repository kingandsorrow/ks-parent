package top.ks.config.consumer.resp;

import lombok.Getter;
import lombok.Setter;
import top.ks.common.util.ResponseEntity;
import top.ks.config.consumer.bean.ConfigBean;

import java.util.List;

@Setter
@Getter
public class ConfigListResp extends ResponseEntity {
    private List<ConfigBean> configBeanList;
}
