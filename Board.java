import java.util.Random;

class Board
{
   int roomsLeft;
   Room[] roomList;

   public Board() {
     BoardImport board = new BoardImport();
     this.roomList = board.getRooms()
     this.roomsLeft = 10;
   }

   public Room findRoom(String name) {
     Room find = null;
     for (Room r : this.roomList) {
       if (r.getName().equals(name))
       find = r;
     }
     return r;
   }

   public void clearBoard(){
     for (Room r : this.roomList)
      r.resetRoom();
   }

   public void initRooms(){
     CardImport card = new CardImport();
     Card[] cards = card.getCards();
     for (Room r : this.roomList) {
       //rng 0..cards.length()
       Card card = cards[rng];
       while (card.isOnBoard()) {
         //rng
         card = cards[rng];
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
