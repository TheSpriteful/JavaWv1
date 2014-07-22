import java.awt.*;

import javax.swing.*;

public class GraphPaper {

	public static void main(String[] args) {

		JFrame Grid = new JFrame("GraphPaper Example - Without Rulers");
		Grid.setSize(350, 300);
		Grid.setVisible(true);
		Grid.setLocation(256, 192);


		JPanel Ckrd = new JPanel();

		Ckrd.setLayout(new GridLayout(8, 8));

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				JPanel w = new JPanel();
				JPanel b = new JPanel();
				b.setBackground(Color.ORANGE);
				w.setBackground(Color.RED);
				Ckrd.add(w);
				Ckrd.add(b);
			}
			JPanel w = new JPanel();
			JPanel b = new JPanel();
			b.setBackground(Color.ORANGE);
			w.setBackground(Color.RED);
			Ckrd.add(w);
			Ckrd.add(b);
		}
		
		Grid.add(Ckrd);
	}
}