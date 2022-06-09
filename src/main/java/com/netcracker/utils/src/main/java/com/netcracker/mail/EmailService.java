package com.netcracker.mail;
import com.netcracker.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("kuzkalinka@gmail.com");
        mail.setTo(email.getRecipient());
        mail.setSubject(email.getSubject());
        mail.setText(email.getMessage());
        javaMailSender.send(mail);

    }
}
