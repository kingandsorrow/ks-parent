package top.ks.yonyou.db.test.event;

import java.io.Serializable;
import java.util.Map;

public class EventModelSenderDTO implements Serializable {

    private static final long serialVersionUID = 7840374453452557493L;

    /**
     * 扩展map老模型
     */
    public static final String EXT_MAP_KEY_OLD_MODEL = "oldModel";

    /**
     * 消息体模型描述
     */
    private Map<String, Object> data;

    /**
     * 消息体主模型
     */
    private BaseEventDTO model;

    /**
     * 消息体扩展属性
     */
    private Map<String, Object> extMap;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public BaseEventDTO getModel() {
        return model;
    }

    public void setModel(BaseEventDTO model) {
        this.model = model;
    }

    public Map<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, Object> extMap) {
        this.extMap = extMap;
    }
}
