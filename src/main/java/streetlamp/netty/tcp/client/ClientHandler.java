package streetlamp.netty.tcp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author gyl
 * @date 2022/5/11 - 14:57
 */
public class ClientHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("encoder", new StringEncoder(Charset.forName("GBK")));
        pipeline.addLast("decoder", new StringDecoder(Charset.forName("GBK")));
        pipeline.addLast(new ClientListenerHandler());
    }
}
