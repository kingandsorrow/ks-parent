package top.ks.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * <b>类名称:</b>NettyClient$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/16<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright 西安创意 2019/4/16
 */
public class NettyClient implements Runnable {

    static ClientHandler client = new ClientHandler();
    public static void main(String[] args) throws Exception {
        new Thread(new NettyClient()).start();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while(client.sendMsg(scanner.nextLine()));
    }
    @Override
    public void run() {

        String host = "127.0.0.1";

        int port = 9090;

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap boostrap = new Bootstrap();
            boostrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(client);
                        }

                    });
            ChannelFuture f = boostrap.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
