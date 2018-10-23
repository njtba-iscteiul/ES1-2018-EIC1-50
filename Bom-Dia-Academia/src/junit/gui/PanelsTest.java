package junit.gui;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.jupiter.api.Test;

import gui.Panels;

class PanelsTest {

	JFrame frame = new JFrame();
	Panels panels = new Panels(frame);	
	
	@Test
	void test() {
		loginPanel();
		registerPanel();
		getMenuBar();
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
