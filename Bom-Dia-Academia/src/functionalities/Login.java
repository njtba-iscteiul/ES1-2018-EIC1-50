package functionalities;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import xml.Xml;

public class Login {
	
	/**
	 * 
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
						JOptionPane.showMessageDialog(null, "Sucessful login!", "Login", 1);
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
}
