package sample;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class SendMail {
    
        String to ;
        String from = "shubhmasand@gmail.com";

         public SendMail(String t) {
             to = t;
             send();
         }
         void send()
         {
             Properties properties = new Properties();
             properties.put("mail.smtp.auth", "true");
             properties.put("mail.smtp.starttls.enable", "true");
             properties.put("mail.smtp.host", "smtp.gmail.com");
             properties.put("mail.smtp.port", 587);
             Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication("shubhmasand@gmail.com", "shubhammasand89911131");
                 }

             });
             try {
                 MimeMessage message = new MimeMessage(session);

                 message.setFrom(new InternetAddress(from));

                 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                 message.setSubject("Hey its me");

                 BodyPart messageBodyPart1 = new MimeBodyPart();
                 messageBodyPart1.setText("Thanyou for shopping in galeria mall");

                 // creating second MimeBodyPart object
                 /*BodyPart messageBodyPart2 = new MimeBodyPart();
                 String filename = "C:\\Users\\shubh\\Application Data\\Desktop\\shubh_synopsis.pdf";
                 DataSource source = new FileDataSource(filename);
                 messageBodyPart2.setDataHandler(new DataHandler(source));
                 messageBodyPart2.setFileName("MeSynopsis.pdf");*/

                 // creating MultiPart object
                 Multipart multipartObject = new MimeMultipart();
                 multipartObject.addBodyPart(messageBodyPart1);
                 //multipartObject.addBodyPart(messageBodyPart2);

                 message.setContent(multipartObject);

                 Transport.send(message);
                 System.out.println("Mail successfully sent");
             } catch (MessagingException mex) {
                 mex.printStackTrace();
             }
         }

}
