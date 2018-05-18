
public class Role {

  String title;
  String line;
  Boolean onCard;
  Boolean occupied;
  int rank;

  public Role(String title, String line, int rank, Boolean onCard) {
    this.title = title;
    this.line = line;
    this.rank = rank;
    this.occupied = false;
    this.onCard = onCard;
  }

	public Boolean isOccupied() {
	   return this.occupied;
  }

  public void makeOccupied() {
    this.occupied = true;
  }

  public Boolean isOnCard() {
    return this.onCard;
  }

  public int getRank() {
    return this.rank;
  }

  public String getTitle() {
    return this.title;
  }

  public String getLine() {
    return this.line;
  }

  public void resetRole() {
    this.occupied = false;
  }

}
