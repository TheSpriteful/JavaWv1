package ca5;

/*
 * THIS IS A WORK IN PROGRESS.
 * Learning Tabbed Panes
 */

import javax.swing.*;

public class TabbedPanes extends JFrame{

	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	JPanel thirdPanel = new JPanel();
	
	JLabel Label1 = new JLabel("Poster");
	JLabel Label2 = new JLabel("Plot");
	JLabel Label3 = new JLabel("Details");
	
	public TabbedPanes(){
		firstPanel.add(Label1);
		secondPanel.add(Label2);
		thirdPanel.add(Label3);
		
		TabbedPanes.add("First Panel",firstPanel);
	}
	
}
