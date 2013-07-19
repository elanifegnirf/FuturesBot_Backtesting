package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class addEvent {
	
	public static void main(String args[]) {
		addEvent runEvent = new addEvent();
		runEvent.GamilSender("philipzheng@gmail.com", "20130317204241.xls");
	}
	
	public boolean GamilSender(String email, String filename) {
		boolean result = false;
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.socketFactory.port", "25");
		props.put("mail.smtp.auth", "true");
		final String username = "GMAIL_ID";
		final String password = "GMAIL_PASSWD";
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		// -- Create a new message --
		Message msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		try {
			msg.setFrom(new InternetAddress("YOUR_EMAIL"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
					email, false));
			msg.setSubject("最佳化回測結果");
			//msg.setText(readFile(filenam));
			Attachment(msg, filename);
			msg.setSentDate(new Date());
			//Transport.send(msg);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", 465, username, password);
			transport.sendMessage(msg,msg.getAllRecipients());
			transport.close();
			result = true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String readFile(String file){
		String Temp = new String();
		String s1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while ((s1 = in.readLine()) != null) {
				Temp = Temp + s1 + "\r\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Temp;
	}
	
	public void Attachment(Message message, String filename){
		// Create the message part 
		BodyPart messageBodyPart1 = new MimeBodyPart();

		Multipart multipart = new MimeMultipart();
		try {
			DataSource source = new FileDataSource("D:\\Runtime\\" + filename);
			messageBodyPart1.setDataHandler(new DataHandler(source));
			messageBodyPart1.setFileName(filename);
			multipart.addBodyPart(messageBodyPart1);
			message.setContent(multipart);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
