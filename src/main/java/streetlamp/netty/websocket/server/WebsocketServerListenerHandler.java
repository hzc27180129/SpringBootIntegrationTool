package streetlamp.netty.websocket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import streetlamp.netty.websocket.common.WebsocketProperties;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebsocketServerListenerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    public static ChannelGroup channelGroup;
    public static WebsocketProperties websocketProperties;

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        websocketProperties = new WebsocketProperties();
    }

    //客户端与服务器建立连接的时候触发，
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端建立连接，通道开启！");
        //添加到channelGroup通道组
        channelGroup.add(ctx.channel());
    }

    //客户端与服务器关闭连接的时候触发，
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端断开连接，通道关闭！");
        channelGroup.remove(ctx.channel());
    }

    //服务器接受客户端的数据信息，
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 根据请求数据类型进行分发处理
        if (frame instanceof PingWebSocketFrame) {
            pingWebSocketFrameHandler(ctx, (PingWebSocketFrame) frame);
        } else if (frame instanceof TextWebSocketFrame) {
            textWebSocketFrameHandler(ctx, (TextWebSocketFrame) frame);
        } else if (frame instanceof CloseWebSocketFrame) {
            closeWebSocketFrameHandler(ctx, (CloseWebSocketFrame) frame);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端请求数据类型：{}", msg.getClass());
        if (msg instanceof FullHttpRequest) {
            fullHttpRequestHandler(ctx, (FullHttpRequest) msg);
        }
        super.channelRead(ctx, msg);
    }

    /**
     * 处理连接请求，客户端WebSocket发送握手包时会执行这一次请求
     *
     * @param ctx
     * @param request
     */
    private void fullHttpRequestHandler(ChannelHandlerContext ctx, FullHttpRequest request) {
        String uri = request.uri();
        Map<String, String> params = getUrlParams(uri);
        log.info("客户端请求参数：{}", params);
        if (websocketProperties.getPath().equals(getBasePath(uri))) {
            request.setUri(getBasePath(uri));
        } else {
            log.info("path不匹配");
            ctx.close();
        }
    }

    /**
     * 客户端发送断开请求处理
     *
     * @param ctx
     * @param frame
     */
    private void closeWebSocketFrameHandler(ChannelHandlerContext ctx, CloseWebSocketFrame frame) {
        ctx.close();
    }

    /**
     * 创建连接之后，客户端发送的消息都会在这里处理
     *
     * @param ctx
     * @param frame
     */
    private void textWebSocketFrameHandler(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        // 客户端发送过来的内容不进行业务处理，原样返回
//        ctx.channel().writeAndFlush(frame.retain());
        sendMessage(ctx);
        sendAllMessage();
    }

    /**
     * 处理客户端心跳包
     *
     * @param ctx
     * @param frame
     */
    private void pingWebSocketFrameHandler(ChannelHandlerContext ctx, PingWebSocketFrame frame) {
        ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
    }

    //给固定的人发消息
    private void sendMessage(ChannelHandlerContext ctx) {
        String message = "你好，" + ctx.channel().localAddress() + " 给固定的人发消息";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }

    //发送群消息,此时其他客户端也能收到群消息
    private void sendAllMessage() {
        String message = "我是服务器，这里发送的是群消息";
        channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    public static Map<String, String> getUrlParams(String uri) {
        Map<String, String> params = new HashMap<>(10);

        int idx = uri.indexOf("?");
        if (idx != -1) {
            String[] paramsArr = uri.substring(idx + 1).split("&");

            for (String param : paramsArr) {
                idx = param.indexOf("=");
                params.put(param.substring(0, idx), param.substring(idx + 1));
            }
        }

        return params;
    }

    /**
     * 获取URI中参数以外部分路径
     *
     * @param uri
     * @return
     */
    public static String getBasePath(String uri) {
        if (uri == null || uri.isEmpty())
            return null;

        int idx = uri.indexOf("?");
        if (idx == -1)
            return uri;

        return uri.substring(0, idx);
    }
}
