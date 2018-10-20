package xml;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {

	private static File f;
	private static Document document;
	private final static String PASSWORD = "admin";
	
	public static void addRegister(String emailText, String usernameText, String passwordText) {
		
		createFile();
		
		createDocument();
		
		NodeList listProject = document.getElementsByTagName("Project");
		Node projectNode = listProject.item(0);
		Element project = (Element) projectNode;
	 		
		NodeList listUsers = document.getElementsByTagName("Users");
		Element users = null;

		if(listUsers.getLength() == 0) {
			users = document.createElement("Users");
			project.appendChild(users);
		}
		else {
			Node node = listUsers.item(0);
			users = (Element) node;
		}

		Element user = document.createElement("User");
		
		Element email = document.createElement("Email");
		email.appendChild(document.createTextNode(emailText));
		Element username = document.createElement("Username");
		username.appendChild(document.createTextNode(usernameText));
		Element password = document.createElement("Password");
		password.appendChild(document.createTextNode(passwordText));
		user.appendChild(email);
		user.appendChild(username);
		user.appendChild(password);
		users.appendChild(user);
		
		writeFile(document);
		
		JOptionPane.showMessageDialog(null, "Complete register!", "Register", 1);
	}
	
	private static void addFilters() {
		
		Element project = document.createElement("Project");
		
		document.appendChild(project);
		
		Element filters = document.createElement("Filters");
		
		project.appendChild(filters);
		
		Element allFilter = document.createElement("Filter");
		allFilter.appendChild(document.createTextNode("All"));
		Element emailFilter = document.createElement("Filter");
		emailFilter.appendChild(document.createTextNode("Email"));
		Element facebookFilter = document.createElement("Filter");
		facebookFilter.appendChild(document.createTextNode("Facebook"));
		Element twitterFilter = document.createElement("Filter");
		twitterFilter.appendChild(document.createTextNode("Twitter"));

		filters.appendChild(allFilter);
		filters.appendChild(emailFilter);
		filters.appendChild(facebookFilter);
		filters.appendChild(twitterFilter);
		
		writeFile(document);
	}
	
	public static void createFile() {
		
		f = new File("./config.xml");
		
		if(!f.exists()) {
			try {
				f.createNewFile();
				createDocument();
				addFilters();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static Document createDocument() {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		 
		DocumentBuilder documentBuilder = null;
		
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		try {
			document = documentBuilder.parse(f);
		} catch (SAXException e) {
			document = documentBuilder.newDocument();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;
	}
	
	private static void writeFile(Document document) {
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		
		DOMSource source = new DOMSource(document);
			
		StreamResult streamResult = new StreamResult(f);
		
		try {
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void readFile(JTextArea textArea) {
		
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		boolean endOfFile = false;
		  
		while(!endOfFile) {
			
			String lineIn = null;
			
			try {
				lineIn = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(lineIn == null) {
				endOfFile = true;
			}
			
			else {
				textArea.append(lineIn + "\n");
		    }
		}
	}

	public static void saveFile(JTextArea textArea) {
		
		String xmlText = textArea.getText();

		JFileChooser fileChooser = new JFileChooser(new File(f.getAbsolutePath()));
		fileChooser.setDialogTitle("Save as ...");

		int userSelection = fileChooser.showSaveDialog(fileChooser);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    try{
		        FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
		        fw.write(xmlText);
		        fw.flush();
		        fw.close();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static void checkPassword(JPanel atualPanel) {
			
		JPasswordField pwd = new JPasswordField(10);
		
		while(!pwd.getText().equals(PASSWORD)) {
				
			int option = JOptionPane.showConfirmDialog(null, pwd, "Enter password", 2);
			
			if(option == JOptionPane.CANCEL_OPTION) {
				atualPanel.show();
				break;
			}
			
			if(!pwd.getText().equals(PASSWORD))
				JOptionPane.showMessageDialog(null, "Password incorrect", "Alert", 2);
		}	
	}
	
	public static String[] getFilters() {
		
		createFile();
		
		createDocument();
		
		NodeList filter = document.getElementsByTagName("Filter");
		
		String[] filters = new String[filter.getLength()];
		
		for (int i = 0; i < filter.getLength(); i++) {
			Node node = filter.item(i);
			filters[i] = node.getTextContent();
		}
		
		return filters;
	}
}
