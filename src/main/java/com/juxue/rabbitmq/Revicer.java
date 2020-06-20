package com.juxue.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Revicer {
  private static final Logger logger  = LoggerFactory.getLogger(Revicer.class);

    @RabbitListener(bindings=@QueueBinding(value=@Queue(value = "device.manage.queue",durable="true"),
            exchange=@Exchange(name ="device.manage.exchange" , type = ExchangeTypes.TOPIC) ,
            key = "device.manage.#",
            arguments = {@Argument(name = "x-dead-letter-exchange", value = "order_exchange"),
                    @Argument(name = "x-dead-letter-routing-key", value = "DL_KEY"),
                    @Argument(name = "x-message-ttl", value = "6000")
                    }
    ))
    @RabbitHandler
    public void deviceManageHandler(Message mqMsg){
        System.out.println(mqMsg.toString());
        logger.error("[MQ][QUEUE=device.manage.frs.queue][REQUEST][BODY={}]",mqMsg.getBody());
        //return "device.manage.queue";
    }

    //监听死信队列
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order_dead_queue", durable = "true"),
            exchange = @Exchange(
                    value = "order_exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC,
                    durable = "true"
            ),
            key = {"DL_KEY"}
    ))
    public void deadQueuelisten(Message message) {
        LocalDateTime now = LocalDateTime.now();
        logger.info("死信队列: order_dead_queue,接收时间=[{}],order=[{}]", now.toString(), message);

    }





    @RabbitListener(bindings=@QueueBinding(value=@Queue("device.except.queue"),exchange=@Exchange(name ="device.manage.exchange" , type = ExchangeTypes.TOPIC) , key = "device.excpet.#"))
    @RabbitHandler
    public String deviceHandler(Message mqMsg){

        System.out.println(mqMsg.toString());
        logger.error("[MQ][QUEUE=device.manage.frs.queue][REQUEST][BODY={}]",mqMsg.getBody());
        return "device.except.queue";
    }

}
