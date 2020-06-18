package com.juxue.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Revicer {
  private static final Logger logger  = LoggerFactory.getLogger(Revicer.class);

    @RabbitListener(bindings=@QueueBinding(value=@Queue("device.manage.queue"),exchange=@Exchange(name ="device.manage.exchange" , type = ExchangeTypes.TOPIC) , key = "device.manage.#"))
    @RabbitHandler
    @SendTo( value = "device.manage.queue")
    public String deviceManageHandler(Message mqMsg){
        System.out.println(mqMsg.toString());
        logger.error("[MQ][QUEUE=device.manage.frs.queue][REQUEST][BODY={}]",mqMsg.getBody());
        return "device.manage.queue";
    }


    @RabbitListener(bindings=@QueueBinding(value=@Queue("device.except.queue"),exchange=@Exchange(name ="device.manage.exchange" , type = ExchangeTypes.TOPIC) , key = "device.excpet.#"))
    @RabbitHandler
    @SendTo( value = "device.except.queue")
    public String deviceHandler(Message mqMsg){

        System.out.println(mqMsg.toString());
        logger.error("[MQ][QUEUE=device.manage.frs.queue][REQUEST][BODY={}]",mqMsg.getBody());
        return "device.except.queue";
    }

}
