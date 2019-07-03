package top.ks.redis;

/**
 * <b>类名称:</b>CommonKey$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/7<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/7
 */
public class LockKey extends BasePrefix {
    public LockKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static LockKey lockKey = new LockKey(5, "dl");

}
