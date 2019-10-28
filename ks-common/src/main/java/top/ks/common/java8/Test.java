package top.ks.common.java8;

import com.alibaba.fastjson.JSON;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private List<Person> personList = new ArrayList<>();

    private List<String> strList = new ArrayList<>();

    @Before
    public void beMethod() {
        Person person1 = new Person("bi", 25);
        Person person2 = new Person("wang", 26);
        Person person3 = new Person("zhang", 24);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        strList.add("a");
        strList.add("d");
        strList.add("b");
        strList.add("f");
        strList.add("c");
    }


    @org.junit.Test
    public void test1() {
        List filterList = strList.stream().filter(string -> !string.equalsIgnoreCase("a")).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(filterList));
    }
}
