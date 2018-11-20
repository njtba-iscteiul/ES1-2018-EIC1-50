package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.mail.MessagingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableCell;

import files.Json;
import files.Xml;
import functionalities.Email;
import functionalities.Facebook;
import functionalities.Login;
import functionalities.Register;
import functionalities.Twitter;
import functionalities.ValuesOperations;
import table.ButtonEditor;
import table.ButtonRenderer;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class Panels {

	private JFrame frame;
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

	/**
	 * Panels constructor
	 * 
	 * @param JFrame App frame
	 */
	public Panels(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * First app panel, in this panel we can login or register a new account
	 */
	public void loginPanel() {

		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel);

		loginPanel.setLayout(null);

		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameLabel.setBounds(66, 25, 67, 24);

		username = new JTextField();
		username.setBounds(143, 27, 153, 22);

		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordLabel.setBounds(66, 86, 67, 24);

		JPasswordField password = new JPasswordField();
		password.setBounds(143, 87, 153, 23);

		JButton login = new JButton("Login");
		login.setBounds(66, 217, 95, 40);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean login = Login.login(username.getText(), password.getText());

				if (login == true) {
	
					doConnections();
					JOptionPane.showMessageDialog(null, "Connections done!", "Connections", 1);
					loginPanel.hide();
					center();
					startPanel();
				}
			}
		});

		JButton register = new JButton("Register");
		register.setBounds(201, 217, 95, 40);

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				loginPanel.hide();

				frame.setSize(375, 545);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

				registerPanel();
			}
		});
		
		JLabel facebookAccessTokenLabel = new JLabel("Facebook Access Token: ");
		facebookAccessTokenLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		facebookAccessTokenLabel.setBounds(117, 135, 145, 24);

		facebookAccessToken = new JPasswordField();
		facebookAccessToken.setBounds(66, 170, 230, 23);
		
		loginPanel.add(usernameLabel);
		loginPanel.add(username);
		loginPanel.add(passwordLabel);
		loginPanel.add(password);
		loginPanel.add(login);
		loginPanel.add(register);
		loginPanel.add(facebookAccessToken);
		loginPanel.add(facebookAccessTokenLabel);
	}

	/**
	 * Register panel, in this panel we must choose an email, username and password
	 * for our app. We must choose a password for the email, facebook and twitter,
	 * facebook and twitter must have the same email as the one we are registering.
	 */
	public void registerPanel() {

		JPanel registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel);

		registerPanel.setLayout(null);

		JLabel bomDiaAcademiaAccount = new JLabel("Bom dia academia account:");
		bomDiaAcademiaAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
		bomDiaAcademiaAccount.setBounds(31, 26, 185, 21);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		emailLabel.setBounds(31, 196, 131, 21);

		JTextField email = new JTextField();
		email.setBounds(156, 54, 167, 22);

		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(31, 89, 75, 21);

		JTextField username = new JTextField();
		username.setBounds(156, 87, 167, 22);

		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(31, 122, 75, 21);

		JPasswordField password = new JPasswordField();
		password.setBounds(156, 122, 167, 22);

		JLabel confirmPasswordLabel = new JLabel("Confirm password: ");
		confirmPasswordLabel.setBounds(31, 154, 115, 21);

		JPasswordField confirmPassword = new JPasswordField();
		confirmPassword.setBounds(156, 154, 167, 22);

		JLabel emailAccount = new JLabel("Email: ");
		emailAccount.setBounds(31, 57, 75, 21);

		JLabel emailPasswordLabel = new JLabel("Password: ");
		emailPasswordLabel.setBounds(31, 229, 75, 21);

		JPasswordField emailPassword = new JPasswordField();
		emailPassword.setBounds(156, 228, 167, 22);

		JLabel facebookAccountLabel = new JLabel("Twitter account:");
		facebookAccountLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		facebookAccountLabel.setBounds(31, 271, 131, 21);

		JLabel consumerKeyLabel = new JLabel("Consumer Key:");
		consumerKeyLabel.setBounds(31, 304, 104, 21);

		JPasswordField consumerKey = new JPasswordField();
		consumerKey.setBounds(156, 303, 167, 22);

		JLabel consumerSecretLabel = new JLabel("Consumer Secret:");
		consumerSecretLabel.setBounds(31, 336, 104, 21);

		JPasswordField consumerSecret = new JPasswordField();
		consumerSecret.setBounds(156, 336, 167, 22);

		JLabel accessTokenLabel = new JLabel("Access Token: ");
		accessTokenLabel.setBounds(31, 368, 115, 21);
		
		JPasswordField acessToken = new JPasswordField();
		acessToken.setBounds(156, 369, 167, 22);
		
		JLabel acessTokenSecretLabel = new JLabel("Access Token Secret: ");
		acessTokenSecretLabel.setBounds(31, 400, 131, 21);
		
		JPasswordField acessTokenSecret = new JPasswordField();
		acessTokenSecret.setBounds(156, 400, 167, 22);
		
		JButton register = new JButton("Register");
		register.setBounds(67, 444, 95, 40);

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean confirmData = Register.confirmData(email.getText(), username.getText(), password.getText(),
						confirmPassword.getText(), emailPassword.getText(), consumerKey.getText(),
						consumerSecret.getText(), acessToken.getText(), acessTokenSecret.getText());

				if (confirmData == true) {

					registerPanel.hide();
					centerLogin();
					loginPanel();
				}
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(195, 444, 95, 40);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				registerPanel.hide();
				centerLogin();
				loginPanel();
			}
		});

		registerPanel.add(bomDiaAcademiaAccount);
		registerPanel.add(emailLabel);
		registerPanel.add(email);
		registerPanel.add(usernameLabel);
		registerPanel.add(username);
		registerPanel.add(passwordLabel);
		registerPanel.add(password);
		registerPanel.add(confirmPasswordLabel);
		registerPanel.add(confirmPassword);
		registerPanel.add(emailAccount);
		registerPanel.add(emailPasswordLabel);
		registerPanel.add(emailPassword);
		registerPanel.add(facebookAccountLabel);
		registerPanel.add(consumerKeyLabel);
		registerPanel.add(consumerKey);
		registerPanel.add(consumerSecretLabel);
		registerPanel.add(consumerSecret);
		registerPanel.add(accessTokenLabel);
		registerPanel.add(acessToken);
		registerPanel.add(acessTokenSecretLabel);
		registerPanel.add(acessTokenSecret);
		registerPanel.add(register);
		registerPanel.add(btnCancel);
	}

	/**
	 * 
	 * App menu bar, this menu has the option to change the panels.
	 * 
	 * @param atualPanel panel to hide and insert a new one
	 * @return JMenuBar returns a JMenuBar to insert in all panels
	 */
	public JMenuBar getMenuBar(JPanel atualPanel) {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 625, 21);

		JMenu menuBDA = new JMenu("Bda");

		JMenuItem menuInitial = new JMenuItem("Menu");
		JMenuItem menuItemInfo = new JMenuItem("Info");
		JMenuItem menuItemLogout = new JMenuItem("Logout");

		menuBDA.add(menuInitial);
		menuBDA.add(menuItemInfo);
		menuBDA.add(menuItemLogout);

		menuInitial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualPanel.hide();
				startPanel();
			}
		});

		menuItemInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualPanel.hide();
				infoPanel();
			}
		});

		menuItemLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int question = JOptionPane.showConfirmDialog(null, "Logout?", "Question", JOptionPane.YES_NO_OPTION);

				if (question == 0) {
					atualPanel.hide();
					centerLogin();
					loginPanel();
				}
			}
		});

		JMenu menuActions = new JMenu("Actions");

		JMenuItem menuItemSendEmail = new JMenuItem("Send email");
		//JMenuItem menuItemFacebookPost = new JMenuItem("Facebook post");
		JMenuItem menuItemTwitterTweet = new JMenuItem("Twitter tweet");

		menuItemSendEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualPanel.hide();
				emailPanel("", "", "");
			}
		});

