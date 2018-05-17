
public class Card {

  String title;
  int sceneNum;
  Role[] onCardRoles;
  int budget;
  Boolean isOnBoard;

  //card constructor
  public Card(String title, int sceneNum, Role[] roles, int budget) {
    this.title=title;
    this.sceneNum=sceneNum;
    this.onCardRoles=roles;
    this.budget=budget;
    this.isInRoom = false;
  }

  public Boolean hasOnCard() {
    Boolean onCard = false;

    for (Role x: this.onCardRoles) {
      if (x.isOccupied()) {
        onCard = true;
      }
    }
    return onCard;
  }

  public String getTitle() {
    return this.title;
  }

  public int getSceneNum() {
    return this.sceneNum;
  }

  public int getBudget() {
    return this.budget;
  }

  public Role[] getRoles() {
    return this.onCardRoles;
  }

  public void putOnBoard() {
    this.isOnBoard = true;
  }

  public Boolean isOnBoard() {
    return this.isOnBoard;
  }


}
