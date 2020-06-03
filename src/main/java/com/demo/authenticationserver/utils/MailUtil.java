package com.demo.authenticationserver.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    public static void sendmail(String email, String otp) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailInformation.MAIL_SERVER, EmailInformation.MAIL_SERVER_PW);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(EmailInformation.MAIL_SERVER, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Verification OTP");
        msg.setContent("Your verification Otp: " + otp, "text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);
    }
}
