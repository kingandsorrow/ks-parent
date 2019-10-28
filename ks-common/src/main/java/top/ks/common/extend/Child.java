package top.ks.common.extend;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Child extends Father {

    public String name = "child";
    static int a = 15;

    static {
        a = a * 3;
    }

    public static void main(String[] args) {
        System.out.println(a);
    }

    static {
        a = a / 5;
    }


}
