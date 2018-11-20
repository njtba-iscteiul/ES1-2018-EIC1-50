package junit.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import files.Xml;
import functionalities.Email;
import functionalities.Facebook;
import functionalities.Twitter;
import functionalities.ValuesOperations;
import gui.Panels;

class PanelsTest {
	
	private JFrame frame = new JFrame();
	private JTextField username;
	private List<String[]> values = new ArrayList<String[]>();
	private List<String[]> valuesToShow = new ArrayList<String[]>();
	private JComboBox filterComboBox = new JComboBox(Xml.getFilters());
	private JComboBox searchComboBox = new JComboBox(new String[] { "All time", "Last hour", "Last 24 hours", "Last week" });
	private JPasswordField facebookAccessToken;
	private Email email;
	private Facebook facebook;
	private Twitter twitter;
	private ValuesOperations valuesOperations = new ValuesOperations();

	Panels panels = new Panels(frame);	
	
	@Test
	void test() {
		loginPanel();
		registerPanel();
//		getMenuBar();
		startPanel();
		infoPanel();
		emailPanel();
		facebookPanel();
		twitterPanel();
		xmlPanel();
	}

	private void loginPanel() {
		panels.loginPanel();
	}

	private void registerPanel() {
		panels.registerPanel();
	}

	private void getMenuBar() {
		JPanel panel = new JPanel();
		panels.getMenuBar(panel);
	}
	
	private void startPanel() {
		panels.startPanel();
	}

	private void infoPanel() {
		panels.infoPanel();
	}

	private void emailPanel() {
		String sender = "";
		String subject = "";
		String content = "";
		panels.emailPanel(sender, subject, content);
	}

	private void facebookPanel() {
		String content = "";
		panels.facebookPanel(content);
	}

	private void twitterPanel() {
		String content = "";
		panels.twitterPanel(content);		
	}

	private void xmlPanel() {
		panels.xmlPanel();
	}
}
