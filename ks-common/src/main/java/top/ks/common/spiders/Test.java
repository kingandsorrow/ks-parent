package top.ks.common.spiders;

import cn.hutool.http.HttpUtil;

public class Test {

    public static void main(String[] args) {
       String resp =  HttpUtil.get("https://fabiaoqing.com/biaoqing/lists/page/2.html");

    }
}
