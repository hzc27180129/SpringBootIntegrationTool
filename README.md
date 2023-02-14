# 个人开发快捷工具

## TCP服务器

1、包路径：streetlamp.netty.tcp

2、使用方法：在启动类添加@EnableNettyServer即可启动。streetlamp.netty.tcp.server.ServerListenerHandler是业务逻辑类

3、配置：
```
#boss线程数量
netty.boss=1   
#worker线程数量             
netty.worker=4    
#连接超时时间          
netty.timeout=6000 
#服务器主端口         
netty.port=7000     
#服务器备用端口       
netty.portSalve=7001
#服务器地址        
netty.host=127.0.0.1        
```
4、依赖
```
    <dependency>
        <groupId>io.netty</groupId>
        <artifactId>netty-all</artifactId>
    </dependency>
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.6.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
```


## Websocket服务器

1、包路径：streetlamp.netty.websocket

2、使用方法：在启动类添加@EnableWebsocketServer即可启动。streetlamp.netty.websocket.server.WebsocketServerListenerHandler是业务逻辑类

3、配置：
```
#boss线程数量
websocket.boss=1          
#worker线程数量      
websocket.worker=4      
#服务端口        
websocket.port=8089   
#请求路径          
websocket.path=/ws            
```
4、依赖
```
    <dependency>
        <groupId>io.netty</groupId>
        <artifactId>netty-all</artifactId>
    </dependency>
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.6.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
```

## RabbitMq队列

1、包路径：streetlamp.rabbitmq

2、使用方法：在启动类添加@EnableRabbitmqConsumer即可启动消费者服务。streetlamp.rabbitmq.consumer.RabbitmqConsumer为消费者业务逻辑类。streetlamp.rabbitmq.producer.RabbitmqProducer类的produceTopics方法生产消息。streetlamp.rabbitmq.config.RabbitmqConfig为配置类

3、配置：
```
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host=/
#确保消息从服务端发送到消费者的投递可靠。消息确认模式,no自动确认，auto根据情况确认，manual手动确认
spring.rabbitmq.listener.simple.acknowledge-mode = manual
#1、确保消息从发送端到服务端投递可靠（分为以下两个步骤）
#1.1、确认消息已发送到交换机(Exchange) 可以把publisher-confirms: true 替换为  publisher-confirm-type: correlate
spring.rabbitmq.publisher-confirm-type=correlated
#1.2、确认消息从交换机中到队列中
spring.rabbitmq.publisher-returns=true
```
4、依赖
```
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-amqp</artifactId>
   </dependency>
```

