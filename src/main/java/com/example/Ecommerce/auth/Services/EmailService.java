package com.example.Ecommerce.auth.Services;


import com.example.Ecommerce.auth.AuthticationEntities.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendMail(User user){
        String subject = "Please verify your email";
        String senderName = "Aether";
        String htmlContent = "<html><body style='font-family:Arial,sans-serif;'>"
                + "<h2>Welcome to Aether!</h2>"
                + "<p>Dear " + user.getFirstName() + ",</p>"
                + "<p>Please click the link below to verify your email:</p>"
                + "<p><a href=\"http://localhost:8080/api/auth/verify?name="
                + user.getUsername()
                + "&code="
                + user.getVerificationCode()
                + "\">"
                + "Verify your email address</a></p>"
                + "<br><p>Best regards,<br><strong>Aether Team</strong></p>"
                + "</body></html>";


        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        }
        catch (Exception e){
            e.printStackTrace();
            return "Error while Sending Mail: " + e.getMessage();
        }
        return "Email sent";
    }


}
