import java.util.Random;

class Board
{
   int roomsLeft;
   Room[] roomList;

   public Board() {
     BoardImport board = new BoardImport();
     this.roomList = board.getRooms();
     this.roomsLeft = 10;
   }

   public Room findRoom(String name) {
     Room find = null;
     for (Room r : this.roomList) {
       if (r.getTitle().equals(name))
       find = r;
     }
     return find;
   }

   public void clearBoard(){
     for (Room r : this.roomList)
      r.resetRoom();
   }

   public void initRooms(){
     this.roomsLeft = 10;
     CardImport cardImport = new CardImport();
     Card[] cards = cardImport.getCards();
     for (Room r : this.roomList) {//randomize card dealt to each room
       RNG rng = new RNG();
       int cardIndex = rng.getRandomNum(0,cards.length-1);
       Card card = cards[cardIndex];
       while (card.isOnBoard()) {//card has been dealt on board already
         cardIndex = rng.getRandomNum(0,cards.length-1);
         card = cards[cardIndex];
       }
       r.setCard(card);
       card.putOnBoard();
     }
   }

   public int getRoomsLeft(){
     return this.roomsLeft;
   }

   public void wrapRoom() {
     this.roomsLeft--;
   }

}
