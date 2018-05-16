public class Room {
  String title;
  Card card;
  String[] adjacentRooms;
  //Player[] playersInRoom;
  int shots;
	int initialShots;
  Role[] offCardRoles;
  Boolean wrapped;

  public Room(String title, String[] adjacentRooms, int shots, Role[] offCardRoles) {
		this.title = title;
		this.adjacentRooms = adjacentRooms;
		this.shots = shots;
		this.initialShots = shots;
		this.offCardRoles = offCardRoles;
  }
/*
  public void addPlayer(Player player) {
  }*/
  public String[] getAdjacentRooms() {
   return this.adjacentRooms;
  }
  public void setCard(Card card) {
		this.card = card;
  }
  public Boolean isWrapped() {
   return this.wrapped;
  }
  public String getTitle() {
   return this.title;
  }
  public int getShots() {
   return this.shots;
  }
  public Role[] getRoles(){
   return this.offCardRoles;
  }
  public Card getCard() {
   return this.card;
  }
  public void completeShot() {
		this.shots--;
		//if (shots == 0) {
			//Deadwood.wrapScene();
		//}
  }
	public void resetRoom(){
		this.card=null;
		this.shots=this.initialShots;
		this.wrapped=false;
		for(Role x: this.offCardRoles){
				x.resetRole();

		}
	}

}
