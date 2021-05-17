/*
 * package org.iskon.services;
 * 
 * import java.io.File; import java.io.IOException; import java.util.Map;
 * 
 * import javax.mail.MessagingException; import javax.mail.internet.MimeMessage;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.core.io.FileSystemResource; import
 * org.springframework.core.io.Resource; import
 * org.springframework.mail.MailException; import
 * org.springframework.mail.SimpleMailMessage; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.mail.javamail.MimeMessageHelper; import
 * org.springframework.stereotype.Service;
 * 
 * @Service("EmailService") public class EmailServiceImpl implements
 * EmailService {
 * 
 * private static final String NOREPLY_ADDRESS = "noreply@volstory.com";
 * 
 * @Autowired private JavaMailSender emailSender;
 * 
 * public void sendSimpleMessage(String to, String subject, String text) { try {
 * SimpleMailMessage message = new SimpleMailMessage();
 * message.setFrom(NOREPLY_ADDRESS); message.setTo(to);
 * message.setSubject(subject); message.setText(text);
 * 
 * emailSender.send(message); } catch (MailException exception) {
 * exception.printStackTrace(); } }
 * 
 * @Override public void sendMessageWithAttachment(String to, String subject,
 * String text, String pathToAttachment) { try { MimeMessage message =
 * emailSender.createMimeMessage(); // pass 'true' to the constructor to create
 * a multipart message MimeMessageHelper helper = new MimeMessageHelper(message,
 * true);
 * 
 * helper.setFrom(NOREPLY_ADDRESS); helper.setTo(to);
 * helper.setSubject(subject); helper.setText(text);
 * 
 * FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
 * helper.addAttachment("Invoice", file);
 * 
 * emailSender.send(message); } catch (MessagingException e) {
 * e.printStackTrace(); } } }
 */