package PatrickCahill;

/**
 * @author Patrick Cahill (20058521)
 */
public class Patrick_Cahill_20058521 {

	public static void main(String[] args) {
		boolean keepRunning = true;
		while (keepRunning) {
			try {
				keepRunning = run();
			} catch (Exception e) {
				// catch all exceptions
				// unknown error
			}
		}
	}

	private static boolean run() {

		int option; // Creates a variable "option" in terms of an integer.

		new GameManager();

		do {
			option = GameManager.menuMain(); // runs the "option" variable on
			// the "GameManager" Class with
			// the "menuMain" method

			switch (option) { // Runs the switch Method on the variable integer
			// "option"
			case 1:
				try {
					GameManager.menuAddGame();
				} catch (CustomException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Runs the "add a game" menu
				break;
			case 2:
				GameManager.editPrice(); // Runs the "edit price" menu
				break;
			case 3:
				GameManager.removeGame(); // Runs the "remove game" menu
				break;
			case 4:
				GameManager.playGame(); // Runs the "play a game" menu
				break;
			case 5:
				GameManager.listGamesDLC(); // Lists all the games
				break;
			case 6:
				break;
			default: // Defaults to nothing if nothing is chosen
				break;
			}

		} while (option != 6); // While option is NOT 5 then program will not
		// exit.

		return false;
	}

}
