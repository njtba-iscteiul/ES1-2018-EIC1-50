package functionalities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import xml.Xml;

public class Register {

	public static boolean confirmData(String email, String username, String password, String confirmPassword,
			String emailPassword, String facebookPassword, String twitterPassword) {

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
							if (facebookPassword.equals("")) {
								JOptionPane.showMessageDialog(null, "Facebook password missing", "Password", 1);
							} else {
								if (twitterPassword.equals("")) {
									JOptionPane.showMessageDialog(null, "Twitter password missing", "Password", 1);
								} else {
									Xml.addRegister(email, username, password);
									return true;
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
