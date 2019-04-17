package top.ks.common.thread;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * <b>类名称:</b>Calculate$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/3/18<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/3/18
 */
public class Calculate {

    private List<Integer> pendCals = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));

    private Map<String, Integer> calResult = new HashMap<>();

    public Map<String, Integer> getCalResult() {
        return calResult;
    }

    public void setCalResult(Map<String, Integer> calResult) {
        this.calResult = calResult;
    }

    public List<Integer> getPendCals() {
        return pendCals;
    }

    public void setPendCals(List<Integer> pendCals) {
        this.pendCals = pendCals;
    }
}
