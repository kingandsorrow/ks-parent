package top.ks.common.singleton;

/**
 * <b>类名称:</b>Single$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/29<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/3/29
 */
public class Single {

    private Single() {

    }

    private static volatile Single single;

    //双重校验锁
    //第一层校验是为了减少同步
    //第二层校验是为了防止重复创建对象
    private static Single getSingleBean() {
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                }
            }
        }
        return single;
    }
}
