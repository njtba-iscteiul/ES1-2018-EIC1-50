package functionalities;

import java.io.*;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;

public class Email {

	private boolean emailConnect = false;
	private boolean sent = false;
	
	/**
	 * 
	 * Method to receive email�s with iscte information from outlook account
	 * (@iscte-iul.pt)
	 * 
	 * @param email    email we want to receive emails
	 * @param password password for the email
	 * @param values   array to insert emails
	 */
	public void receiveEmail(String email, String password, List<String[]> values) {
		try {

			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap-mail.outlook.com", 993, email, password);

			Folder inbox = store.getFolder("Inbox");

			inbox.open(Folder.READ_ONLY);

			javax.mail.Message[] messages = inbox.getMessages();

			for (int i = messages.length - 25; i < messages.length; i++) {
				javax.mail.Message message = messages[i];
				Address[] froms = message.getFrom();
				String from = ((InternetAddress) froms[0]).getAddress();

				if (from.toLowerCase().contains("iscte") || message.getSubject().toLowerCase().contains("iscte")) 
					values.add(new String[] { message.getReceivedDate().toString(), "Email", from, message.getSubject(),
							getTextFromMessage(message), "View"});
			}

			inbox.close(false);
			store.close();
			emailConnect = true;
		} catch (Exception e) {
			emailConnect = false;
		}
	}
	
	/**
	 * Verifies the message type (text, image, html)
	 * @param message message to verify
	 * @return message in text
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMessage(javax.mail.Message message) throws MessagingException, IOException {
		
		String result = "";
		if (((Part) message).isMimeType("text/plain")) {
			result = ((Part) message).getContent().toString();
		} else if (((Part) message).isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) ((Part) message).getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	/**
	 * When it is not a text message it will remove the html tags
	 * @param mimeMultipart message in a mimeMultipart format
	 * @return message in text
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break;
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}
	
	/**
	 * Sends an email
	 * @param sendTo destination user
	 * @param subject email's subject
	 * @param content email's content
	 * @param email sender's email
	 * @param password sender's email password
	 */
	public void sendEmail(String sendTo, String subject, String content, String email, String password) {
		
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
          });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(sendTo));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            sent = true;

        } catch (MessagingException e) {
        	sent = false;
        	JOptionPane.showMessageDialog(null, "Email not sent, verify if email is correct", "Email", 1);
        }
	}
	
	public boolean getEmailConnect() {
		return emailConnect;
	}

	public boolean getSent() {
		return sent;
	}

}
