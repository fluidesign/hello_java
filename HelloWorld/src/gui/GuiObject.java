package gui;
import javax.swing.UIManager;
import javax.swing.UIManager.*;
import helpers.MessageBox;

public abstract class GuiObject {
// abstract classed live to be extended 
		
	public abstract void draw (boolean useProperties);
	
	public abstract boolean visible (boolean setStatus);
	
	public static void main(String[] args) {
		
		try{
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if ("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception e){
			MessageBox.error(e.getMessage(), MessageBox.whoCalledMe());
		}
		
		// build the login window
		Login login = new Login();
		
		login.draw(false);
	}
}
