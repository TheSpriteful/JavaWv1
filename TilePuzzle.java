package ca4;

import java.awt.*;

import javax.swing.*;

public class TilePuzzle {

	public static void main(String[] args) {

		JFrame Puzzle = new JFrame("Tile Puzzle Game");

		Puzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Puzzle.setSize(1024, 768);
		Puzzle.setVisible(true);
		Puzzle.setLocation(256, 192);

		Puzzle.setLayout(new GridLayout(1, 5));

		JPanel Area1 = new JPanel();
		JPanel Area2 = new JPanel();
		JPanel Area3 = new JPanel();
		JPanel Area4 = new JPanel();
		Puzzle.add(Area2);
		Puzzle.add(Area3);
		Puzzle.add(Area4);

		Area1.setBackground(Color.red);

		JPanel Area1Grid = new JPanel();
		Area1Grid.setLayout(new GridLayout(3, 3));
		Area1Grid.setBackground(Color.BLACK);
		Area1Grid.setVisible(true);
		Area1.add(Area1Grid);

		Puzzle.add(Area1);

		JPanel Area5 = new JPanel();

		Area5.setSize(0, 100);
		Area5.setLayout(new GridLayout(3, 1));

		// --------------------------------Column 1
		// -----------------------------------
		JPanel Area2Col1 = new JPanel();

		Area2Col1.paintComponents(null);

		// --------------------------------Column 2
		// -----------------------------------

		JPanel Area2Col2 = new JPanel();

		// --------------------------------Column 3
		// -----------------------------------
		JPanel Area2Col3 = new JPanel();
		Area2Col3.setLayout(new FlowLayout());

		JButton Tile3Puzzle = new JButton("Start 3x3 Puzzle");
		JButton Tile4Puzzle = new JButton("Start 4x4 Puzzle");
		JButton Randomise = new JButton("Randomise");
		JButton Solve = new JButton("Autosolve");
		JCheckBox MissingPiece = new JCheckBox("Show Missing Piece");

		Tile3Puzzle.setVisible(true);

		Area2Col3.add(Tile3Puzzle);
		Area2Col3.add(Tile4Puzzle);
		Area2Col3.add(Randomise);
		Area2Col3.add(Solve);
		Area2Col3.add(MissingPiece);

		Area5.add(Area2Col1);
		Area5.add(Area2Col2);
		Area5.add(Area2Col3);

		Puzzle.add(Area5);

	}
}
