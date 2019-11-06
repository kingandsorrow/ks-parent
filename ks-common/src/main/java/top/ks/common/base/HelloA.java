package top.ks.common.base;

import top.ks.common.util.ToolUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    }
}
