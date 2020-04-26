package top.ks.common.base;

import com.alibaba.fastjson.JSON;
import top.ks.common.util.ToolUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HelloA {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    public HelloA() {
        System.out.println("HelloA");
    }

    {
        System.out.println("im class A");
    }

    static {
        System.out.println(" static A");
    }

    public static void main(String[] args) {
        System.out.println(sdf.format(new Date(Long.parseLong(String.valueOf("549471600000")))));
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        map1.put("pubts", "2020-03-01:00:00:01");
        map2.put("pubts", "2020-03-01:00:00:05");
        map3.put("pubts", "2020-03-01:00:00:03");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        System.out.println("original is.." + JSON.toJSON(list));
        List<Map> list1 = list.stream().sorted((o1, o2) -> {
            String o1Code = (String) o1.get("pubts");
            String o2Code = (String) o2.get("pubts");
            return o1Code.compareTo(o2Code);
        }).collect(Collectors.toList());
        System.out.println("after:、、、" + JSON.toJSON(list1));
    }
}
