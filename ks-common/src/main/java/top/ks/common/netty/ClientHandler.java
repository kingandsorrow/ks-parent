package top.ks.common.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * <b>类名称:</b>SocketChannel$<br/>
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
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext ctx;



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       // super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器端发来的消息："+buf.toString(CharsetUtil.UTF_8));
    }

    public boolean sendMsg(String msg) {
        System.out.println("客户端发送消息：" + msg);
        /*byte[] req = msg.getBytes();

        ByteBuf byteBuf = Unpooled.buffer(req.length);
        byteBuf.writeBytes(byteBuf);*/
        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));

        return "q".equals(msg) ? false : true;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        System.out.println("错误");
        ctx.close();
    }
}
