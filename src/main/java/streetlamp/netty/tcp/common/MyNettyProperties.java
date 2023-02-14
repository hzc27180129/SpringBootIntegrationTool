package streetlamp.netty.tcp.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "netty")
@Data
@Configuration
public class MyNettyProperties {
    /**
     * boss线程数量 默认为cpu线程数*2
     */
    private Integer boss;
    /**
     * worker线程数量 默认为cpu线程数*2
     */
    private Integer worker;
    /**
     * 连接超时时间 默认为30s
     */
    private Integer timeout = 30000;
    /**
     * 服务器主端口 默认7000
     */
    private Integer port = 7000;
    /**
     * 服务器备用端口 默认70001
     */
    private Integer portSalve = 7001;
    /**
     * 服务器地址 默认为本地
     */
    private String host = "127.0.0.1";
}
