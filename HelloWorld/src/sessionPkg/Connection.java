package sessionPkg;

import helpers.MessageBox;
import helpers.UserPrompt;

import javax.swing.JOptionPane;

import com.jcraft.jsch.*;

import gui.ProgressBar;

public class Connection {
	private static String userName;
	private static char [] password;
	public static String IpAddress;
	
	private ProgressBar progressBar;
	private static Session session;
	/*
	// one connection per session, therefore it needs to be a final
	public Connection (String user, char [] paswd, String ip){
		userName = user;
		password = paswd;
		IpAddress = ip;	
	}
	*/
	@SuppressWarnings("restriction")
	public static final boolean connect (String user, char [] paswd, String ip){
		// all user/password/host tests done in the connect function
		userName = user;
		password = paswd;
		IpAddress = ip;	
		
		try{
			JSch jsch = new JSch();
			session = jsch.getSession(userName, IpAddress, 22);
			// Any issue with changing toString ? vulnerabilty ?
			session.setPassword(String.valueOf(password));
			UserInfo userInfo = new UserPrompt() {
				public boolean promptYesNo(String message){
			          Object[] options={ "Yes", "No" };
			          int foo=JOptionPane.showOptionDialog(null, 
			                                               message,
			                                               "Warning", 
			                                               JOptionPane.DEFAULT_OPTION, 
			                                               JOptionPane.WARNING_MESSAGE,
			                                               null, options, options[0]);
			          return foo==0;
				}
			};
			
			// start thread
			
			// this part responsible for the actual connection
			// we need to put a thread and status bar while its connecting
			session.setUserInfo(userInfo);
			session.connect(2000);
			
			
			// we need to pass the session
			/*
		    Channel channel=session.openChannel("shell");
		    channel.setInputStream(System.in);
		    channel.setOutputStream(System.out);
		    channel.connect(3*1000);
		    */
			
		    return true;
		    
		}
		
	    catch(Exception e){
	    	
	    	if (e.getMessage().contains("timeout"))
	    		MessageBox.info("Timeout \nMake sure that the IP is correct and you have access to the server","Login");
	    	
	    	// Auth fail
	    	//reject HostKey
	        System.out.println(e);
	     }
		return false;
	}
	public static String sessionExec (){
		System.out.println(session.getHost().toString());
	
			return null;
		
	}
}
