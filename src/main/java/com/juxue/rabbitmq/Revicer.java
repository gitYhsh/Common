package com.juxue.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class Revicer {
  private static final Logger logger  = LoggerFactory.getLogger(Revicer.class);

    @RabbitListener(bindings=@QueueBinding(value=@Queue("device.manage.queue"),exchange=@Exchange(name ="device.manage.exchange" , type = ExchangeTypes.TOPIC) , key = "device.manage.#"))
    @RabbitHandler
    public void deviceManageHandler(Message mqMsg){
        System.out.println("device.manage===");
        System.out.println(mqMsg.toString());
        logger.error("[MQ][QUEUE=device.manage.frs.queue][REQUEST][BODY={}]",mqMsg.getBody());
    }


    @RabbitListener(bindings=@QueueBinding(value=@Queue("device.manage.queue"),exchange=@Exchange(name ="device.manage.exchange" , type = ExchangeTypes.TOPIC) , key = "device.manage.#"))
    @RabbitHandler
    public void deviceHandler(Message mqMsg){

        System.out.println("device.alame===");
        System.out.println(mqMsg.toString());
        logger.error("[MQ][QUEUE=device.manage.frs.queue][REQUEST][BODY={}]",mqMsg.getBody());
    }

}
