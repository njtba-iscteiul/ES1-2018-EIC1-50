package functionalities;

import java.io.*;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import org.jsoup.Jsoup;
import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;

public class Email {

	/**
	 * 
	 * Method to receive email´s with iscte information from outlook account
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
			// System.out.println("No of Unread Messages : " +
			// inbox.getUnreadMessageCount());
			inbox.open(Folder.READ_ONLY);

			/* Get the messages which is unread in the Inbox */
			javax.mail.Message[] messages = inbox.getMessages(); // search(new FlagTerm(new Flags(Flag.SEEN), false));

			for (int i = messages.length - 25; i < messages.length; i++) {
				javax.mail.Message message = messages[i];
				Address[] froms = message.getFrom();
				String from = ((InternetAddress) froms[0]).getAddress();

				if (from.contains("iscte")) {
					values.add(new String[] { message.getReceivedDate().toString(), "Email", from, message.getSubject(),
							getTextFromMessage(message), "View" });
					// System.out.println(message.getContent().toString());
				}
			}

			inbox.close(false);
			store.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

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

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}
}
