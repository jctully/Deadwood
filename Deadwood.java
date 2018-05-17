import java.util.*;

public class Deadwood {
  int currentDay;
  Player[] players;
  Player currentPlayer;
  int currentPlayerNum;
  Board board;

  public Player getCurrPlayer() {
    return this.currentPlayer;
  }

  public void nextTurn() {
    this.currentPlayerNum = (this.currentPlayerNum+1)%4;
    this.currentPlayer = this.players[this.currentPlayerNum];
  }

  public void wrap(Room room) {
    room.wrap();
    board.wrapRoom();
    if (room.getCard().hasOnCard()) {//need to give rewards
      int budget = room.getCard().getBudget();
      RNG dice = new RNG();
      int[] rolls = dice.getDiceRolls(budget);
      for (Player player : this.players) {
        if (player.playerLocation.getTitle().equals(room.getTitle())) {
          //check if on or off card, give bonus accordingly
        }
      }
    }
  }

  public void endDay() {
    //Board.clearBoard();
    for (Player p : this.players) {
      // p.move(trailer);

    }
  }

  public void endGame() {

  }

  public void main(String[] args) {
    this.currentDay = 0;
    int numPlayers = Integer.parseInt(args[0]);
    this.players = new Player[numPlayers];
    this.currentPlayer = null;
    this.currentPlayerNum = 0;
    this.board = new Board();
    board.initRooms();

    Scanner scn = new Scanner(System.in);
    //while (day != 4) {
    System.out.println("Day " + this.currentDay);

    System.out.println("Player" + this.currentPlayerNum);
    System.out.println("1 - ");





  }


}
