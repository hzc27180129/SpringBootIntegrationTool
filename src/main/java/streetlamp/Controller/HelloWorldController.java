package streetlamp.Controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import streetlamp.mapper.WebsiteMapper;
import streetlamp.pojo.Website;
import streetlamp.rabbitmq.config.RabbitmqConfig;
import streetlamp.rabbitmq.producer.RabbitmqProducer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class HelloWorldController {
    @Autowired
    WebsiteMapper websiteMapper;

    @Autowired
    RabbitmqProducer rabbitmqProducer;


    @RequestMapping("/hello")
    @ResponseBody
    public List<Website> hello() throws InterruptedException {
        rabbitmqProducer.produceTopics(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.email","测试rabbitmq");
        PageHelper.startPage(2, 2);
        Map param = new HashMap();
        Page<Website> data = websiteMapper.findByPaging(param);
        return data;
    }

//    @Autowired
//    ClientBoot clientBoot;
//
//    @RequestMapping("/hello")
//    @ResponseBody
//    public List<Website> hello() throws InterruptedException {
//        clientBoot.sendMsg("aaaaaaaaa");
//        PageHelper.startPage(2, 2);
//        Map param = new HashMap();
//        Page<Website> data = websiteMapper.findByPaging(param);
//        return data;
//    }

//    具备扎实的 JAVA 基础，数据结构与算法，拿提并发编程，集合，网络编程等相关知识精通JVM 内存模型，熟悉类加载过程，垃圾回收算法和垃圾回收器特性，有丰富的问题排查和性能调优经验
//    精通 MySql 的底层结构，执行流程，锁，索引，事务，MVCC 原理，热练掌握表结构设计，索引优化能力
//    精通 Redis 的执行流程，数据类型，持久化和过期策略，熟悉 Redis 的高可用架构原理。熟练掌握分布式下的开发经验，包括分布式事务，幂等设计，分布式理论等
//    熟练拿握SpringMyBatis，SpringCloud，Dubbo，Netty 等主流Java 开发框架
//    热练使用Maven， Git，RocketMO， Kafka， x-job，Apollo， Grafana、Prometheus， Elasticsearch 等中间件熟练使用 Linux 常用命今，以及 Linux 的环境搭建，项目部署，日志分析，问题排查熟委设计模式，在项目中能熟练使用单例，策略。适配器，模板方法等模式获得中级软件设计师，计算机二级(C 语言》，三级《网络技术)，CET-6 等证书
}
