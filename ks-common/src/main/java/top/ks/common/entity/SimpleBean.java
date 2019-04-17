package top.ks.common.entity;

import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SimpleBean {
    public String name;

    public void send() {
        System.out.println("I am send method from SimpleBean!");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(Arrays.toString(("Hello RocketMQ " + 0).getBytes(RemotingHelper.DEFAULT_CHARSET)));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
