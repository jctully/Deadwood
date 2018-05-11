
public class Card {

  String title;
  String sceneNum;
  Role[] onCardRoles;
  int budget;

  //card constructor
  public Card(String title, String sceneNum, Role[] roles, int budget) {
    this.title = title;
    this.sceneNum = sceneNum;
    this.roles = roles;
    this.budget = budget;
  }

  public Boolean hasOnCard() {
    Boolean onCard = false;
    for (Role x : this.onCardRoles) {
      if (x.occupied()) {
        onCard = true;
      }
    }
    return onCard;
  }

  public String getTitle() {
    return null;
  }

  public String getSceneNum() {
    return null;

  }

  public int getBudget() {
    return 0;

  }

  public Role[] getRoles() {
    return null;

  }


}
