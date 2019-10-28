package top.ks.common.netty;

import java.util.Scanner;

/**
 * <b>类名称:</b>ClientMain$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/16<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/16
 */
public class ClientMain {

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        new Thread(nettyClient).start();
        Scanner scanner = new Scanner(System.in);
        while (nettyClient.client.sendMsg(scanner.nextLine())) ;


    }
}
