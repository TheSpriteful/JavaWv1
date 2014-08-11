package PatrickCahill;

import javax.swing.JOptionPane;
import java.lang.*;

public class Games implements Comparable<Games> {

	private int GiD;
	private String gName;
	private int ageRating;
	private double priceNewG;
	private double price2hG;
	private double hrsContent;

	/*
	 * The above attributes are given a variable type for each and are also set
	 * to private so only this class can use them
	 */

	public int GetGiD() {
		return GiD;// returns Game ID
	}

	public void setGiD(int GiD) {
		this.GiD = GiD; // sets game ID
	}

	public String getgName() {
		return gName; // Returns the name of the game
	}

	public void setgName(String gName) {
		this.gName = gName; // sets the name of the game to gName
	}

	public int getAgeRating() {
		return ageRating; // Returns the Age Rating of the game.
	}

	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;// sets the game's age rating
	}

	public double getPriceNewG() {
		return priceNewG; // returns the price of the game when it was new
	}

	public void setPriceNewG(double priceNewG) {
		this.priceNewG = priceNewG; // sets the game price when the game is to
		// be bought as new
	}

	public double getPrice2hG() {
		return price2hG; // returns the game price as if it were to be bought
		// second hand
	}

	public void setPrice2hG(double price2hG) {
		this.price2hG = price2hG; // sets the game price of the game when it is
		// second hand/pre-owned
	}

	public double getHrsContent() {
		return hrsContent; // Returns the amount of hours of gameplay based in
		// the game.
	}

	public void setHrsContent(double hrsContent) {
		this.hrsContent = hrsContent; // sets the amount of playable hours in
		// the game's storyline
	}

	public String toString() {
		return "Game ID = " + GiD + ".\nName of Game: " + gName
				+ ", comes with an Age Rating of = " + ageRating
				+ "\nGame New �" + priceNewG
				+ "\nThe game can also be bought in" + " other stores for �"
				+ price2hG
				+ " as second hand(Pre-owned), \nThe game also comes with, "
				+ hrsContent + " hrs of content!";
	} // Generates a string for the Variables in this class, but is overwritten
		// by the toString in the GamesDLC Class

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Games))
			return false;

		Games games = (Games) o;

		if (GiD != games.GiD)
			return false;
		if (ageRating != games.ageRating)
			return false;
		if (Double.compare(games.hrsContent, hrsContent) != 0)
			return false;
		if (Double.compare(games.price2hG, price2hG) != 0)
			return false;
		if (Double.compare(games.priceNewG, priceNewG) != 0)
			return false;
		if (gName != null ? !gName.equals(games.gName) : games.gName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = GiD;
		result = 31 * result + (gName != null ? gName.hashCode() : 0);
		result = 31 * result + ageRating;
		temp = Double.doubleToLongBits(priceNewG);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(price2hG);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(hrsContent);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public void play() {
		JOptionPane.showMessageDialog(null, "You are now playing: "
				+ this.gName);
	}

	public Games() {
		super();
		this.GiD = 0;
		this.gName = "";
		this.ageRating = 0;
		this.priceNewG = 0.00;
		this.price2hG = 0.00;
		this.hrsContent = 0.0;
	} // Constructor for this class, sets the default value for each variable
		// String = ""; Int = 0; and double = 0.00;

	public Games(int i, String string, int j, double d, double e, double f) {
	}// default constructor for Games.
	 public int compareTo(Games o) {
		    if (o == null) return -1;
		    if (this == o) return 0;
		    return String.CASE_INSENSITIVE_ORDER.compare(gName, o.gName);
		  }
}
