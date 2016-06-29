import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * Created by justinkang on 6/27/16.
 */
public class EmailController {
    public static void sendList(ArrayList<LionsLunchMemberModel> list){

        try {
            ListCreatorController.makeListFile(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String host = "smtp.gmail.com";
        String port = "587";
        final String userName = "LionsLunchPairing@gmail.com";
        final String password = "sunflowerseed";
        String toAddress = "justin.kang@utexas.edu";
        String subject = "Lions lunches Weekly Pairings";
        String message = "here are the pairs for the week!<br/><br/>" + createMessageBody(list);

        String[] attachFiles = new String[1];

        String pathPlus = System.getProperty("user.dir");
        String path = pathPlus + "/pairedList.txt";
        attachFiles[0] = path;

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

    public static void sendMember(ArrayList<LionsLunchMemberModel> list){

        int first = 0;
        int second = 1;
        for (int i = 0; i < list.size()/2; i++) {
            String host = "smtp.gmail.com";
            String port = "587";
            final String userName = "LionsLunchPairing@gmail.com";
            final String password = "sunflowerseed";
            String toAddress1 = list.get(first).getEmailAddress();
            String toAddress2 = list.get(second).getEmailAddress();
            String subject1 = "Lions Lunch Pairing";
            String subject2 = "Lions Lunch Pairing";
            String message1 = "Hello Lion " + list.get(first).getName() + ",<br/><br/>" + "Your lions lunch for this week is " + list.get(second).getName()
                    + "<br/>" + list.get(second).getName() + "'s Phone Number: " + list.get(second).getPhoneNumber()
                    + "<br/>" + list.get(second).getName() + "'s Email Address: " + list.get(second).getEmailAddress();
            String message2 = "Hello Lion " + list.get(second).getName() + ",<br/><br/>" + "Your lions lunch for this week is " + list.get(first).getName()
                    + "<br/>" + list.get(first).getName() + "'s Phone Number: " + list.get(first).getPhoneNumber()
                    + "<br/>" + list.get(first).getName() + "'s Email Address: " + list.get(first).getEmailAddress();

            Properties props1 = new Properties();
            props1.put("mail.smtp.host", host);
            props1.put("mail.smtp.port", port);
            props1.put("mail.smtp.auth", "true");
            props1.put("mail.smtp.starttls.enable", "true");
            props1.put("mail.user", userName);
            props1.put("mail.password", password);

            // creates a new session with an authenticator
            Authenticator auth1 = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session session = Session.getInstance(props1, auth1);

            Authenticator auth2 = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session session2 = Session.getInstance(props1, auth2);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);
            Message msg2 = new MimeMessage(session2);

            try {
                msg.setFrom(new InternetAddress(userName));
                msg2.setFrom(new InternetAddress(userName));
                InternetAddress[] toAddresses = new InternetAddress[0];
                InternetAddress[] toAddresses2 = new InternetAddress[0];
                toAddresses = new InternetAddress[]{new InternetAddress(toAddress1)};
                toAddresses2 = new InternetAddress[]{new InternetAddress(toAddress2)};
                msg.setRecipients(Message.RecipientType.TO, toAddresses);
                msg2.setRecipient(Message.RecipientType.TO, toAddresses2[0]);
                msg.setSubject(subject1);
                msg2.setSubject(subject2);
                msg.setSentDate(new Date());
                msg2.setSentDate(new Date());
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            try {
                messageBodyPart.setContent(message1, "text/html");
                messageBodyPart2.setContent(message2, "text/html");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            Multipart multipart2 = new MimeMultipart();
            try {
                multipart.addBodyPart(messageBodyPart);
                multipart2.addBodyPart(messageBodyPart2);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // sets the multi-part as e-mail's content
            try {
                msg.setContent(multipart);
                msg2.setContent(multipart2);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // sends the e-mail
            try {
                Transport.send(msg);
                Transport.send(msg2);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            first = first + 2;
            second = second + 2;
        }
    }

    public static String createMessageBody(ArrayList<LionsLunchMemberModel> list){
        String body = "";

        int first = 0;
        int second = 1;
        for (int i = 0; i < list.size()/2; i++){
            body = body + list.get(first).getName() + " (" + list.get(first).getEID() + ") -- " + list.get(second).getName() + " (" + list.get(second).getEID() + ")<br/>";
            first = first + 2;
            second = second + 2;
        }
        body = body + "<br/>";

        return body;
    }


}
