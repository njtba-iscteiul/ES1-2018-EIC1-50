package junit.files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import files.Xml;

class XmlTest {

	File file;
	Document document;
	String PASSWORD = "admin";
	
	Xml xml= new Xml();
	
	@Test
	void test() {
		addRegister();
		if(xml.getListLength() == 0) {
			addFilters();
			createFile();
			createDocument();
			writeFile();
			readFile();
			saveFile();
			checkPassword();
			getFilters();
		}
	}

	private void addRegister() {
		String email = "teste@iscte-iul.pt";
		String username = "Teste";
		String password = "teste";
		String emailPassword = "teste";
		String consumerKey = "teste";
		String consumerSecret = "teste";
		String accessToken = "teste";
		String accessTokenSecret = "teste";
		
		xml.addRegister(email, username, password, emailPassword, consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	private void addFilters() {
		xml.addFilters();		
	}

	private void createFile() {
		xml.createFile();
	}

	private void createDocument() {
		xml.createDocument();
	}

	private void writeFile() {
		xml.writeFile(document);
	}

	private void readFile() {
		JTextArea textArea = new JTextArea();
		xml.readFile(textArea);
	}

	private void saveFile() {
		JTextArea textArea = new JTextArea();
		xml.saveFile(textArea);
	}

	private void checkPassword() {
		JPanel panel = new JPanel();
		xml.checkPassword(panel);
	}

	private void getFilters() {
		xml.getFilters();
	}

}
