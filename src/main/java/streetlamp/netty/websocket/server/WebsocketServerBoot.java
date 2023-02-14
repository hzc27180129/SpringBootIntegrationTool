package streetlamp.netty.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import streetlamp.netty.websocket.common.WebsocketProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Slf4j
public class WebsocketServerBoot {

    @Resource
    ServerBootstrap websocketServerBootstrap;
    @Resource
    NioEventLoopGroup websocketBoosGroup;
    @Resource
    NioEventLoopGroup websocketWorkerGroup;
    @Autowired
    WebsocketProperties websocketProperties;

    /**
     * 开机启动
     *
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        // 绑定端口启动
        websocketServerBootstrap.bind(websocketProperties.getPort()).sync();
        log.info("启动Websocket端口服务器: {}", websocketProperties.getPort());
    }

    /**
     * 关闭线程池
     */
    @PreDestroy
    public void close() throws InterruptedException {
        log.info("优雅得关闭Websocket服务器");
        websocketBoosGroup.shutdownGracefully();
        websocketWorkerGroup.shutdownGracefully();
    }
}
