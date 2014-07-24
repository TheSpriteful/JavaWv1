package ca2;

import java.awt.*;
import javax.swing.*;

public class Graphpaper2 {

	public static void main(String[] args) {
		
		JFrame test = new JFrame("GraphPaper Exampe - Without Rulers");
		test.getContentPane().add(new GraphPaper());
		test.setSize(350,300);
		test.setVisible(true);
		

	}

}
