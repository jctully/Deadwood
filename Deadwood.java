import java.util.*;

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
    System.out.println("Player " + (currentPlayerNum+1) + "'s turn\n");
    return currentPlayerNum;
  }

  public static void wrap(Room room, Board board, Player[] players) {
    room.wrap();
    board.wrapRoom();
    if (room.getCard().hasOnCard()) {//need to give rewards
      int budget = room.getCard().getBudget();
      RNG dice = new RNG();
      int[] rolls = dice.getDiceRolls(budget);
      for (Player player : players) {
        if (player.playerLocation.getTitle().equals(room.getTitle())) {
          //check if on or off card, give bonus accordingly
        }
      }
    }
  }

  public static void endDay(Player[] players) {
    //Board.clearBoard();
    for (Player p : players) {
      // p.move(trailer);

    }
  }

  public static void endGame() {
    //calc score
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

    Scanner scn = new Scanner(System.in);

    System.out.print("Day " + currentDay + ", ");
    System.out.println("Player " + (currentPlayerNum+1) + "'s turn");

    while (currentDay != 4) {
    Player currentPlayer = players[currentPlayerNum];

    System.out.println("Options:");
    System.out.println("1 - Current Player Info");
    System.out.println("2 - Show all player locations");
    System.out.println("3 - Move");
    System.out.println("4 - Take role");
    System.out.println("5 - Act");
    System.out.println("6 - Rehearse");
    System.out.println("7 - Upgrade");
    System.out.println("8 - End Turn");
    System.out.println("\nInput the number pertaining to the action you would like to perform.");

    int i = scn.nextInt();
    if (i == 1) {//player info
      System.out.println("ID: " + (currentPlayer.getPlayerID()+1));
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
        System.out.println("Player " + player.getPlayerID()+1 + " in " +player.getPlayerLoc().getTitle());
      }
    }else if (i == 3) {//move
      if (!currentPlayer.hasMovedThisTurn()) {
        System.out.println("\nInput the number pertaining to the room you would like to move to.");
        String[] options = currentPlayer.getPlayerLoc().getAdjacentRooms();
        for (int a=1; a<=options.length; a++) {
          System.out.println(a+ " - " + options[a-1]);
        }
        int opt = scn.nextInt();
        currentPlayer.move(board.findRoom(options[opt-1]));
        System.out.println("Moved to " + currentPlayer.getPlayerLoc().getTitle());
        System.out.println("----------");
      }
      else{//already moved
        System.out.println("You have already moved this turn\n");
      }


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
          int opt = scn.nextInt();
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
    }else if (i == 5) {

    }else if (i == 6) {

    }else if (i == 7) {

    }else if (i == 8) {
      currentPlayerNum = nextTurn(players, currentPlayerNum);
    }



  }



  }


}
