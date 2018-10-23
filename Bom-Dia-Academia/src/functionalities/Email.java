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
	 * Method to receive email´s with iscte information from outlook account (@iscte-iul.pt)
	 * 
	 * @param email email we want to receive emails
	 * @param password password for the email
	 * @param values array to insert emails
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
			javax.mail.Message[] messages = inbox.getMessages(); //search(new FlagTerm(new Flags(Flag.SEEN), false));

			for (int i = messages.length - 25; i < messages.length; i++) {
				javax.mail.Message message = messages[i];
				Address[] froms = message.getFrom();
				String from = ((InternetAddress) froms[0]).getAddress();

				if (from.contains("iscte")) {
					values.add(new String[] { message.getReceivedDate().toString() , "Email", from, message.getSubject(), 
							message.getContent().toString(), "View" });
					//System.out.println(message.getContent().toString());
				}
			}

			inbox.close(false);
			store.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
