package gui;

public abstract class GuiObject {
// abstract classed live to be extended 
		
	public abstract void draw (int h, int w);
	
	public static void main(String[] args) {
		Login login = new Login();
		login.draw(1, 2);
	}
}
