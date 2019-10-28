package top.ks.common.java8;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestSorted {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("a", 10));
        list.add(new Person("b", 11));
        list.add(new Person("c", 12));
        System.out.println(JSON.toJSONString(list.stream().sorted((o1, o2) -> {
            Person p1 = o1;
            if (o1.getAge() < o2.getAge()) {
                return -1;
            } else if (o1.getAge() == o2.getAge()) {
                return 0;
            } else {
                return 1;
            }
        }).collect(Collectors.toList())));
    }
}
