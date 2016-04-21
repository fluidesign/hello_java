package gui;

import helpers.MessageBox;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProgressBar extends GuiObject {
	// classed used to show a progress bar during a connections
	// thread ?
	
	// Set J components
	private JFrame progressBarFrame;
	private JPanel progressBarPanel;
	@Override
	public void draw(boolean useProperties) {
		// no need to use properties at the moment
		
		progressBarFrame = new JFrame("Please wait");
		progressBarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		progressBarPanel = new JPanel(new BorderLayout(30, 30));
		
		progressBarPanel.add(progressBarFrame,BorderLayout.CENTER);
		
		progressBarFrame.setContentPane(progressBarPanel);
		progressBarFrame.pack();
		
		if (((Float.parseFloat(System.getProperty("java.specification.version")))>1.4))
			progressBarFrame.setLocationRelativeTo(null);
	}

	@Override
	public boolean visible(boolean setStatus) {
		try{
			progressBarFrame.setVisible(setStatus);
		}
		catch (Exception e){
			MessageBox.error("Error:" +e.getMessage(), MessageBox.whoCalledMe());
		}
		return false;
	}
	
	

}
