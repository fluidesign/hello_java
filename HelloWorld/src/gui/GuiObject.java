package gui;

public abstract class GuiObject {
// abstract classed live to be extended 
		
	public abstract void draw (boolean useProperties);
	
	public static void main(String[] args) {
		
		// build the login window
		Login login = new Login();
		login.draw(true);
	}
}
