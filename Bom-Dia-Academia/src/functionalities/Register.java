package functionalities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import files.Xml;

public class Register {
	
	/**
	 * 
	 * Method to confirm register data, email must be @iscte-iul.pt and the fields can't be null.
	 * 
	 * @param email email to register, this email is the same one we register on facebook and twitter accounts
	 * @param username username we want in the app
	 * @param password password we want in the app
	 * @param confirmPassword confirm password of the app
	 * @param emailPassword email password that we want to use in this account
	 * @param facebookPassword facebook password that we want to use in this account
	 * @param twitterPassword twitter password that we want to use in this account
	 * @return returns true if all data is valid and returns false if something is invalid
	 */

	public static boolean confirmData(String email, String username, String password, String confirmPassword,
			String emailPassword, String consumerKey, String consumerSecret, String acessToken, String acessTokenSecret) {

		String email_regex = "^[\\w-\\+]+(\\.[\\w]+)*@iscte-iul.pt";

		Pattern pattern = Pattern.compile(email_regex, Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(email);

		if (email.equals("") || matcher.matches() == false) {
			JOptionPane.showMessageDialog(null, "Email incorrect or missing", "Email", 1);
		} else {

			if (username.equals("")) {
				JOptionPane.showMessageDialog(null, "Username missing", "Username", 1);
			} else {
				if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "Password missing", "Password", 1);
				} else {
					if (confirmPassword.equals("") || !confirmPassword.equals(password)) {
						JOptionPane.showMessageDialog(null, "Confirm password missing or not equal a password",
								"Password", 1);
					} else {
						if (emailPassword.equals("")) {
							JOptionPane.showMessageDialog(null, "Email password missing", "Password", 1);
						} else {
							if (consumerKey.equals("")) {
								JOptionPane.showMessageDialog(null, "Consumer key missing", "Password", 1);
							} else {
								if (consumerSecret.equals("")) {
									JOptionPane.showMessageDialog(null, "Consumer secret missing", "Password", 1);
								} else {
									if (acessToken.equals("")) {
										JOptionPane.showMessageDialog(null, "Acess token missing", "Password", 1);
									}else {
										if (acessTokenSecret.equals("")) {
											JOptionPane.showMessageDialog(null, "Acess token secret missing", "Password", 1);
										}else {
											Xml.addRegister(email, username, password, emailPassword, consumerKey, consumerSecret,
													acessToken, acessTokenSecret);
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}
