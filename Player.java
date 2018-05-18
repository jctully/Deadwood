import java.util.*;
import java.io.*;

class Player
{
   int playerID;
   int fame;
   int money;
   int rank;
   Boolean workStatus;
   int rehearseBonus;
   Room playerLocation;
   Role playerRole;
   Boolean movedThisTurn;

   public Player(int id, Room playerLocation) {
     this.playerID = id;
     this.fame = 0;
     this.money = 0;
     this.rank = 1;
     this.workStatus = false;
     this.rehearseBonus = 0;
     this.playerLocation = playerLocation;
     this.playerRole = null;
     this.movedThisTurn = false;
   }

   public int getPlayerID(){
     return this.playerID;
   }

   public Room getPlayerLoc(){
     return this.playerLocation;
   }

   public int getFame(){
     return this.fame;
   }

   public void setFame(int f){
     this.fame = f;
   }

   public int getMoney(){
     return this.money;
   }

   public void setMoney(int m){
     this.money = m;
   }

   public int getRank(){
     return this.rank;
   }

   public void setRank(int r){
     this.rank = r;
   }

   public Boolean getWorkStatus(){
     return this.workStatus;
   }

   public void setWorkStatus(Boolean w){
     this.workStatus = w;
   }

   public void rehearse(){
     this.rehearseBonus+=1;
   }

   public int getRehearseBonus() {
     return this.rehearseBonus;
   }

   public void move(Room loc){
     this.playerLocation = loc;
     this.movedThisTurn = true;
   }

   private void act(){

   }

   public Role getRole() {
     return this.playerRole;
   }

   public void takeRole(Role role){
     this.playerRole = role;
     this.workStatus = true;
     role.makeOccupied();
     System.out.println("Role " + role.getTitle() + " taken.\n");
   }

   public Boolean checkRole(Role role) {
     Boolean ret = false;

     if(role.isOccupied())
       System.out.println("Role is occupied. Please try another.");

     else if (role.getRank() > this.rank)
       System.out.println("Rank not high enough for this role. Please try another.");

     else
       ret = true;

     return ret;
   }

   public Boolean hasMovedThisTurn() {
     return this.movedThisTurn;
   }

   public void resetMovedThisTurn() {
     this.movedThisTurn = false;
   }


}
