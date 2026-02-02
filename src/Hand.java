public class Hand {
    int handStrength;
    String hand;
    String handType;

    public Hand(String hand, int handStrength) {
        this.hand = hand;
        this.handStrength = handStrength;
        this.handType = "Null";
    }

    public void handArray(){
        String[] handArray = hand.split(" ");
    }

}
