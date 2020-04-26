package top.ks.common.util;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Client {


    public static int a;
    private static Client client = new Client();
    public static int b = 0;


    private Client() {
        a++;
        b++;
    }

    public static Client getInstance() {
        return client;
    }

    public static void main(String[] args) {
        Client instance = Client.getInstance();
        System.out.println("a= " + Client.a);
        System.out.println("b= " + Client.b);
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add(1, "b");

        ArrayList<String> list1 = (ArrayList<String>) list.clone();
        list1.add("f");
        System.out.println("list1::" + JSON.toJSON(list1));
        System.out.println("list::" + JSON.toJSON(list));

        int num = -10;
        System.out.println(num >>> 1);

        int b = 128;
        System.out.println(b >>> 4);

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("a", 1);

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
        copyOnWriteArrayList.add("a");

        String a="cccddd";
    }


}

