package com.scienceminer.mailMonitor;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Mailer
{

   public static final Logger log4j = LogManager.getLogger(Mailer.class);
   private static String username;
   private static String password; 


   private static final LoadProps loadProps = new LoadProps();
   
   static {
	   
	   try {
		   loadProps.loadProps1();
	   } catch (IOException e) {
		   log4j.error(" Exception : " + e.getMessage());
		   System.out.println(e.toString());
	   }

	   username = loadProps.getProperty("username");
	   password = loadProps.getProperty("password");
	   
	   log4j.info("username:" + username + " and password: " + password );
   }
   
   
   public static void sendMail( String subject, String messageText )
   { 
	   
       Properties props = new Properties();
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");

       Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
           }
         });

	   log4j.debug("Sent message successfully....");
	   
      // Recipient's email ID needs to be mentioned.
      String to = Mailer.username;

      // Sender's email ID needs to be mentioned
      String from = Mailer.username;

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject( subject );

         // Now set the actual message
         message.setText(messageText);           	

         // Send message
         Transport.send(message);
         log4j.debug("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
   
   public static void main(String[] args)
   {
	   String name = "This is a test";
	   String subject = "Test subject ";
	   
	   Mailer m = new Mailer();
	   
	   m.sendMail(subject, name);
   }
   
}