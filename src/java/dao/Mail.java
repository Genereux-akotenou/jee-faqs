package dao;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {

    private static final String from = "answer.ifri@gmail.com";
    private static final String host = "smtp.gmail.com";
    private static final String user = "answer.ifri@gmail.com";
    private static final String pwd = "1234/ifri/1234";

    public static boolean sendMail(String to, String subject, String msg) {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        /*props.put("mail.smtp.starttls.enable", "true");*/
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pwd);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            System.err.println("#01#");
            // Set From: header field
            message.setFrom(new InternetAddress(from));
            System.err.println("#02#");
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            System.err.println("#03#");
            // Set Subject: header field
            message.setSubject(subject);
            System.err.println("#04#");
            // Config for HTML emails
            Multipart multipart = new MimeMultipart();
            MimeBodyPart bodyMessagePart = new MimeBodyPart();
            bodyMessagePart.setContent(msg, "text/html;charset=utf-8");
            multipart.addBodyPart(bodyMessagePart);
            System.err.println("#05#");
            // Put the content of your message
            message.setContent(multipart);
            System.err.println("#06#");
            // Send message
            Transport.send(message);
            System.err.println("#07#");
            System.out.println("######Sent message successfully....###########");

        }
        catch (MessagingException e) {
            
            System.err.println("#######An error occur while trying to send mail######");
            throw new RuntimeException(e);
        }
        return true;
    }
    
    /*public static void main(String[] args) {
        Mail.sendMail("jeffrey.dvk.fr@gmail.com", "test test jee - ifri faqs", "ceci est un test d'envoie de mail");
    }*/
}
