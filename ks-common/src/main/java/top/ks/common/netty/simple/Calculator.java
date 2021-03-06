package top.ks.common.netty.simple;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <b>类名称:</b>Calculator$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/16<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/16
 */
public enum Calculator {

    Instance;

    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }
}
