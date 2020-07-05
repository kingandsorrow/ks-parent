package top.ks.config.consumer.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.ks.common.util.BaseEntity;

@Setter
@Getter
@ToString
public class ConfigBean extends BaseEntity {
    private String configKey;
    private String configValue;
    private String updateTime;
    private String createTime;
    private String configScope;
    private String jsonContent;
    private String configKeyName;
}
