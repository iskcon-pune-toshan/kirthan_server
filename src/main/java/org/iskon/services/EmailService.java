package org.iskon.services;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}
