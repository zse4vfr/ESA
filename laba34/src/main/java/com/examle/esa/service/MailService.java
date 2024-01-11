package com.examle.esa.service;

import lombok.RequiredArgsConstructor;
import com.examle.esa.entity.LogEvent;
import com.examle.esa.entity.MailCondition;
import com.examle.esa.jms.ConsumerMail;
import com.examle.esa.repository.MailConditionRepo;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailConditionRepo mailConditionRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMail.class);

    public void sendMail(String address, LogEvent logEvent) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(address);
        simpleMailMessage.setSubject(
                String.format("Change in %s", logEvent.getTableName()));
        simpleMailMessage.setText(
                String.format(
                        "Change in %s of type %s. %s",
                        logEvent.getTableName(),
                        logEvent.getEventType(),
                        logEvent.getDescription()));
        mailSender.send(simpleMailMessage);
    }

    public void resolve(LogEvent logEvent) {
        List<MailCondition> mails = mailConditionRepo.findAllByIsDeletedFalse();
        mails.forEach(mail -> {
            try {
                JSONObject condition = new JSONObject(mail.getCondition());
                if (condition.opt(logEvent.getEventType()) == null)
                    return;
                var conditionList = ((JSONArray)condition.opt(logEvent.getEventType())).toList();
                if (conditionList.contains(logEvent.getTableName()) || conditionList.contains("any"))
                    sendMail(mail.getAddress(), logEvent);
            }
            catch (Exception e){
                LOGGER.info(e.getMessage(), e);
            }
        });
    }
}