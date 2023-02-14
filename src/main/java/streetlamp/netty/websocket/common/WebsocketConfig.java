package streetlamp.netty.websocket.common;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import streetlamp.netty.websocket.server.WebsocketServerHandler;

@Configuration
@EnableConfigurationProperties
public class WebsocketConfig {
    @Autowired
    WebsocketProperties websocketProperties;

    @Value("${server.port}")
    private int port;

    /**
     * boss 线程池
     * 负责客户端连接
     *
     * @return
     */
    @Bean
    public NioEventLoopGroup websocketBoosGroup() {
        return new NioEventLoopGroup(websocketProperties.getBoss());
    }

    /**
     * worker线程池
     * 负责业务处理
     *
     * @return
     */
    @Bean
    public NioEventLoopGroup websocketWorkerGroup() {
        return new NioEventLoopGroup(websocketProperties.getWork());
    }

    /**
     * 服务器启动器
     *
     * @return
     */
    @Bean()
    public ServerBootstrap websocketServerBootstrap() {
        ServerBootstrap websocketServerBootstrap = new ServerBootstrap();
        websocketServerBootstrap
                .option(ChannelOption.SO_BACKLOG, 1024)
                .group(websocketWorkerGroup(), websocketBoosGroup())   // 指定使用的线程组
                .channel(NioServerSocketChannel.class) // 指定使用的通道
                .localAddress(this.port)
                .childHandler(new WebsocketServerHandler()); // 指定worker处理器
        return websocketServerBootstrap;
    }

//    /**
//     * 客户端启动器
//     * @return
//     */
//    @Bean
//    public Bootstrap bootstrap(){
//        // 新建一组线程池
//        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(websocketProperties.getBoss());
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap
//                .group(eventExecutors)   // 指定线程组
//                .option(ChannelOption.SO_KEEPALIVE, true)
//                .channel(NioSocketChannel.class) // 指定通道
//                .handler(new C()); // 指定处理器
//        return bootstrap;
//    }
}
