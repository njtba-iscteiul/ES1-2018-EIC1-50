import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {

	private static File f;
	private static Document document;
	
	public static void addRegister(String emailText, String usernameText, String passwordText) {
		
		createFile();
		
		createDocument();
	 		
		NodeList list = document.getElementsByTagName("Users");
		Element users = null;

		if(list.getLength() == 0) {
			users = document.createElement("Users");
			document.appendChild(users);
		}
		else {
			Node node = list.item(0);
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
	}
	
	public static boolean login(String usernameText, String passwordText) {
		
		createFile();
		
		createDocument();
 		
		NodeList userList = document.getElementsByTagName("User");
		
		if(userList.getLength() == 0) {
			JOptionPane.showMessageDialog(null, "No users", "Login", 1);
		}
		else {
			for (int i = 0; i < userList.getLength(); i++) {
				
				NodeList usernameList = document.getElementsByTagName("Username");
				
				if(usernameList.item(i).getTextContent().equals(usernameText)) {
					
					NodeList passwordList = document.getElementsByTagName("Password");
					
					if(passwordList.item(i).getTextContent().equals(passwordText)) {
						return true;
					}
					else {
						JOptionPane.showMessageDialog(null, "Password incorrect", "Login", 1);
						break;
					}
				}
				else {
					if(i == userList.getLength() - 1)
						JOptionPane.showMessageDialog(null, "User doesn´t exist", "Login", 1);
				}
			}
		}
		
		return false;
	}
	
	public static boolean confirmData(String email, String username, String password, String confirmPassword, 
			String emailPassword, String facebookPassword, String twitterPassword) {
		
		String email_regex = "^[\\w-\\+]+(\\.[\\w]+)*@iscte-iul.pt";
		
		Pattern pattern = Pattern.compile(email_regex, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(email);
		
		if(email.equals("") || matcher.matches() == false) {
			JOptionPane.showMessageDialog(null, "Email incorrect or missing", "Email", 1);
		}
		else {
			
			if(username.equals("")) {
				JOptionPane.showMessageDialog(null, "Username missing", "Username", 1);
			}
			else {
				if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "Password missing", "Password", 1);
				}
				else {
					if(confirmPassword.equals("") || !confirmPassword.equals(password)) {
						JOptionPane.showMessageDialog(null, "Confirm password missing or not equal a password", "Password", 1);
					}
					else {
						if(emailPassword.equals("")) {
							JOptionPane.showMessageDialog(null, "Email password missing", "Password", 1);
						}
						else {
							if(facebookPassword.equals("")) {
								JOptionPane.showMessageDialog(null, "Facebook password missing", "Password", 1);
							}
							else {
								if(twitterPassword.equals("")) {
									JOptionPane.showMessageDialog(null, "Twitter password missing", "Password", 1);
								}
								else {
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
	
	public static void createFile() {
		
		f = new File("./config.xml");
		
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	private static void createDocument() {
		
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
		
		String path;
		String filename;
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
}
