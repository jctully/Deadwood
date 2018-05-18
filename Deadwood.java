import java.util.*;

public class Deadwood {
  /* int currentDay;
  Player[] players;
  Player currentPlayer;
  int currentPlayerNum;
  Board board;
  */

  public Player getCurrPlayer(Player[] players, int currentPlayerNum) {
    return players[currentPlayerNum];
  }

  public Player nextTurn(Player[] players, int currentPlayerNum) {
    currentPlayerNum = (currentPlayerNum+1)%players.length;
    return players[currentPlayerNum];
  }

  public void wrap(Room room, Board board, Player[] players) {
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

  public void endDay(Player[] players) {
    //Board.clearBoard();
    for (Player p : players) {
      // p.move(trailer);

    }
  }

  public void endGame() {
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
    Player currentPlayer = players[0];
    Scanner scn = new Scanner(System.in);

    //while (day != 4) {
    System.out.print("Day " + currentDay + ", ");

    System.out.println("Player " + currentPlayerNum+ "'s turn");
    System.out.println("Options:");
    System.out.println("1 - Show all player locations");
    System.out.println("2 - Move");
    System.out.println("3 - Take roll");
    System.out.println("4 - Act");
    System.out.println("5 - Rehearse");
    System.out.println("6 - Upgrade");
    System.out.println("7 - End Turn");
    System.out.println("Input the number pertaining to the action you would like to preform.");

    int i = scn.nextInt();
    if (i == 1) {
      for (Player player : players) {
        System.out.println(currentPlayer.getPlayerID() + " " +currentPlayer.getPlayerLoc().getTitle());
      }
    }else if (i == 2) {
      System.out.println("Input the number pertaining to the room you would like to move to.");
      String[] options = currentPlayer.getPlayerLoc().getAdjacentRooms();
      for (int a=1; a<=options.length; a++) {
        System.out.println(a+ " - " + options[a-1]);
      }

    }else if (i == 3) {
      int playerRank=currentPlayer.getRank();
      Role[] onCard = currentPlayer.getPlayerLoc().getCard().getRoles();
      Role[] offCard = currentPlayer.getPlayerLoc().getRoles();

      System.out.println("Your rank is"+ playerRank);

      int possibleRoles = onCard.length + offCard.length;
      for (int a=1; a-1< possibleRoles; a++) {

        for (Role role : offCard ) {
          System.out.println(a++ +" - "+role.getTitle()+ " " +role.getRank());
        }
        for (Role role : onCard ) {
          System.out.println(a++ +" - "+role.getTitle()+ " " +role.getRank());
        }
      }

      System.out.println("The off card roles available in this room are:" + currentPlayer.getPlayerLoc().getRoles());
      System.out.println("The on card roles available in this room are:" + currentPlayer.getPlayerLoc().getCard().getRoles());
      //System.out.println("The off card roles available in this room are:" + player.getPlayerLoc.getRoles());
    }else if (i == 4) {

    }else if (i == 5) {

    }else if (i == 6) {

    }else if (i == 7) {

    }







  }


}
