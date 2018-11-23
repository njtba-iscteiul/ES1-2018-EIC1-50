package functionalities;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import files.Xml;

public class Login {
	
	private static String userEmail;
	private static String userPassword;
	private static String consumerKey;
	private static String consumerSecret;
	private static String accessToken;
	private static String accessTokenSecret;
	
	/**
	 * Method to sign in, confirms user in config.xml file if that user exists the login is successful.
	 * 
	 * @param username username we want to login
	 * @param password account password of username
	 * @return
	 */
	public static boolean login(String usernameText, String passwordText) {
		
		Xml.createFile();

		Document document = Xml.createDocument();

		NodeList userList = document.getElementsByTagName("User");

		if (userList.getLength() == 0) {
			JOptionPane.showMessageDialog(null, "No users", "Login", 1);
		} else {
			for (int i = 0; i < userList.getLength(); i++) {

				NodeList usernameList = document.getElementsByTagName("Username");

				if (usernameList.item(i).getTextContent().equals(usernameText)) {

					NodeList passwordList = document.getElementsByTagName("Password");

					if (passwordList.item(i).getTextContent().equals(passwordText)) {
						JOptionPane.showMessageDialog(null, "Successful login! \nClick ok and wait until the \nconnections are established!", "Login", 1);
						userEmail = document.getElementsByTagName("Email").item(i).getTextContent();
						userPassword = document.getElementsByTagName("Email_Password").item(i).getTextContent();
						consumerKey = document.getElementsByTagName("ConsumerKey").item(i).getTextContent();
						consumerSecret = document.getElementsByTagName("ConsumerSecret").item(i).getTextContent();
						accessToken = document.getElementsByTagName("AcessToken").item(i).getTextContent();
						accessTokenSecret = document.getElementsByTagName("AcessTokenSecret").item(i).getTextContent();
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "Password incorrect", "Login", 1);
						break;
					}
				} else {
					if (i == userList.getLength() - 1)
						JOptionPane.showMessageDialog(null, "User doesn´t exist", "Login", 1);
				}
			}
		}
		return false;
	}
	
	public static String getUserEmail() {
		return userEmail;
	}
	
	public static String getUserPassword() {
		return userPassword;
	}
	
	public static String getConsumerKey() {
		return consumerKey;
	}
	
	public static String getConsumerSecret() {
		return consumerSecret;
	}
	
	public static String getAcessToken() {
		return accessToken;
	}
	
	public static String getAcessTokenSecret() {
		return accessTokenSecret;
	}
}
