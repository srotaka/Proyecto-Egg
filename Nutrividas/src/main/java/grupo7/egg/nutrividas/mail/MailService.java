package grupo7.egg.nutrividas.mail;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {


    private final JavaMailSender javaMailSender;


    private final String from = "nutrividas.info@gmail.com";

    private final Template template;


    public void sendWelcomeMail(String title, String mail, String name, Long id, String rol) {
        new Thread(() -> {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                String htmlMsg = getTemplate(name,id,rol);
                helper.setText(htmlMsg, true);
                helper.setTo(mail);
                helper.setSubject(title);
                helper.setFrom(from);
                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getTemplate(String name, Long id, String rol){
        switch (rol){
            case "USUARIO":
                return template.getTemplateConfirmMail(name,id);
            case "NUTRICIONISTA":
                return template.getTemplateConfirmMailNutri(name,id);
            case "COMEDOR":
                return template.getTemplateConfirmComedor(name,id);
            default:
                return null;
        }
    }

}
