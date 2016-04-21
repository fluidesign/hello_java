package gui;

import gui.ProgressBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import helpers.FileHelper;

public class MainMenu extends GuiObject implements ActionListener{
	
	// mainmenu items:
	/*
	1. top menu bar -> put function, decide option later
	2. top panel -> hostname from the server
	3. left panel -> list of options
	4. right panel -> shell output
	5. button panel -> file transfer progress
	*/
	
	// set menu text
	private String mainMenuTitle;;
	
	// set J components 
	private JFrame mainMenuFrame; // top level container
	private ProgressBar progressBar;
	// menu : https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/MenuLookDemoProject/src/components/MenuLookDemo.java
	
	
	@Override
	public void draw(boolean useProperties) {
		if (useProperties){
			if (FileHelper.isFileExists("File Path"))
				System.out.println("load file");
		}
		else{
			mainMenuTitle="Lazy admin tools";
		}
		
	// set panels
		JPanel mainMenuPanelTop = new JPanel(new BorderLayout(10, 10));

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean visible(boolean setStatus) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
