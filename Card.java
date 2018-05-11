
public class Card {

  String title;
  String sceneNum;
  Role[] onCardRoles;
  int budget;

  //card constructor
  public Card(String title, String sceneNum, Role[] roles, int budget) {
    this.title=title;
    this.sceneNum=sceneNum;
    this.onCardRoles=roles;
    this.budget=budget;
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

  public String getSceneNum() {
    return this.sceneNum;
  }

  public int getBudget() {
    return this.budget;
  }

  public Role[] getRoles() {
    return this.onCardRoles;
  }


}
