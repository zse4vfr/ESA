package com.examle.esa.jms;

import lombok.RequiredArgsConstructor;
import com.examle.esa.entity.LogEvent;
import com.examle.esa.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerMail {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMail.class);
    private final MailService mailService;

    @RabbitListener(queues = {"${rabbitmq.queue.mail}"})
    public void consume(LogEvent logEvent){
        LOGGER.info(String.format("message received :: %s", logEvent.getEventType()));
        mailService.resolve(logEvent);
    }
}