package top.ks.config.consumer.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.ks.common.util.RequestEntity;

@Setter
@Getter
@ToString
public class ConfigBeanReq extends RequestEntity {
    private String configScope;
    private String configKey;
}
