package top.ks.config.consumer.req;

import lombok.Getter;
import lombok.Setter;
import top.ks.common.util.RequestEntity;

@Getter
@Setter
public class ConfigListReq extends RequestEntity {
    private String configScope;
}
