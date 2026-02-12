import java.util.Arrays;

public class HandClass {
    String [] handArray;
    String handOccurences;
    String jackHandOccurences;
    int bettingValue;
    String handType;
    String jackHandType;
    String[] wordOfCard = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    int rank;
    // Deconvert from static methods

    // So we'll refrence each of these occurences across each hand for its value
    int parseThroughHandResults(int i, String[] currentHand){
        int parsingResults = 0; //Counter of how many results of what we're parsing for (CurrentHand)
        String lookingFor = wordOfCard[i]; //This is the item in word order we are looking for
        for (String currentHandValue: currentHand) { // stores each value in Currenthand as currenthandvalue
            if (currentHandValue.equals(lookingFor)){ // if it equals item we're lookign for looking for
                parsingResults++;
            }
        }
        return parsingResults;
    }

    void determineHandType (String determiningHand) {

        //  0 0 ... 1 0 1 0

        String hand; // 5oAK 4oAK FH 3oAK 2p 1p HC
        boolean contains5;
        boolean contains4;
        boolean contains3;
        boolean contains2;
        boolean double2;


        if (determiningHand.contains("5")) {
            contains5 = true;
        } else {
            contains5 = false;
        }

        if (determiningHand.contains("4")) {
            contains4 = true;
        } else {
            contains4 = false;
        }

        if (determiningHand.contains("3")) {
            contains3 = true;
        } else {
            contains3 = false;
        }

        int first2Index = determiningHand.indexOf("2");
        if (first2Index != -1) {
            contains2 = true;
            // Search for another "2" starting from the position after the first one
            double2 = determiningHand.indexOf("2", first2Index + 1) != -1; // Double 2 is if the substring after the point contains another 2
        } else {
            contains2 = false;
            double2 = false;
        }


        // labeling begins here

        if (contains5) {
            hand = "5oAK";
        } else if (contains4) {
            hand = "4oAK";
        } else if (contains3 && contains2) {
            hand = "FH";
        } else if (contains3) {
            hand = "3oAK";
        } else if (double2) {
                hand = "2p";
        } else if (contains2) {
            hand = "1p";
        } else {
            hand = "HC";
        }
        this.handType = hand; // Hand Contains the TYPE of HAND
    }


    public HandClass(int bettingValue, String [] handArray){
        this.bettingValue = bettingValue;
        this.handArray = handArray;
    }

    public String toString( ){
        return bettingValue + " " + Arrays.toString(handArray);
    }

    public void setHandOccurences(String handOccurences){
        this.handOccurences = handOccurences;
        this.jackHandOccurences = handOccurences;
        int jackAmt = Integer.parseInt(jackHandOccurences.substring(9, 10)); // Occurence of specifically the jokers
        System.out.println("Jack Amount: " + jackAmt); // How many of them there are
        while (jackAmt != 0) {
            for (int i = 4; i > 0; i++) { // Starting at 4 and going down
                int nextHighestlocation = jackHandOccurences.indexOf(i); // Location of next highest. make sure it goes through ALL of the 4s before moving onward
                while (nextHighestlocation != -1){ // While the current number is still detected (make sure to define nexthighestlocation somewhewre within the loop)
                    int siphoningIndex = Integer.parseInt(jackHandOccurences.substring(nextHighestlocation, nextHighestlocation+1)); // Siphon index is the index of which we're currently looking at
                    while (jackAmt != 0 && siphoningIndex < 6 ) {
                        siphoningIndex++;
                        jackAmt--;
                        jackHandOccurences.setCharAt(1, "0"); // redefine this to be the item in particular at jackhandoccurence string being updated to siphoningoindex
                    }

                }


            }


        }

    }

    public String getHandType(){
        return handType;
    }

    public void setRank(int rank){
        this.rank = rank;
    }
}
