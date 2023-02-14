package streetlamp.netty.tcp.interfacec;

import org.springframework.context.annotation.Import;
import streetlamp.netty.tcp.client.ClientBoot;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ClientBoot.class)
public @interface EnableNettyClient {
}
