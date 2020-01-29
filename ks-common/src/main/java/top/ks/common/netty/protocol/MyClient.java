package top.ks.common.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.awt.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = getBootstrap(eventLoopGroup);
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect("localhost", 7000).sync();
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                byte[] content = msg.getBytes(Charset.forName("utf-8"));
                int length = msg.getBytes(Charset.forName("utf-8")).length;
                //创建协议包对象
                MessageProtocol messageProtocol = new MessageProtocol();
                messageProtocol.setLen(length);
                messageProtocol.setContent(content);
                channel.writeAndFlush(messageProtocol);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

    private static Bootstrap getBootstrap(EventLoopGroup eventLoopGroup) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline channelPipeline = channel.pipeline();
                channelPipeline.addLast(new MyMessageEncoder());
                channelPipeline.addLast(new MyMessageDecoder());
                channelPipeline.addLast(new MyClientHandler());
            }
        });
        return bootstrap;
    }
}
