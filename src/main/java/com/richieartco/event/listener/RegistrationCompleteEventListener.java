package com.richieartco.event.listener;

import com.richieartco.registration.token.VerificationTokenService;
import com.richieartco.event.RegistrationCompleteEvent;
import com.richieartco.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author Alfred Ochola
 */
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final VerificationTokenService tokenService;
    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //1. get the user
        user = event.getUser();
        //2. generate a token for the user
        String vToken = UUID.randomUUID().toString();
        //3. save the token for the user
        tokenService.saveVerificationTokenForUser(user, vToken);
        //4. Build the verification url
        String url = event.getConfirmationUrl()+"/registration/verifyEmail?token="+vToken;
        //5. send the email to the user
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "VERIFY RICHIEART ACCOUNT";
        String senderName = "RICHIEART ADMIN";
        String mailContent = "<p> Hello, "+ user.getFirstName()+ ", </p>"+
                 "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you for trusting Richieart<br> ";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }


    public void sendPasswordResetVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "VERIFY PASSWORD RESET FOR RICHIEART ACCOUNT";
        String senderName = "RICHIEART ADMIN";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>"+
                "<p><b>You recently requested to reset your password,</b>"+"" +
                "Please, follow the link below to complete the action.</p>"+
                "<a href=\"" +url+ "\">Reset password</a>"+
                "<p>Thank you for trusting RichieArt";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User theUser)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("bigezocrm@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }



}
