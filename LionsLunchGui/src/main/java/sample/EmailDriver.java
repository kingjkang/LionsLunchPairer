package sample;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by justinkang on 6/27/16.
 */
public class EmailDriver {
    private class SMTPAuthenticator extends Authenticator
    {
        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password)
        {
            authentication = new PasswordAuthentication(login, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return authentication;
        }
    }


    public static void sendList(){
        String host = "smtp.gmail.com";
        String port = "587";
        final String userName = "LionsLunchPairing@gmail.com";
        final String password = "sunflowerseed";
        String toAddress = "justin.kang@utexas.edu";
        String subject = "Lions lunches Weekly Pairings";
        String message = "here are the pairs for the week!";
        String[] attachFiles = null;
        //attachFiles[0] = ""; //address of the file i create

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", userName);
        props.put("mail.password", password);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(userName));
            InternetAddress[] toAddresses = new InternetAddress[0];
            toAddresses = new InternetAddress[]{ new InternetAddress(toAddress) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        try {
            messageBodyPart.setContent(message, "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        try {
            multipart.addBodyPart(messageBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                try {
                    multipart.addBodyPart(attachPart);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

        // sets the multi-part as e-mail's content
        try {
            msg.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // sends the e-mail
        try {
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
