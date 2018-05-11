
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
    this.onCard = onCard;
  }

	public Boolean isOccupied() {
	return this.occupied;
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

}
