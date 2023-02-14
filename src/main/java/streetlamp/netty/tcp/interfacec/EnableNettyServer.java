package streetlamp.netty.tcp.interfacec;

import org.springframework.context.annotation.Import;
import streetlamp.netty.tcp.server.ServerBoot;

import java.lang.annotation.*;

@Import(ServerBoot.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableNettyServer {
}
