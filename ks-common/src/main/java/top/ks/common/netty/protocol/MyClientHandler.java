package top.ks.common.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        int len = messageProtocol.getLen();
        byte[] content = messageProtocol.getContent();
        System.out.println("客户端接收到消息如下：");
        System.out.println("长度为：" + len);
        System.out.println("内容=" + new String(content, "utf-8"));
        System.out.println("客户端接收消息数量=" + (++count));
    }
}
