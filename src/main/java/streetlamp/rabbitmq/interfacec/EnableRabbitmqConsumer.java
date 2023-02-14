package streetlamp.rabbitmq.interfacec;

import org.springframework.context.annotation.Import;
import streetlamp.netty.websocket.server.WebsocketServerBoot;
import streetlamp.rabbitmq.consumer.RabbitmqConsumer;

import java.lang.annotation.*;

@Import(RabbitmqConsumer.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableRabbitmqConsumer {
}
