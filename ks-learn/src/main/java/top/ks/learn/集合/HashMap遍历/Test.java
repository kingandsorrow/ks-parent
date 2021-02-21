package top.ks.learn.集合.HashMap遍历;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "ks0");
        map.put("b", "ks1");
        map.put("c", "ks2");
        map.put("d", "ks3");

        //iterator(map);
        //lambda(map);
        stream(map);

    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :第一种迭代器遍历及删除
     * @author : birjc
     * @CreateDate : 2021-02-18 12:05
     */
    public static void iterator(Map map) {
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if ("c".equalsIgnoreCase(entry.getKey())) {
                System.out.println("del ::" + entry.getValue());
            } else {
                System.out.println("show::" + entry.getKey());
            }
        }
    }

    public static void lambda(Map map) {
        map.keySet().removeIf(key -> "c".equals(key));
        map.forEach((key, val) -> {
            System.out.println("show:" + key);
        });
    }

    public static void stream(Map<String, Object> map) {
        map.entrySet().stream().forEach((entry) -> {
            if ("c".equals(entry.getKey())) {
                System.out.println("del:" + entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        });
    }
}
