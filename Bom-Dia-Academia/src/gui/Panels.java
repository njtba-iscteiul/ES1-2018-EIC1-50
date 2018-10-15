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

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;

import functionalities.Login;
import functionalities.Register;
import xml.Xml;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class Panels {

	JFrame frame;
	JTextField username;

	/**
	 * Create the panel.
	 * 
	 * @param JFrame frame
	 */
	public Panels(JFrame frame) {
		this.frame = frame;
	}

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
		login.setBounds(66, 151, 95, 40);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean login = Login.login(username.getText(), password.getText());

				if (login == true) {

					loginPanel.hide();

					frame.setSize(625, 500);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

					startPanel();
				}
			}
		});

		JButton register = new JButton("Register");
		register.setBounds(201, 151, 95, 40);

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.hide();

				frame.setSize(375, 525);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

				registerPanel();
			}
		});

		loginPanel.add(usernameLabel);
		loginPanel.add(username);
		loginPanel.add(passwordLabel);
		loginPanel.add(password);
		loginPanel.add(login);
		loginPanel.add(register);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void registerPanel() {

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

		JLabel facebookAccountLabel = new JLabel("Facebook account:");
		facebookAccountLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		facebookAccountLabel.setBounds(31, 271, 131, 21);

		JLabel facebookPasswordLabel = new JLabel("Password: ");
		facebookPasswordLabel.setBounds(31, 304, 75, 21);

		JPasswordField facebookPassword = new JPasswordField();
		facebookPassword.setBounds(156, 303, 167, 22);

		JLabel twitterAccountLabel = new JLabel("Twitter account:");
		twitterAccountLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		twitterAccountLabel.setBounds(31, 348, 131, 21);

		JLabel twitterPasswordLabel = new JLabel("Password: ");
		twitterPasswordLabel.setBounds(31, 380, 75, 21);

		JPasswordField twitterPassword = new JPasswordField();
		twitterPassword.setBounds(156, 380, 167, 22);

		JButton register = new JButton("Register");
		register.setBounds(67, 427, 95, 40);

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean confirmData = Register.confirmData(email.getText(), username.getText(), password.getText(),
						confirmPassword.getText(), emailPassword.getText(), facebookPassword.getText(),
						twitterPassword.getText());

				if (confirmData == true) {

					registerPanel.hide();

					frame.setSize(375, 265);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

					loginPanel();
				}
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(195, 427, 95, 40);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				registerPanel.hide();

				frame.setSize(375, 265);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

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
		registerPanel.add(facebookPasswordLabel);
		registerPanel.add(facebookPassword);
		registerPanel.add(twitterAccountLabel);
		registerPanel.add(twitterPasswordLabel);
		registerPanel.add(twitterPassword);
		registerPanel.add(register);
		registerPanel.add(btnCancel);
	}

	private JMenuBar getMenuBar(JPanel atualPanel) {

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
					frame.setSize(375, 265);
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

					loginPanel();
				}
			}
		});

		JMenu menuActions = new JMenu("Actions");

		JMenuItem menuItemSendEmail = new JMenuItem("Send email");
		JMenuItem menuItemFacebookPost = new JMenuItem("Facebook post");
		JMenuItem menuItemTwitterTweet = new JMenuItem("Twitter tweet");

		menuItemSendEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualPanel.hide();
				emailPanel();
			}
		});

		menuItemFacebookPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualPanel.hide();
				facebookPanel();
			}
		});

		menuItemTwitterTweet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atualPanel.hide();
				twitterPanel();
			}
		});

		menuActions.add(menuItemSendEmail);
		menuActions.add(menuItemFacebookPost);
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
		menuBar.add(menuActions);
		menuBar.add(menuXml);

		return menuBar;
	}

	private void startPanel() {

		JPanel initialPanel = new JPanel();
		frame.getContentPane().add(initialPanel);

		initialPanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		welcomeLabel.setBounds(242, 68, 143, 55);

		JTextArea userInfo = new JTextArea();
		userInfo.setEditable(false);
		userInfo.setBackground(new Color(238, 238, 238));
		userInfo.setBounds(159, 141, 290, 99);

		userInfo.setDisabledTextColor(Color.BLACK);
		userInfo.setText("Username: " + username.getText() + "\n\n");
		userInfo.append("Email: \n\n");
//		userInfo.append("Facebook: \n");
//		userInfo.append("Twitter: \n");
		userInfo.append("Connect: ");

		JLabel shortcutsLabel = new JLabel("Shortcuts: ");
		shortcutsLabel.setBounds(275, 262, 89, 23);

		JButton btnInfo = new JButton("Info");
		btnInfo.setBounds(159, 308, 95, 40);

		btnInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initialPanel.hide();
				infoPanel();
			}
		});

		JButton btnConfigXml = new JButton("config.xml");
		btnConfigXml.setBounds(354, 308, 95, 40);

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

	private void infoPanel() {

		JPanel starMenuPanel = new JPanel();
		frame.getContentPane().add(starMenuPanel);

		starMenuPanel.setLayout(null);

		JLabel filterLabel = new JLabel("Filter: ");
		filterLabel.setBounds(10, 35, 46, 14);

		JComboBox filterComboBox = new JComboBox();
		filterComboBox.setModel(new DefaultComboBoxModel(new String[] { "All", "Email", "Facebook", "Twitter" }));
		filterComboBox.setBounds(66, 32, 95, 22);

		JLabel searchLabel = new JLabel("Search by:");
		searchLabel.setBounds(171, 35, 72, 14);

		JComboBox searchComboBox = new JComboBox();
		searchComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "All time", "Last hour", "Last day", "Last week" }));
		searchComboBox.setBounds(253, 32, 95, 20);

		JTextField searchText = new JTextField();
		searchText.setBounds(368, 32, 162, 20);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(540, 31, 53, 22);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 65, 588, 387);

		String[] columnNames = { "Data", "Source", "Sender", "Subject", "Content", "View" };

		Object[][] data = {};

		JTable table = new JTable(data, columnNames);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		starMenuPanel.add(getMenuBar(starMenuPanel));
		starMenuPanel.add(filterLabel);
		starMenuPanel.add(filterComboBox);
		starMenuPanel.add(searchLabel);
		starMenuPanel.add(searchComboBox);
		starMenuPanel.add(searchText);
		starMenuPanel.add(btnOk);
		starMenuPanel.add(scrollPane);
	}

	private void emailPanel() {

		JPanel emailPanel = new JPanel();
		frame.getContentPane().add(emailPanel);

		emailPanel.setLayout(null);

		JLabel sendLabel = new JLabel("Send to:");
		sendLabel.setBounds(30, 58, 75, 21);

		JTextField sendText = new JTextField();
		sendText.setBounds(83, 58, 489, 20);

		JLabel subjectLabel = new JLabel("Subject: ");
		subjectLabel.setBounds(30, 99, 75, 21);

		JTextField subjectText = new JTextField();
		subjectText.setBounds(83, 99, 489, 20);

		JLabel emailLabel = new JLabel("Email content:");
		emailLabel.setBounds(30, 141, 103, 21);

		JTextArea emailText = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(emailText);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		emailText.setLineWrap(true);
		scrollPane.setBounds(30, 173, 542, 176);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(268, 386, 95, 40);

		btnSend.addActionListener(new ActionListener() {

			Object[] options = { "Info", "New email" };

			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showOptionDialog(null, "Email successfully sent", "Email",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

				if (option == 0) {
					emailPanel.hide();
					infoPanel();
				}
			}
		});

		emailPanel.add(getMenuBar(emailPanel));
		emailPanel.add(sendLabel);
		emailPanel.add(sendText);
		emailPanel.add(subjectLabel);
		emailPanel.add(subjectText);
		emailPanel.add(emailLabel);
		emailPanel.add(scrollPane);
		emailPanel.add(btnSend);
	}

	private void facebookPanel() {

		JPanel facebookPanel = new JPanel();
		frame.getContentPane().add(facebookPanel);

		facebookPanel.setLayout(null);

		JTextArea post = new JTextArea("Facebook");
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

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(256, 343, 95, 40);
		btnOk.hide();

		btnRespond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollPaneRespond.show();
				btnOk.show();
			}
		});

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Tweet successfully sent", "Facebook post", 1);
				facebookPanel.hide();
				infoPanel();
			}
		});

		facebookPanel.add(getMenuBar(facebookPanel));
		facebookPanel.add(scrollPanePost);
		facebookPanel.add(btnRespond);
		facebookPanel.add(scrollPaneRespond);
		facebookPanel.add(btnOk);
	}

	private void twitterPanel() {

		JPanel twitterPanel = new JPanel();
		frame.getContentPane().add(twitterPanel);

		twitterPanel.setLayout(null);

		JTextArea tweet = new JTextArea("Tweet");
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

		JButton btnRespond = new JButton("Tweet");
		btnRespond.setBounds(256, 179, 95, 40);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(256, 343, 95, 40);
		btnOk.hide();

		btnRespond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollPaneRespond.show();
				btnOk.show();
			}
		});

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Post successfully sent", "Facebook post", 1);
				twitterPanel.hide();
				infoPanel();
			}
		});

		twitterPanel.add(getMenuBar(twitterPanel));
		twitterPanel.add(scrollPaneTweet);
		twitterPanel.add(btnRespond);
		twitterPanel.add(scrollPaneRespond);
		twitterPanel.add(btnOk);
	}

	private void xmlPanel() {

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
}