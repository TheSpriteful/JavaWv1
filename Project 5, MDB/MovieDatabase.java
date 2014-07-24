package ca5;

import java.awt.*;

import javax.swing.*;

public class MovieDatabase {

	public static void main(String[] args) {

		JFrame MDB = new JFrame("Movie Database");

		MDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MDB.setSize(700, 550);
		MDB.setVisible(true);
		MDB.setLocation(256, 192);

		JPanel Container = new JPanel();
		Container.setVisible(true);
		Container.setLayout(new GridLayout(3, 1));
		MDB.add(Container);
		// topbars
		JMenuBar TopBar = new JMenuBar();
		TopBar.setVisible(true);
		TopBar.setSize(0, 20);
		Container.add(TopBar);
		JMenuBar TopBar2 = new JMenuBar();
		Container.add(TopBar2);

		// buttons
		JButton Edit = new JButton("Edit");
		Edit.setVisible(true);
		TopBar.add(Edit);

		JButton Slideshow = new JButton("Slideshow");
		Slideshow.setVisible(true);
		TopBar.add(Slideshow);
		// static buttons
		JRadioButton Year1 = new JRadioButton("1970-1979");
		TopBar2.add(Year1);
		JRadioButton Year2 = new JRadioButton("1980-1989");
		TopBar2.add(Year2);
		JRadioButton Year3 = new JRadioButton("1990-1999");
		TopBar2.add(Year3);
		JRadioButton Year4 = new JRadioButton("2000-2009");
		TopBar2.add(Year4);
		JRadioButton Year5 = new JRadioButton("2010-2019");
		TopBar2.add(Year5);
		// Button Group for behavior on radio Buttons.
		ButtonGroup Group1 = new ButtonGroup();
		Group1.add(Year1);
		Group1.add(Year2);
		Group1.add(Year3);
		Group1.add(Year4);
		Group1.add(Year5);
		// Content Area for the information
		JPanel Content = new JPanel();
		Content.setVisible(true);
		Container.add(Content);
		// Divided Content area for movies and information
		JPanel infoSect = new JPanel();
		JPanel movies = new JPanel();
		Content.add(infoSect);
		Content.add(movies);
		// Infosect area
		String Poster = "Poster";
		JTabbedPane Tab1 = new JTabbedPane();
		infoSect.add(Tab1);

	}

}
