package streetlamp.netty.websocket.interfacec;

import org.springframework.context.annotation.Import;
import streetlamp.netty.websocket.server.WebsocketServerBoot;

import java.lang.annotation.*;

@Import(WebsocketServerBoot.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableWebsocketServer {
}
