package PatrickCahill;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

imports the Icon, ImageIcon, JOptionPane & JTextField from the Swing class
 */

public class GameManager {

	private interface Converter<C> {
		C convert(String value);
	}

	private static final Converter<Integer> INTEGER_CONVERTER = new Converter<Integer>() {
		@Override
		public Integer convert(String value) {
			return Integer.parseInt(value);
		}
	};
	private static final Converter<Double> DOUBLE_CONVERTER = new Converter<Double>() {
		@Override
		public Double convert(String value) {
			return Double.parseDouble(value);
		}
	};

	private interface SearchFunction<C> {
		boolean matches(GamesDLC gamesDLC, C criteria);

		C convert(String value);
	}

	private static enum SearchType {
		ID(new SearchFunction<Integer>() {
			@Override
			public boolean matches(GamesDLC gamesDLC, Integer criteria) {
				if (gamesDLC != null) {
					return gamesDLC.GetGiD() == criteria;
				}
				return false;
			}

			@Override
			public Integer convert(String value) {
				return INTEGER_CONVERTER.convert(value);
			}
		}), NAME(new SearchFunction<String>() {
			@Override
			public boolean matches(GamesDLC gamesDLC, String criteria) {
				if (gamesDLC != null) {
					if (gamesDLC.getgName() == null && criteria == null) {
						return true; // nulls equal?
					}
					return gamesDLC.getgName().toUpperCase()
							.matches(criteria.toUpperCase());
				}
				return false;
			}

			@Override
			public String convert(String value) {
				return value;
			}
		});

		private SearchFunction function;

		private SearchType(SearchFunction function) {
			this.function = function;
		}

		public boolean matches(GamesDLC gamesDLC, Object value) {
			return function.matches(gamesDLC, value);
		}

		public Object convert(String value) {
			return function.convert(value);
		}

		@Override
		public String toString() {
			return name().substring(0, 1) + name().substring(1).toLowerCase();
		}
	}

	private static <C> GamesDLC searchGames(C criteria, SearchType type) {
		if (criteria == null || type == null) {
			return null; // unless you're wanting to search and find entries
							// with null
		}

		for (GamesDLC gamesDLC : gameList) {
			if (gamesDLC == null) {
				break; // assuming that all games are "packed" in array. first
						// null should indicate end of list
			}

			if (type.matches(gamesDLC, criteria)) {
				return gamesDLC;
			}
		}
		return null;
	}

	private static Icon myIcon = new ImageIcon(
			"..\\Resubmissions\\src\\images\\Icon.jpg"); // Creates a Private
	// variable to store
	// an
	// image in
	// "Icon myIcon" and
	// then directs the
	// variable to the
	// directory of the
	// image and the
	// image
	// file.
	private static Icon anIcon = new ImageIcon(); //
	private static int currentSize = 0; // Creates a private variable
	// "currentSize" of type "Int" and sets
	// it to default of 0
	private static int maxSize = 5; // Creates a private variable "maxSize" of
	// type "Int" and sets it to default of 5

	private static List<GamesDLC> gameList = new ArrayList<GamesDLC>();

	// Creates
	// an
	// array list of
	// type GamesDLC
	// called
	// "gameList"
	// and sets its
	// "maxSize" to
	// 5

	public GameManager() {

		// GamesDLC aGame = new GamesDLC(1, "Batman Arkham Origins", 25, 55.99,
		// 45.99, 16.0, "yes");
		// gameList[currentSize] = aGame;
		// currentSize++;

		/*
		 * try { File Data = new File("..//..//Data.txt");
		 * 
		 * if (!file.exists()) { file.createNewFile(); }
		 * 
		 * FileWriter fw = new FileWriter(file.getAbsoluteFile());
		 * BufferedWriter bw = new BufferedWriter(fw); bw.write(gameList);
		 * bw.close(); }
		 * 
		 * catch (IOException ioe) { JOptionPane.showMessageDialog(null,
		 * "Error Writing to List" + ioe + "\nPlease Try Again"); }
		 */
		// Tried to write Games from the "game list" but couldn't seem to get it
		// to work. Did research, came up with nothing to help fix it
	}

