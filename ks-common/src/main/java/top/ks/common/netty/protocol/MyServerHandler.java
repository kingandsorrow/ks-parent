package top.ks.common.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        int len = messageProtocol.getLen();
        byte[] content = messageProtocol.getContent();
        System.out.println("服务器接收信息：");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, CharsetUtil.UTF_8));
        // 回复消息
        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes("UTF-8").length;
        byte[] responseConBytes = responseContent.getBytes("UTF-8");
        MessageProtocol responseProtocol = new MessageProtocol();
        responseProtocol.setLen(responseLen);
        responseProtocol.setContent(responseConBytes);
        channelHandlerContext.writeAndFlush(responseProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
