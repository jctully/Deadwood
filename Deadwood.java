import java.util.*;
import java.io.*;

public class Deadwood {
  /* int currentDay;
  Player[] players;
  Player currentPlayer;
  int currentPlayerNum;
  Board board;
  */

  public static int nextTurn(Player[] players, int currentPlayerNum) {
    Player current = players[currentPlayerNum];
    current.resetMovedThisTurn();
    currentPlayerNum = (currentPlayerNum+1)%players.length;
    System.out.println("Player " + (currentPlayerNum+1) + "'s turn");
    return currentPlayerNum;
  }

  public static void wrap(Room room, Board board, Player[] players) {
    room.wrap();
    board.wrapRoom();
    if (room.getCard().hasOnCard()) {//need to give rewards
      int numOnCard = 0;
      int numOffCard = 0;
      //count num on card players
      for (Player player : players)
        if (player.getPlayerLoc().getTitle().equals(room.getTitle()) && player.getWorkStatus()) {
          if (player.getRole().isOnCard())
            numOnCard++;
          else
            numOffCard++;
        }

      Player[] onCardPlayers = new Player[numOnCard];
      Player[] offCardPlayers = new Player[numOffCard];

      //get dice rolls
      int budget = room.getCard().getBudget();
      RNG rng = new RNG();
      int[] rolls = rng.getDiceRolls(budget);

      Role[] onCard = room.getCard().getRoles();
      Role[] offCard = room.getRoles();

      //check if on or off card, give bonus accordingly, reset rehearse bonus
      int i = 0;
      while (i < budget)
        for (Role r : onCard) {
          for (Player p : onCardPlayers)
            if (p.getRole().getTitle().equals(r.getTitle()))
              p.giveMoney(rolls[i++]);

          i++;
        }
      //give off card
      for(Player p : offCardPlayers)
        p.giveMoney(p.getRole().getRank());

      for (Player player : players)
        System.out.println("Player " + (player.getPlayerID()+1) + " has $" +player.getMoney());

    } else {
      System.out.println("No one on card. No rewards for wrapping this scene.\n");
    }
  }

  public static int endDay(Board board, Player[] players, int currentDay) {
			if(board.getRoomsLeft() == 1) {
				board.clearBoard();
        board.initRooms();
				for(Player p : players) {
          p.move(board.findRoom("trailer"));
          p.resetMovedThisTurn();
        }
        //increment day
        currentDay++;
        System.out.print("\nDay " + (currentDay+1));
        if (currentDay==3) {
          endGame(players);
        }
      }
      return currentDay;

  }

  public static void endGame(Player[] players) {
    //calc score
    System.out.println("Game is over!!! Score results: ");
    int[] scores = 0;
    int maxInd = 0;
    for (int i = 0; i<players.length; i++) {
      scores[i] = players[i].getMoney() + players[i].getFame() + 5*players[i].getRank();
      if (scores[i] > scores[maxInd])
        maxInd = i;
      System.out.println("Player " + (players[i].getPlayerID()+1) + " score: " + scores[i]);
    }
    System.out.println("Winner is player " + (players[maxInd].getPlayerID()+1) + ". Congratulations!");
    System.exit(1);
  }

