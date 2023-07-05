package ca.access.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author: Lei Fu
 * @date: 2023/07/05
 * @description: ca.access.email
 */
@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    /**
     * @param from
     *        email sender
     * @param to
     *        recipient
     * @param cc
     *        cc
     * @param subject
     *        email subject
     * @param content
     *        content of email
     */
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        simpMsg.setFrom(from);
        simpMsg.setTo(to);
        simpMsg.setCc(cc);
        simpMsg.setSubject(subject);
        simpMsg.setText(content);
        javaMailSender.send(simpMsg);
    }
}
