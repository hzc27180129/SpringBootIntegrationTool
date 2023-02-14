package streetlamp.netty.websocket.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "websocket")
public class WebsocketProperties {
    private Integer port = 8089; // 监听端口
    private String path = "/ws"; // 请求路径
    private Integer boss = 2; // bossGroup线程数
    private Integer work = 2; // workGroup线程数
}
