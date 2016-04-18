package sessionPkg;

import helpers.MessageBox;
import helpers.UserPrompt;

import javax.swing.JOptionPane;

import com.jcraft.jsch.*;

public class Connection {
	private String userName;
	private char [] password;
	private String IpAddress;
	
	// one connection per session, therefore it needs to be a final
	public Connection (String user, char [] paswd, String ip){
		userName = user;
		password = paswd;
		IpAddress = ip;	
	}
	
	public final boolean connect (){
		// all user/password/host tests done in the connect function
		
		try{
			JSch jsch = new JSch();
			Session session = jsch.getSession(userName, IpAddress, 22);
			// Any issue with changing toString ? vulnerabilty ?
			session.setPassword(password.toString());
			
			UserInfo userInfo = new UserPrompt() {
				public boolean promptYesNo(String message){
			          Object[] options={ "yes", "no" };
			          int foo=JOptionPane.showOptionDialog(null, 
			                                               message,
			                                               "Warning", 
			                                               JOptionPane.DEFAULT_OPTION, 
			                                               JOptionPane.WARNING_MESSAGE,
			                                               null, options, options[0]);
			          return foo==0;
				}
			};
			
			session.setUserInfo(userInfo);
			session.connect(2000);
		}
	    catch(Exception e){
	        System.out.println(e);
	     }
		
		return false;
	}
}
