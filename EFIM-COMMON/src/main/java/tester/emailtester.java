package tester;

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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import common.dto.headuser.TblDataUserDto;

public class emailtester {
	
	//open this to make more insecure gmail https://myaccount.google.com/lesssecureapps?pli=1
	
	public static void main(String[] args) {
		sendMail();
	}
	
	 public static void sendMail() {
	        //Setting up configurations for the email connection to the Google SMTP server using TLS
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        //Establishing a session with required user details
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("efim.management123@gmail.com", "Roxas0309.");
	            }
	        });
	        try {
	            MimeMessage msg = new MimeMessage(session);
	            String to = "bimasebayang11@gmail.com";
	            InternetAddress[] address = InternetAddress.parse(to, true);
	            //Setting the recepients from the address variable
	            msg.setRecipients(Message.RecipientType.TO, address);
	            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
	            msg.setSubject("Sample Mail : " + timeStamp);
	            msg.setSentDate(new Date());
	            msg.setText("Sampel System Generated mail");
	            msg.setHeader("XPriority", "1");
	            
	            InternetHeaders headers = new InternetHeaders();
	            headers.addHeader("Content-type", "text/html; charset=UTF-8");
	            TblDataUserDto dto = new TblDataUserDto();
	            dto.setUserId("hahah");
	            dto.setUserName("bima");
	            String html = "Mau masuk ke dalam \n"+ "\n<a href='http://localhost:28080/EFIM-UI/registeration/successPr.zul?id=1121213311'>Klik Link Ini</a>";
	            MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
	            
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(body);
	            msg.setContent(multipart);
	            Transport.send(msg);
	            System.out.println("Mail has been sent successfully");
	        } catch (MessagingException | UnsupportedEncodingException mex) {
	            System.out.println("Unable to send an email" + mex);
	        }
	    }
}
