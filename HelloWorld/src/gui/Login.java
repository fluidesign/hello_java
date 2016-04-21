package gui;
import helpers.IPAddressValidator;
import helpers.MessageBox;
import helpers.FileHelper;
import sessionPkg.Connection;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.Properties;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

// Login menu allows to use a predefined properties file to keep username, password [Enc.decr ?] and remote server
public class Login extends GuiObject implements ActionListener{
	private InputStream inputStream;
	
	// set menu text
	private String loginFrameTitle;
	private String buttonConnectText = "Connect";
	private String buttonCloseText = "Close";
	private String userNameHint = "Write your username";  
	private String passwordHint = "Write your password";  
	private String serverIpHint = "Write your server IP";

	// set J components 
	private JPasswordField passwordField;
	private JFrame loginFrame;
	private JLabel userNameLabel;
	private JTextField userNameField;
	private JLabel passwordLabel;
	private JLabel serverIpLabel;
	private JTextField serverIpField;
	
	@Override
	public void draw(boolean useProperties) {
		// if expcetion found use predefied values
		
		if (useProperties){
			if (FileHelper.isFileExists("File Path"))
				System.out.println("load file");
		}
		else{
			loginFrameTitle="Login";
		}
		
		// init
		loginFrame = new JFrame(loginFrameTitle);
		userNameLabel = new JLabel("UserName: ");
        userNameField = new JTextField();
        userNameField.setToolTipText(userNameHint);
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(30);
        passwordField.setToolTipText(passwordHint);
		serverIpLabel = new JLabel("Server IP: ");
		serverIpField = new JTextField();
		serverIpField.setToolTipText(serverIpHint);
        
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html
        JPanel loginPanelInput = new JPanel(new BorderLayout(10, 10));
        
        JPanel loginPanelLabels = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel loginPanelFields = new JPanel(new GridLayout(0, 1, 5, 5));
        
        loginPanelInput.add(loginPanelLabels, BorderLayout.WEST);
        loginPanelInput.add(loginPanelFields, BorderLayout.CENTER);
        
        loginPanelLabels.add(userNameLabel);
        loginPanelFields.add(userNameField);
        loginPanelLabels.add(passwordLabel);
        loginPanelFields.add(passwordField);
        loginPanelLabels.add(serverIpLabel);
        loginPanelFields.add(serverIpField);
        
        JPanel loginPanelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));

        JButton buttonConnect = new JButton(buttonConnectText);
        JButton buttonClose = new JButton(buttonCloseText);
        
        buttonConnect.setActionCommand(buttonConnectText);
        buttonConnect.addActionListener(this);
        buttonClose.setActionCommand(buttonCloseText);
        buttonClose.addActionListener(this);

        loginPanelControl.add(buttonConnect);
        loginPanelControl.add(buttonClose);
        
        JPanel loginPanel = new JPanel(new BorderLayout(10, 10));
        
        loginPanel.setBorder(new TitledBorder("Login credentials"));
        loginPanel.add(loginPanelInput, BorderLayout.CENTER);
        loginPanel.add(loginPanelControl, BorderLayout.SOUTH);
        
		loginFrame.setContentPane(loginPanel);
		loginFrame.pack();
		
		if (((Float.parseFloat(System.getProperty("java.specification.version")))>1.4))
				loginFrame.setLocationRelativeTo(null);
		
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);
	}
	
	public String getPropValues() throws IOException {
		try{
			Properties prop = new Properties();
			String propFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
				
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		}
		catch (Exception e){
			MessageBox.error("Get properties issue" + e, "getPropValues");

		}
		finally {
			inputStream.close();
		}
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		IPAddressValidator iPValid = new IPAddressValidator();
		
		if (cmd.equals(buttonConnectText)){			
			// validatore
			if (userNameField.getText().isEmpty())
				MessageBox.error("User name is missing", "Login");
			
			else if (passwordField.getPassword().length==0)
				MessageBox.error("Password is missing", "Login");
			
			else if(!iPValid.validate(serverIpField.getText().toString()))
				MessageBox.error("Server IP is missing or wrong syntax", "Login");
			else {
				if (Connection.connect(userNameField.getText(), passwordField.getPassword(), serverIpField.getText())){
					
					loginFrame.setVisible(true);
					Connection.sessionExec();
					System.out.println(Connection.IpAddress);
				}
				else
					loginFrame.setVisible(false);
			}
		}
		else if(cmd.equals(buttonCloseText)){
			MessageBox.info("Good bye","Exit");
			System.exit(0);
		}
	}

	@Override
	public boolean visible(boolean setStatus) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
