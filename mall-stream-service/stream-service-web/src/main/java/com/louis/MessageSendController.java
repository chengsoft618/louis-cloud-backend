package com.louis;

import com.louis.message.spring.producer.MessageProducer;
import kafka.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.internals.Topic;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/7/24
 * Description:
 */

@Slf4j
@RestController
public class MessageSendController {

    @Autowired
    private MessageProducer producer;


    /***
     * 发送消息体为基本类型的消息
     */
    @GetMapping("sendSimple/{topic}")
    public void sendSimple(@PathVariable("topic")String topic) {
        producer.send(topic, "hello spring boot kafka");


    }
}
