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

   public Player(int id, Room playerLocation) {
     this.playerID = id;
     this.fame = 0;
     this.money = 0;
     this.rank = 1;
     this.workStatus = false;
     this.rehearseBonus = 0;
     this.playerLocation = playerLocation;
     this.playerRole = null;
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

   public Boolean getWorkSatus(){
     return this.workStatus;
   }

   public void setWorkStatus(Boolean w){
     this.workStatus = w;
   }


   private void rehearse(){
     this.rehearseBonus+=1;
   }

   public void move(Room loc){
     this.playerLocation = loc;
   }

   private void act(){

   }

   public void takeRole(Role role){
     this.playerRole = role;
   }


}
