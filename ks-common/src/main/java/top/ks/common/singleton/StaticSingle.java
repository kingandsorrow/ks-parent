package top.ks.common.singleton;

/**
 * <b>类名称:</b>StaticSingle$<br/>
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
public class StaticSingle {

    private StaticSingle() {

    }

    // 使用内部类的方式来实现懒加载
    private static class SingleHolder {
        // 创建单例对象
        private static StaticSingle staticSingle = new StaticSingle();
    }

    public String sayHello() {
        System.out.println("您好..");
        return "您好";
    }


    private static final StaticSingle getInstance() {
        return SingleHolder.staticSingle;
    }

}
