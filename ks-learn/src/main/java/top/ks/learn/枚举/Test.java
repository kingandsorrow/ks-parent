package top.ks.learn.枚举;

import com.alibaba.fastjson.JSON;


public class Test {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(PizzaStatus.ORDERED));
        PizzaStatus status = null;

        System.out.println(JSON.toJSONString(Distance.METER));
    }

    public static int getStatus() {
        PizzaStatus status = null;
        switch (status) {
            case ORDERED:
                return 5;
            case READY:
                return 2;
            case DELIVERED:
                return 0;
        }
        return 0;
    }
}
