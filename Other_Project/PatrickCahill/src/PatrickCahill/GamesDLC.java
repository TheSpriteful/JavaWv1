package PatrickCahill;

public class GamesDLC extends Games {

  private String DLC; // Private attribute for "DLC"

  public void setDLC(String dLC) {
    this.DLC = dLC; // Sets this.DLC to dLC which is the input
  }

  public String getDLC() {
    return DLC; // returns the value for "DLC" when this method is called
  }

  public String toString() {
    return "Game ID: " + GetGiD() + ", Name: " + getgName()
        + ", Age Rating: " + getAgeRating() + ", Price of Game new: "
        + getPriceNewG() + ", Price of game second hand: "
        + getPrice2hG() + ", This game comes with " + getHrsContent()
        + " hours of Content" + ", DLC? " + DLC;
  } // Generates a String of text to display the "Game ID, Game Name, Age
  // Rating, Price of the Game new, second hand Price,
  // hours of content and whether the game has Downloadable content (DLC)

  public GamesDLC() {
    super();
    this.DLC = "";// calls the Superclass "Games" and sends the non
    // "GamesDLC" attributes to that class
  }

  public GamesDLC(int Gid, String gName, int ageRating, double priceNewG,
                  double price2hG, double hrsContent, String DLC) {
    super(Gid, gName, ageRating, priceNewG, price2hG, hrsContent);
    this.DLC = DLC;
  }// sends the superclass attributes to the constructor in the superclass so
  // that it can set them there

}
