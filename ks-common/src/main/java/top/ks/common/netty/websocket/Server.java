package top.ks.common.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import top.ks.common.netty.chat.GroupChatServerHandler;

public class Server {
    public void run() {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = getServerBootstrap(bossLoopGroup, workerGroup);
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossLoopGroup.shutdownGracefully();
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
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        channelPipeline.addLast(new HttpServerCodec());
                        // 块状方式写
                        channelPipeline.addLast(new ChunkedWriteHandler());
                        channelPipeline.addLast(new GroupChatServerHandler());
                        channelPipeline.addLast(new HttpObjectAggregator(8192));
                        channelPipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                        channelPipeline.addLast(new MyTextWebSocketFrameHandler());
                    }
                });

        return serverBootstrap;
    }

    public static void main(String[] args) {
        new Server().run();
    }
}
