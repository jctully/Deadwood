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
    Player currentPlayer = null;
    int currentPlayerNum = 0;
    Board board = new Board();
    board.initRooms();
    for (int i =0; i<numPlayers; i++) {
      players[i] = new Player(i, board.findRoom("trailer"));
    }

    Scanner scn = new Scanner(System.in);

    //while (day != 4) {
    System.out.print("Day " + currentDay + ", ");

    System.out.println("Player " + currentPlayerNum);
    System.out.println("Options:");
    System.out.println("1 - ");




  }


}
