package com.examle.esa.jms;

import lombok.RequiredArgsConstructor;
import com.examle.esa.entity.LogEvent;
import com.examle.esa.repository.LogEventRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerLog.class);
    private final LogEventRepo logEventRepo;

    @RabbitListener(queues = {"${rabbitmq.queue.log}"})
    public void consume(LogEvent logEvent){
        LOGGER.info(String.format("message received :: %s", logEvent.getEventType()));
        logEventRepo.save(logEvent);
    }
}