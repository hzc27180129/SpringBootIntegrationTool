package streetlamp.netty.tcp.interfacec;

import java.lang.annotation.*;

/**
 * 组合注解
 * @author gyl
 * @date 2022/5/12 - 10:29
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableNettyClient
@EnableNettyServer
public @interface EnableNetty {
}
