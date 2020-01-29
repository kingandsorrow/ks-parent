package top.ks.common.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupServer {

    public static void main(String[] args) {
        new GroupServer().run();
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = getServerBootstrap(bossGroup, workerGroup);
            System.out.println("服务器启动成功");
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    /**
     * @param :
     * @param bossGroup
     * @param workGroup
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2020-01-23 11:31
     */
    private ServerBootstrap getServerBootstrap(EventLoopGroup bossGroup, EventLoopGroup workGroup) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        channelPipeline.addLast("decoder", new StringDecoder());
                        channelPipeline.addLast("encoder", new StringEncoder());
                        channelPipeline.addLast(new GroupChatServerHandler());
                    }
                });
        return serverBootstrap;
    }
}
