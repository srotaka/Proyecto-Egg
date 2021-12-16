package grupo7.egg.nutrividas.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailNotificationService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String from = "zabalaflorencia.pruebascodigo2021@gmail.com";

    @Autowired
    private Template template;



    @Async
    public void sendMail(String title,String mail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = template.getTemplateConfirmMail();
            helper.setText(htmlMsg, true);
            helper.setTo(mail);
            helper.setSubject(title);
            helper.setFrom(from);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendWelcomeMail(String title, String mail) {
         new Thread(() -> {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                String htmlMsg = template.getTemplateConfirmMail();
                helper.setText(htmlMsg, true);
                helper.setTo(mail);
                helper.setSubject(title);
                helper.setFrom(from);
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
             e.printStackTrace();
            }
       }).start();
    }


    /*public void prueba() throws MessagingException {
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("newtesting920@gmail.com");
        mailSender.setPassword("calavera*calabaza");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo("zabalafaz@gmail.com");
        helper.setText(template.getTemplateConfirmMail(),true);
        mailSender.send(message);
    }*/
}
