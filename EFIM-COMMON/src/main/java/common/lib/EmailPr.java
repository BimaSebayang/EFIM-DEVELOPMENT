package common.lib;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailPr {
	
	 //open this to make more insecure gmail https://myaccount.google.com/lesssecureapps?pli=1
	
	
	 private static String USER =  "efim.management123@gmail.com";
	 private static String PASS =  "Roxas0309.";
	
	 public String sendMail(String toRecipient, String message, String subject, String messageIfSendSucces) {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(USER,PASS);
	            }
	        });
	        try {
	            MimeMessage msg = new MimeMessage(session);
	            InternetAddress[] address = InternetAddress.parse(toRecipient, true);
	            msg.setRecipients(Message.RecipientType.TO, address);
	            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
	            msg.setSubject(subject);
	            msg.setSentDate(new Date());
	            msg.setHeader("XPriority", "1");
	            
	            //ini bagian jika ingin mengirimkan sesuatu  --start
	            InternetHeaders headers = new InternetHeaders();
	            headers.addHeader("Content-type", "text/html; charset=UTF-8");
	            String html = message;
	            MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(body);
	            msg.setContent(multipart);
	          //ini bagian jika ingin mengirimkan sesuatu  --end
	            
	            
	            Transport.send(msg);
	            System.out.println("Mail has been sent successfully");
	            return messageIfSendSucces;
	        } catch (MessagingException mex) {
	            System.out.println("Unable to send an email" + mex);
	            return "Unable to send an email" + mex;
	        } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "Unable to send an email" + e;
			}
	    }
}
