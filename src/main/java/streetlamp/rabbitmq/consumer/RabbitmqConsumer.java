package streetlamp.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import streetlamp.rabbitmq.config.RabbitmqConfig;

import java.io.IOException;

public class RabbitmqConsumer {
    //监听email队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(Object msg, Message message, Channel channel) throws IOException {
        System.out.println("QUEUE_INFORM_EMAIL msg"+msg);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        channel.basicReject(deliveryTag, true); //拒绝消息
        channel.basicAck(deliveryTag,true); //确认消息
    }
    //监听sms队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void receive_sms(Object msg, Message message, Channel channel){
        System.out.println("QUEUE_INFORM_SMS msg"+msg);
    }

}
