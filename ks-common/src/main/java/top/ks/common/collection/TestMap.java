package top.ks.common.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestMap {

    public static void main(String[] args) {
        Map map = new HashMap<String, Object>();
        map.put("a", 11);
        map.put("c", 22);
        map.put("e", 33);
        map.put("b", 43);

        Set<String> keySets = map.keySet();

        for (String key : keySets) {
            System.out.println(key + "--" + map.get(key));
        }
    }
}