	public static int menuMain() {

		int option = 0; // Creates a variable "option" in terms of an integer
		// and sets default to 0

		String opt1 = new String("1. Add a Game :");
		String opt2 = new String("2. Edit game details : ");
		String opt3 = new String("3. Remove a Game :");
		String opt4 = new String("4. Play a Game :");
		String opt5 = new String("5. List All GamesDLC :");
		String opt6 = new String("6. Exit :");
		String msg = new String("Enter Option:");
		JTextField opt = new JTextField("");

		/*
		 * Creates 6 String variables and sets their message to be the message
		 * in the quotes. Also creates a JTextField so that an option can be
		 * chosen and a menu can be switched over to.
		 */

		Object message[] = new Object[9]; // Creates an Object Array of name
		// "message" and set to default size
		// 9

		message[0] = myIcon;
		message[1] = opt1;
		message[2] = opt2;
		message[3] = opt3;
		message[4] = opt4;
		message[5] = opt5;
		message[6] = opt6;
		message[7] = msg;
		message[8] = opt;

		/*
		 * Sets each message[*] to a different option or message also implements
		 * the icon that was stored too.
		 */

		int response = JOptionPane.showConfirmDialog(null, message,
				"GamesDLC Data Entry", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, anIcon);

		if (response != JOptionPane.CANCEL_OPTION) {
			if (opt.getText().trim().isEmpty()) {
				return option;
			}

			try {
				option = Integer.parseInt(opt.getText());
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Data Input Error" + error
						+ "\nPlease Try Again");
			}
		}
		return option; // Returns the option variable to menuMain to select
		// which menu to bring up next.

	}

	public static void menuAddGame() throws CustomException {
		if (currentSize == maxSize) {
			JOptionPane.showMessageDialog(null,
					"You cannot add any more games.");
			return;
		}
		GamesDLC game = new GamesDLC();
		if (editGame(game)) {
			addGAMEToList(game);
		}
    writeFile();
	}

	public static void removeGame() {
		GamesDLC matchedGame = searchGames();
		if (matchedGame == null) {
			return;
		}

		int response = JOptionPane.showConfirmDialog(null, new Object[] {
				"Are you sure you want to remove this game?", matchedGame },
				"Game Data Entry", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, anIcon);

		if (response == JOptionPane.CANCEL_OPTION) {
			return;
		}

		for (int i = 0; i < gameList.size(); i++) {
			if (matchedGame.equals(gameList.get(i))) {
				gameList.remove(i);
				break;
			}
		}
	}

	private static void addGAMEToList(GamesDLC aGame) {
		if (aGame == null) {
			JOptionPane.showMessageDialog(null, "Game cannot be null");
			return;
		}

		if (gameList.size() == maxSize) {
			JOptionPane.showMessageDialog(null,
					"You cannot add any more games.");
			return;
		}

		GamesDLC matchedGame = searchGames(aGame.GetGiD(), SearchType.ID);
		if (matchedGame == null) {
			// game not found. good to add
			gameList.add(aGame);
			Collections.sort(gameList);
			// Arrays.sort(gameList);
		} else {
			JOptionPane.showMessageDialog(null, "Game already added.");
		}
	}

	public static void playGame() {
		String txtID = JOptionPane
				.showInputDialog("Please Enter the ID of the game you would like to Play");
		int id = Integer.parseInt(txtID);
		boolean found = false;
		int i = 0;

		while (!found && (gameList.get(i) != null)) {
			if (gameList.get(i).GetGiD() == id) {
				gameList.get(i).play();
				found = true;
			}
			i++;
		}

		if (!found)
			JOptionPane
					.showMessageDialog(null,
							"A game with that ID does not match with our database. Sorry =/");
	}

	// -------------------------------------------------------------------------//

	public static void listGamesDLC() {
		JOptionPane.showMessageDialog(null, gameList); // Lists all the games in
		// the GameList at the
		// current time it was
		// run
	}

	// -------------------------------------------------------------------------//
	public static void editPrice() {
		GamesDLC matchedGame = searchGames();

		if (matchedGame == null) {
			return;
		}

		try {
			editGame(matchedGame);
		} catch (CustomException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @param gamesDLC
	 */
	private static boolean editGame(GamesDLC gamesDLC) throws CustomException {
		if (gamesDLC == null) {
			gamesDLC = new GamesDLC();
		}
		final JTextField gid = new JTextField("" + gamesDLC.GetGiD());
		JTextField gName = new JTextField(gamesDLC.getgName());
		JTextField gRating = new JTextField("" + gamesDLC.getAgeRating());
		JTextField price = new JTextField("" + gamesDLC.getPriceNewG());
		JTextField price2 = new JTextField("" + gamesDLC.getPrice2hG());
		JTextField hrsCon = new JTextField("" + gamesDLC.getHrsContent());
		JTextField dlc = new JTextField(gamesDLC.getDLC());

		// approach shown above... doesn't work!
		gid.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				gid.requestFocusInWindow();
			}
		});

		Object message[] = new Object[] { myIcon, "Game ID :", gid,
				"Game Name :", gName, "Game Rating :", gRating,
				"Game Price (1) :", price, "Game Price (2) :", price2,
				"Game Content :", hrsCon, "Does the game have DLC? (yes/no)",
				dlc, };

		int response = JOptionPane.showConfirmDialog(null, message,
				"Game Data Entry", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, anIcon);

		if (response == JOptionPane.CANCEL_OPTION) {
			return false;
		}

		// separate parsing from setting to bean. makes sure
		// that bean is updated partially
		try {
			Integer id = Integer.parseInt(gid.getText());
			String name = gName.getText();
			Integer ageRating = Integer.parseInt(gRating.getText());

			Double vprice1 = Double.parseDouble(price.getText());
			Double vprice2 = Double.parseDouble(price2.getText());

			Double hrsContent = Double.parseDouble(hrsCon.getText());

			String vdlc = dlc.getText();

			gamesDLC.setGiD(id);
			gamesDLC.setgName(name);
			gamesDLC.setAgeRating(ageRating);
			gamesDLC.setPriceNewG(vprice1);
			gamesDLC.setPrice2hG(vprice2);
			gamesDLC.setHrsContent(hrsContent);
			gamesDLC.setDLC(vdlc);

		} catch (Exception Error) {
			JOptionPane.showMessageDialog(null, "Data Input Error" + Error
					+ "\nPlease Try Again");

			// call edit again.
			editGame(gamesDLC);
		}

		return true;
	}

	private static GamesDLC searchGames() {
		JComboBox<SearchType> searchTypeCombo = new JComboBox<SearchType>(
				SearchType.values());
		JTextField searchCriteria = new JTextField("");

		/*
		 * Creates 7 String variables and sets their message to be the message
		 * in the quotes. Also creates JTextField's so that an option can be
		 * chosen and a menu can be switched over to.
		 */

		Object message[] = new Object[] { myIcon, "Search Type: ",
				searchTypeCombo, "Search Criteria: ", searchCriteria, };

		/*
		 * Sets each message[*] to a different option or message also implements
		 * the icon that was stored too.
		 */

		int response = JOptionPane.showConfirmDialog(null, message,
				"Game Data Entry", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, anIcon);

		if (response == JOptionPane.CANCEL_OPTION) {
			return null;
		}

		SearchType selectedSearchType = searchTypeCombo
				.getItemAt(searchTypeCombo.getSelectedIndex());
		GamesDLC matchedGame = null;
		try {
			matchedGame = searchGames(
					selectedSearchType.convert(searchCriteria.getText()),
					selectedSearchType);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Invalid data format.  Try again");
			return searchGames();
		}

		if (matchedGame == null) {
			JOptionPane.showMessageDialog(null, "Game not found");
			return null;
		}

		return matchedGame;
	}

  private static void writeFile() {
    String workingDirectory = System.getProperty("user.dir");
    File outputFile = new File(workingDirectory + File.separator + "records.log");
    // make sure parent directory exists
    outputFile.getParentFile().mkdirs();

    String content = serializeGames();

    try {
      FileWriter writer = new FileWriter(outputFile);
      BufferedWriter bw = new BufferedWriter(writer);
      bw.write(content);
      bw.close();
    } catch (IOException e) {
      System.err.println("Unable to write file");
      e.printStackTrace(System.err);
    }
  }

  private static String serializeGames() {
    StringBuilder sb = new StringBuilder();
    if (gameList != null) {
      for (GamesDLC gamesDLC : gameList) {
        sb.append(gamesDLC).append("\n");
      }
    }
    return sb.toString();
  }
}