//		menuItemFacebookPost.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				atualPanel.hide();
//				facebookPanel("");
//			}
//		});

		menuItemTwitterTweet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualPanel.hide();
				twitterPanel("");
			}
		});

		if(email.getEmailConnect() == true)
			menuActions.add(menuItemSendEmail);
//		if(facebook.getFacebookConnect() == true)
//			menuActions.add(menuItemFacebookPost);
		if(twitter.getTwitterConnect() == true)
			menuActions.add(menuItemTwitterTweet);

		JMenu menuXml = new JMenu("Xml");

		JMenuItem menuItemConfigXml = new JMenuItem("config.xml");

		menuItemConfigXml.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualPanel.hide();
				xmlPanel();
				Xml.checkPassword(atualPanel);
			}
		});

		menuXml.add(menuItemConfigXml);

		menuBar.add(menuBDA);
		if(email.getEmailConnect() == true || twitter.getTwitterConnect() == true)
			menuBar.add(menuActions);
		menuBar.add(menuXml);

		return menuBar;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void doConnections() {
		
		email = new Email();
		facebook = new Facebook(facebookAccessToken.getText());
		twitter = new Twitter(Login.getConsumerKey(), Login.getConsumerSecret(), Login.getAcessToken(), Login.getAcessTokenSecret());
		
		email.receiveEmail(Login.getUserEmail(), Login.getUserPassword(), values);
		facebook.viewPosts(values);
		twitter.viewTweets(values);
		
		Json json = new Json(username.getText(), values, email.getEmailConnect(), facebook.getFacebookConnect(), 
				twitter.getTwitterConnect());
		
		Json.createFile();
		Json.addToArray();
		Json.write();
		
		valuesOperations.sort(values);
		
		for (int i = 0; i < values.size(); i++) {
			valuesToShow.add(values.get(i));
		}	
	}
	
	/**
	 * First panel after login, in this panel we have some information of the user
	 * and some shorcuts
	 */
	public void startPanel() {

		JPanel initialPanel = new JPanel();
		frame.getContentPane().add(initialPanel);
		
		initialPanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		welcomeLabel.setBounds(242, 68, 143, 55);

		JTextArea userInfo = new JTextArea();
		userInfo.setEditable(false);
		userInfo.setBackground(new Color(238, 238, 238));
		userInfo.setBounds(159, 141, 290, 163);

		userInfo.setDisabledTextColor(Color.BLACK);
		userInfo.setText("Username: " + username.getText() + "\n\n");
		userInfo.append("Email: " + Login.getUserEmail() + "\n\n");
		userInfo.append("Email connect: " + email.getEmailConnect() + "\n\n");
		userInfo.append("Facebook connect: " + facebook.getFacebookConnect() + "\n\n");
		userInfo.append("Twitter connect: " + twitter.getTwitterConnect());
		
		JLabel shortcutsLabel = new JLabel("Shortcuts: ");
		shortcutsLabel.setBounds(275, 329, 89, 23);

		JButton btnInfo = new JButton("Info");
		btnInfo.setBounds(159, 375, 95, 40);

		btnInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initialPanel.hide();
				infoPanel();
			}
		});

		JButton btnConfigXml = new JButton("config.xml");
		btnConfigXml.setBounds(354, 375, 95, 40);

		btnConfigXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initialPanel.hide();
				xmlPanel();
				Xml.checkPassword(initialPanel);
			}
		});

		initialPanel.add(getMenuBar(initialPanel));
		initialPanel.add(welcomeLabel);
		initialPanel.add(userInfo);
		initialPanel.add(shortcutsLabel);
		initialPanel.add(btnInfo);
		initialPanel.add(btnConfigXml);
	}

	/**
	 * Panel that has the table with emails, posts, and tweets. This panel has the
	 * option to filter the table information.
	 */
	public void infoPanel() {

		JPanel infoPanel = new JPanel();
		frame.getContentPane().add(infoPanel);

		infoPanel.setLayout(null);

		JLabel filterLabel = new JLabel("Filter: ");
		filterLabel.setBounds(10, 35, 46, 14);

		filterComboBox.setBounds(66, 32, 95, 22);

		JLabel searchLabel = new JLabel("Search by:");
		searchLabel.setBounds(171, 35, 72, 14);

		searchComboBox.setBounds(253, 32, 95, 20);

		JTextField searchText = new JTextField();
		searchText.setBounds(368, 32, 162, 20);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(540, 31, 53, 22);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 65, 588, 321);

		String[] columnNames = { "Data", "Source", "Sender", "Subject", "Content", "View" };

		TableModel tableModel = new DefaultTableModel(valuesToShow.toArray(new String[][] {}), columnNames);
		JTable table = new JTable(tableModel);
		table.setEnabled(false);
		//table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
		ButtonEditor buttonEditor = new ButtonEditor(new JTextField());
		table.getColumnModel().getColumn(5).setCellEditor(buttonEditor);

		filterComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				valuesToShow.clear();
				valuesOperations.filter(filterComboBox, searchComboBox, values, valuesToShow);
				infoPanel.hide();
				infoPanel();
			}
		});
		
		searchComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				valuesToShow.clear();
				valuesOperations.filter(filterComboBox, searchComboBox, values, valuesToShow);
				infoPanel.hide();
				infoPanel();
			}
		});

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col == 5) {

					infoPanel.hide();

					if (table.getValueAt(row, 1).toString().equals("Email"))
						emailPanel(table.getValueAt(row, 2).toString(), table.getValueAt(row, 3).toString(),
								table.getValueAt(row, 4).toString());
					if (table.getValueAt(row, 1).toString().equals("Facebook"))
						facebookPanel(table.getValueAt(row, 4).toString());
					if (table.getValueAt(row, 1).toString().equals("Twitter"))
						twitterPanel(table.getValueAt(row, 4).toString());
				}
			}
		});

		scrollPane.setViewportView(table);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(258, 405, 95, 40);
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				values.clear();
				valuesToShow.clear();
				doConnections();
				infoPanel.hide();
				infoPanel();
			}
		});
		
		infoPanel.add(getMenuBar(infoPanel));
		infoPanel.add(filterLabel);
		infoPanel.add(filterComboBox);
		infoPanel.add(searchLabel);
		infoPanel.add(searchComboBox);
		infoPanel.add(searchText);
		infoPanel.add(btnOk);
		infoPanel.add(scrollPane);
		if(email.getEmailConnect() == true || facebook.getFacebookConnect() == true || twitter.getTwitterConnect() == true)
			infoPanel.add(btnRefresh);
	}

	/**
	 * 
	 * Panel to view emails we choose in infoPanel. This panel has the option to
	 * send emails.
	 * 
	 * @param from    String with the sender's email of the email selected if this
	 *                string is null this attribute changes to "send to" to insert
	 *                the email we want to send.
	 * @param subject String with the email's subject we selected or we want to send
	 * @param content String with the email's content we selected or we want to
	 *                send.
	 */
	public void emailPanel(String from, String subject, String content) {

		JPanel emailPanel = new JPanel();
		frame.getContentPane().add(emailPanel);

		emailPanel.setLayout(null);

		JLabel fromOrSendLabel = null;
		if (!from.equals("")) {
			fromOrSendLabel = new JLabel("From:");
			fromOrSendLabel.setBounds(30, 58, 75, 21);
		} else {
			fromOrSendLabel = new JLabel("Send to:");
			fromOrSendLabel.setBounds(30, 58, 75, 21);
		}

		JTextField sendText = new JTextField(from);
		sendText.setBounds(83, 58, 489, 20);

		JLabel subjectLabel = new JLabel("Subject: ");
		subjectLabel.setBounds(30, 99, 75, 21);

		JTextField subjectText = new JTextField(subject);
		subjectText.setBounds(83, 99, 489, 20);

		JLabel emailLabel = new JLabel("Email content:");
		emailLabel.setBounds(30, 141, 103, 21);

		JTextArea emailText = new JTextArea(content);
		JScrollPane scrollPane = new JScrollPane(emailText);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		emailText.setLineWrap(true);
		scrollPane.setBounds(30, 173, 542, 176);

		if(!from.equals("")) {
			sendText.setEditable(false);
			subjectText.setEditable(false);
			emailText.setEditable(false);
		}
		
		JButton respondOrSend = null;

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(475, 386, 95, 40);
		
		if (!from.equals("")) {
			respondOrSend = new JButton("Respond");
			respondOrSend.setBounds(255, 386, 95, 40);

			respondOrSend.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					emailPanel.hide();
					emailPanel("", "", "");
				}
			});
			
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					emailPanel.hide();
					infoPanel();
				}
			});
		} else {
			respondOrSend = new JButton("Send");
			respondOrSend.setBounds(255, 386, 95, 40);

			respondOrSend.addActionListener(new ActionListener() {				
				
				Object[] options = { "Start", "New email" };

				public void actionPerformed(ActionEvent e) {
					
					email.sendEmail(sendText.getText(), subjectText.getText(), emailText.getText(), Login.getUserEmail(), Login.getUserPassword());
					
					if(email.getSent() == true) {
						int option = JOptionPane.showOptionDialog(null, "Email successfully sent", "Email",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
								options[1]);
						
						if (option == 0) {
							emailPanel.hide();
							startPanel();
						}
						else {
							emailPanel.hide();
							emailPanel("", "", "");
						}
					}
					else {
						emailPanel.hide();
						emailPanel("", "", "");
					}
				}
			});
			
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					emailPanel.hide();
					startPanel();
				}
			});
		}

		emailPanel.add(getMenuBar(emailPanel));
		emailPanel.add(fromOrSendLabel);
		emailPanel.add(sendText);
		emailPanel.add(subjectLabel);
		emailPanel.add(subjectText);
		emailPanel.add(emailLabel);
		emailPanel.add(scrollPane);
		if(email.getEmailConnect() == true)
			emailPanel.add(respondOrSend);
		emailPanel.add(btnBack);
	}

	/**
	 * 
	 * Panel to view a post we choose in infoPanel and comment if we want to
	 * 
	 * @param content String with post content
	 */
	public void facebookPanel(String content) {

		JPanel facebookPanel = new JPanel();
		frame.getContentPane().add(facebookPanel);

		facebookPanel.setLayout(null);

		JTextArea post = new JTextArea(content);
		JScrollPane scrollPanePost = new JScrollPane(post);
		scrollPanePost.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		post.setLineWrap(true);
		post.setEditable(false);
		post.setBackground(new Color(238, 238, 238));
		scrollPanePost.setBounds(89, 66, 429, 102);

		JTextArea respondTextArea = new JTextArea();
		JScrollPane scrollPaneRespond = new JScrollPane(respondTextArea);
		scrollPaneRespond.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		respondTextArea.setLineWrap(true);
		scrollPaneRespond.setBounds(89, 230, 429, 102);
		scrollPaneRespond.hide();

		JButton btnRespond = new JButton("Comment");
		btnRespond.setBounds(256, 179, 95, 40);

		JButton btnPost = new JButton("Post");
		btnPost.setBounds(256, 343, 95, 40);
		btnPost.hide();

		btnRespond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollPaneRespond.show();
				btnPost.show();
			}
		});

		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				facebook.post(respondTextArea.getText());
				
				JOptionPane.showMessageDialog(null, "Post successfully sent", "Facebook post", 1);
				facebookPanel.hide();
				infoPanel();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(256, 394, 95, 40);

		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				facebookPanel.hide();
				infoPanel();
			}
		});
		
		facebookPanel.add(getMenuBar(facebookPanel));
		facebookPanel.add(scrollPanePost);
		if(facebook.getFacebookConnect() == true)
			facebookPanel.add(btnRespond);
		facebookPanel.add(scrollPaneRespond);
		facebookPanel.add(btnPost);
		facebookPanel.add(btnBack);
	}

	/**
	 * 
	 * Panel to view a tweet we choose in infoPanel and retweet if we want to
	 * 
	 * @param content String with post content
	 */
	public void twitterPanel(String content) {

		JPanel twitterPanel = new JPanel();
		frame.getContentPane().add(twitterPanel);

		twitterPanel.setLayout(null);

		JTextArea tweet = new JTextArea(content);
		JScrollPane scrollPaneTweet = new JScrollPane(tweet);
		scrollPaneTweet.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tweet.setLineWrap(true);
		tweet.setEditable(false);
		tweet.setBackground(new Color(238, 238, 238));
		scrollPaneTweet.setBounds(89, 66, 429, 102);

		JTextArea respondTextArea = new JTextArea();
		JScrollPane scrollPaneRespond = new JScrollPane(respondTextArea);
		scrollPaneRespond.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		respondTextArea.setLineWrap(true);
		scrollPaneRespond.setBounds(89, 230, 429, 102);
		scrollPaneRespond.hide();

		JButton btnRespond = new JButton("Retweet");
		btnRespond.setBounds(256, 179, 95, 40);

		JButton btnTweet = new JButton("Tweet");
		btnTweet.setBounds(256, 343, 95, 40);
		btnTweet.hide();

		btnRespond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollPaneRespond.show();
				btnTweet.show();
			}
		});

		btnTweet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				twitter.retweet(respondTextArea.getText());
				
				JOptionPane.showMessageDialog(null, "Tweet successfully sent", "Facebook post", 1);
				twitterPanel.hide();
				infoPanel();
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(256, 394, 95, 40);

		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				twitterPanel.hide();
				infoPanel();
			}
		});
		
		twitterPanel.add(getMenuBar(twitterPanel));
		twitterPanel.add(scrollPaneTweet);
		if(twitter.getTwitterConnect() == true)
			twitterPanel.add(btnRespond);
		twitterPanel.add(scrollPaneRespond);
		twitterPanel.add(btnTweet);
		twitterPanel.add(btnBack);
	}

	/**
	 * Panel with password, if password is correct we can see the content of
	 * config.xml and we can edit and save the changes of this file.
	 */
	public void xmlPanel() {

		JPanel xmlPanel = new JPanel();
		frame.getContentPane().add(xmlPanel);

		xmlPanel.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(new Color(238, 238, 238));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 31, 426, 424);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(480, 54, 95, 40);
		btnEdit.hide();

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (btnEdit.getText().equals("Edit")) {
					textArea.setEditable(true);
					textArea.setBackground(Color.WHITE);
					btnEdit.setText("Finish edit");
				}

				else {
					textArea.setEditable(false);
					textArea.setBackground(new Color(238, 238, 238));
					btnEdit.setText("Edit");
				}
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(480, 133, 95, 40);
		btnSave.hide();

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Xml.saveFile(textArea);
			}
		});

		JButton btnShowFile = new JButton("Show file");
		btnShowFile.setBounds(480, 54, 95, 40);

		btnShowFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Xml.readFile(textArea);

				btnShowFile.hide();
				btnEdit.show();
				btnSave.show();
			}
		});

		xmlPanel.add(getMenuBar(xmlPanel));
		xmlPanel.add(scrollPane);
		xmlPanel.add(btnShowFile);
		xmlPanel.add(btnEdit);
		xmlPanel.add(btnSave);
	}
	
	private void center() {
		frame.setSize(625, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
	}
	
	private void centerLogin() {
		frame.setSize(375, 320);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
	}
}