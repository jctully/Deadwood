
public class Deadwood {
  int currentDay;
  Player[] players;
  Player currentPlayer;
  int currentPlayerNum;

  public Player getCurrPlayer() {
    return this.currentPlayer;
  }

  public void nextTurn() {
    this.currentPlayerNum = (this.currentPlayerNum+1)%4;
    this.currentPlayer = this.players[this.currentPlayerNum];
  }

  public void endDay() {
    Board.clearBoard();
    for (Player p : this.players) {
      // p.move(trailer);

    }
  }

  public void endGame() {

  }

  public void main() {

  }


}
