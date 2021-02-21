package top.ks.learn.集合.手写HashMap;

public class Test {

    public static void main(String[] args) {
        KHashMap<String, String> map = new KHashMap<>();
        map.put("name", "tom");
        map.put("age", "23");
        map.put("address", "beijing");
        String oldValue = map.put("address", "shanghai"); //key一样，返回旧值，保存新值

        System.out.println(map.get("name"));
        System.out.println(map.get("age"));

        System.out.println("旧值=" + oldValue);
        System.out.println("新值=" + map.get("address"));

    }
}
