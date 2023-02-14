package streetlamp.netty.tcp.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@ChannelHandler.Sharable
public class ServerListenerHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 客户端上线的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}客户端连接进来了", ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }

    /**
     * 客户端掉线的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}连接断开了", ctx.channel().remoteAddress());
        ctx.fireChannelInactive();
    }


    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        String data = msg.toString();
        String remoteAddress = channelHandlerContext.channel().remoteAddress().toString();
        log.info("来自客户端{}的消息{}", remoteAddress, data);
        channelHandlerContext.writeAndFlush(msg);
    }

    /**
     * 异常发生时候调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("{}连接出异常了", ctx.channel().remoteAddress());
//        log.error(ExceptionUtil.printStackTrace((Exception) cause));
        cause.printStackTrace();
        ctx.close();
    }
}
