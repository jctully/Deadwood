import java.util.*;

public class Deadwood {
  int currentDay;
  Player[] players;
  Player currentPlayer;
  int currentPlayerNum;
  Board board = new Board();

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
    if (room.card.hasOnCard()) {//give rewards
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
    this.players = new Player[3];
    this.currentPlayer = null;
    this.currentPlayerNum = 0;
    CardImport cards = new CardImport();
    Card[] allCards = cards.getCards();

    Scanner scn = new Scanner(System.in);
    //while (day != 4) {
    System.out.println("Day " + this.currentDay);

    System.out.println("Player" + this.currentPlayerNum);
    System.out.println("1 - ");





  }


}