  public static void main(String[] args) {
    int currentDay = 0;
    int numPlayers = Integer.parseInt(args[0]);
    Player[] players = new Player[numPlayers];
    int currentPlayerNum = 0;
    Board board = new Board();
    board.initRooms();
    for (int i =0; i<numPlayers; i++) {
      players[i] = new Player(i, board.findRoom("trailer"));
    }



    System.out.print("\nDay " + (currentDay+1));
    System.out.println("\nPlayer " + (currentPlayerNum+1) + "'s turn");

    while (currentDay != 4) {
    Player currentPlayer = players[currentPlayerNum];
    Scanner scn = new Scanner(System.in);
    System.out.println("\nOptions:");
    System.out.println("1 - Current Player Info");
    System.out.println("2 - Show all player locations");
    System.out.println("3 - Move");
    System.out.println("4 - Take role");
    System.out.println("5 - Act");
    System.out.println("6 - Rehearse");
    System.out.println("7 - Upgrade");
    System.out.println("8 - End Turn");
    System.out.println("\nInput the number pertaining to the action you would like to perform.");

    int i = 0;
    try{
      i = scn.nextInt();
    }
    catch(InputMismatchException exception){
      System.out.println("You did not enter a number");

    }

    if (i == 1) {//player info
      System.out.println("\nID: " + (currentPlayer.getPlayerID()+1));
      System.out.println("Rank: " + currentPlayer.getRank());
      System.out.println("Money: " + currentPlayer.getMoney());
      System.out.println("Fame Points: " + currentPlayer.getFame());
      System.out.println("Location: " + currentPlayer.getPlayerLoc().getTitle());
      System.out.print("Working: ");
      if (currentPlayer.getWorkStatus()) {
        System.out.println(currentPlayer.getRole().getTitle() + ", " + currentPlayer.getRehearseBonus() + " rehearse tokens.");
      }
      else {
        System.out.println("Not working.");
      }
      System.out.println();

    }

    if (i == 2) {//display locations
      for (Player player : players) {
        System.out.println("Player " + (player.getPlayerID()+1) + " in " +player.getPlayerLoc().getTitle());
      }
    }else if (i == 3) {//move
      if (!currentPlayer.getWorkStatus()) {


        if (!currentPlayer.hasMovedThisTurn()) {
          System.out.println("\nInput the number pertaining to the room you would like to move to.");
          String[] options = currentPlayer.getPlayerLoc().getAdjacentRooms();
          for (int a=1; a<=options.length; a++) {
            System.out.println(a+ " - " + options[a-1]);
          }

          int opt = 0;
          try{
            opt = scn.nextInt();
          }
          catch(InputMismatchException exception){
            System.out.println("You did not enter a number, returning to menu");
            break;
          }

          currentPlayer.move(board.findRoom(options[opt-1]));
          System.out.println("\nMoved to " + currentPlayer.getPlayerLoc().getTitle());
          System.out.println("----------\n");
        }
        else{//already moved
          System.out.println("You have already moved this turn\n");
        }
      }else{System.out.println("You can not move while you are working.");}

    }else if (i == 4) {//take role
      if (currentPlayer.getPlayerLoc().isWrapped()) {
        System.out.println("Current room is wrapped. Unable to act.");
      }
      else if (currentPlayer.workStatus == true) {
        System.out.println("You are already working a role.");
      }
      else {
        Boolean success = false;
        int playerRank=currentPlayer.getRank();
        Role[] onCard = currentPlayer.getPlayerLoc().getCard().getRoles();
        Role[] offCard = currentPlayer.getPlayerLoc().getRoles();
        int possibleRoles = onCard.length + offCard.length;

        Role[] allRoles = new Role[possibleRoles];
        for (int j=0; j<possibleRoles; j++) {
          for (Role role : offCard )
            allRoles[j++] = role;
          for (Role role : onCard )
            allRoles[j++] = role;
        }

        while(!success) {
          System.out.println("\nYour rank is "+ playerRank);
          System.out.println("0 - return to menu");
          for (int a=0; a< possibleRoles; a++) {
            String onCardStr = null;
            if(allRoles[a].isOnCard())
              onCardStr = " On Card";
            else onCardStr = " Off Card";
            System.out.println((a+1) +" - "+ allRoles[a].getTitle()+ ", Rank = "
              + allRoles[a].getRank() + ", " + onCardStr);
          }

          int opt;
          try{
            opt = scn.nextInt();
          }
          catch(InputMismatchException exception){
            System.out.println("You did not enter a number, returning to menu");
            break;
          }

          if (opt == 0) {
            break;
          }
          if (currentPlayer.checkRole(allRoles[opt-1])) {
            currentPlayer.takeRole(allRoles[opt-1]);
            success = true;
            currentPlayerNum = nextTurn(players, currentPlayerNum);
          }

        }//end while

      }//end else

      //System.out.println("The off card roles available in this room are:" + currentPlayer.getPlayerLoc().getRoles());
      //System.out.println("The on card roles available in this room are:" + currentPlayer.getPlayerLoc().getCard().getRoles());
      //System.out.println("The off card roles available in this room are:" + player.getPlayerLoc.getRoles());
    }else if (i == 5) {//act
      if (currentPlayer.getWorkStatus()) {
        RNG rng = new RNG();
        int roll = rng.getRandomNum(1, 6);
        System.out.println("You rolled a " + roll + ".");

        int shotsLeft = currentPlayer.getPlayerLoc().getShots();

        if ((roll+currentPlayer.getRehearseBonus()) >= currentPlayer.getPlayerLoc().getCard().getBudget()) {//succeed
          if (currentPlayer.getRole().isOnCard()) {//on card
            shotsLeft = currentPlayer.getPlayerLoc().completeShot();
            currentPlayer.giveFame(2);
            System.out.println("Your act attempt succeeded. You get 2 fame.");
            System.out.println("Fame Points: " + currentPlayer.getFame());
          } else {//off card
            shotsLeft = currentPlayer.getPlayerLoc().completeShot();
            currentPlayer.giveMoney(1);
            currentPlayer.giveFame(1);
            System.out.println("Your act attempt succeeded. You get 1 Dollar & 1 fame.");
            System.out.println("Money: " + currentPlayer.getMoney());
            System.out.println("Fame Points: " + currentPlayer.getFame());

          }
        } else {//fail
          if (!currentPlayer.getRole().isOnCard()) {//off card
            currentPlayer.giveMoney(1);
            System.out.println("Your act attempt failed. You get 1 Dollar.");
            System.out.println("Money: " + currentPlayer.getMoney());
          } else {//on card
            System.out.println("Your act attempt failed. You get no rewards.");
          }
        }

        if (shotsLeft == 0) {
          System.out.println("Time to wrap scene");
          wrap(currentPlayer.getPlayerLoc(), board, players);
        }
        currentPlayerNum = nextTurn(players, currentPlayerNum);

      } else {//not working
        System.out.println("You are not currently working. You must be working a role to act.");
      }




    }else if (i == 6) {//rehearse
      if (currentPlayer.getWorkStatus()){
        currentPlayer.rehearse();
        System.out.println("You have rehearsed your role. When you act "+currentPlayer.getRehearseBonus()+" will be added to your score.");
        currentPlayerNum = nextTurn(players, currentPlayerNum);
      }else{
        System.out.println("You must be working on a role to rehearse.");
      }
    }else if (i == 7) {//upgrade
      if(currentPlayer.getPlayerLoc().getTitle() == "office"){

        int playerRank = currentPlayer.getRank();
        int playerCash = currentPlayer.getMoney();
        int playerFame = currentPlayer.getFame();

        System.out.println("Your Money: " + currentPlayer.getMoney());
        System.out.println("Fame Points: " + currentPlayer.getFame());

        System.out.println("\nUpgrade Options:");
        System.out.println("Rank 2 -  4$ or  5 fame");
        System.out.println("Rank 3 - 10$ or 10 fame");
        System.out.println("Rank 4 - 18$ or 15 fame");
        System.out.println("Rank 5 - 28$ or 20 fame");
        System.out.println("Rank 6 - 40$ or 25 fame");

        System.out.println("\nInput the number of the rank you would like to recieve.");

        int opt = 0;
        try{
          opt = scn.nextInt();
        }
        catch(InputMismatchException exception){
          System.out.println("You did not enter a number, returning to menu");
        }

        while (currentPlayer.upgrade(opt) == false) {}
        currentPlayerNum = nextTurn(players, currentPlayerNum);

      }else{
        System.out.println("You must be in the office to be able to upgrade");
      }
    }else if (i == 8) {
      currentPlayerNum = nextTurn(players, currentPlayerNum);
    }



  }



  }


}
