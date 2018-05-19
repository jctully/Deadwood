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

   public void giveFame(int f){
     this.fame += f;
   }

   public int getMoney(){
     return this.money;
   }

   public void giveMoney(int m){
     this.money += m;
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

   public Boolean upgrade(int opt) {
     Boolean ret = false;
     System.out.println("Select how you would like to pay, or 0 to end turn:");
     Scanner scn = new Scanner(System.in);
     int i;
     if (opt == 0) {
       ret = true;
     } else if (opt ==2) {
       System.out.println("1 - $4");
       System.out.println("2 - 5 Fame");
       i = scn.nextInt();
       if (i==1 && getMoney()>=4 && getRank()<2) {
         setRank(2);
         giveMoney(-4);
         System.out.println("Rank up succeeded. You are now rank 2.");
         ret = true;
       } else if (i==2 && getFame()>=5 && getRank()<2) {
         setRank(2);
         giveFame(-5);
         System.out.println("Rank up succeeded. You are now rank 2.");
         ret = true;
       } else {
         System.out.println("Rank up failed.");
       }
    }else if (opt ==3) {
      System.out.println("1 - $10");
      System.out.println("2 - 10 Fame");
      i = scn.nextInt();
      if (i==1 && getMoney()>=10 && getRank()<3) {
        setRank(3);
        giveMoney(-10);
        System.out.println("Rank up succeeded. You are now rank 3.");
        ret = true;
      } else if (i==2 && getFame()>=10 && getRank()<3) {
        setRank(3);
        giveFame(-10);
        System.out.println("Rank up succeeded. You are now rank 3.");
        ret = true;
      } else {
        System.out.println("Rank up failed.");
      }
    }else if (opt ==4) {
      System.out.println("1 - $18");
      System.out.println("2 - 15 Fame");
      i = scn.nextInt();
      if (i==1 && getMoney()>=18 && getRank()<4) {
        setRank(4);
        giveMoney(-18);
        System.out.println("Rank up succeeded. You are now rank 4.");
        ret = true;
      } else if (i==2 && getFame()>=15 && getRank()<4) {
        setRank(4);
        giveFame(-15);
        System.out.println("Rank up succeeded. You are now rank 4.");
        ret = true;
      } else {
        System.out.println("Rank up failed.");
      }
    } else if (opt ==5) {
      System.out.println("1 - $28");
      System.out.println("2 - 20 Fame");
      i = scn.nextInt();
      if (i==1 && getMoney()>=28 && getRank()<5) {
        setRank(5);
        giveMoney(-28);
        System.out.println("Rank up succeeded. You are now rank 5.");
        ret = true;
      } else if (i==2 && getFame()>=20 && getRank()<5) {
        setRank(5);
        giveFame(-20);
        System.out.println("Rank up succeeded. You are now rank 5.");
        ret = true;
      } else {
        System.out.println("Rank up failed.");
      }
    } else if (opt ==6) {
      System.out.println("1 - $40");
      System.out.println("2 - 25 Fame");
      i = scn.nextInt();
      if (i==1 && getMoney()>=40 && getRank()<6) {
        setRank(6);
        giveMoney(-40);
        System.out.println("Rank up succeeded. You are now rank 6.");
        ret = true;
      } else if (i==2 && getFame()>=25 && getRank()<6) {
        setRank(6);
        giveFame(-25);
        System.out.println("Rank up succeeded. You are now rank 6.");
        ret = true;
      } else {
        System.out.println("Rank up failed.");
      }
    } else {
      System.out.println("Enter a number 1 through 6.");
    }
    return ret;
  }//end upgrade


}
