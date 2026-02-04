public class MethodStorage {
    // So we'll refrence each of these occurences across each hand for its value
    static int parseThroughHandResults(int i, String[] currentHand, String[] wordOfCard){
        int parsingResults = 0; //Counter of how many results of what we're parsing for (CurrentHand)
        String lookingFor = wordOfCard[i]; //This is the item in word order we are looking for
        for (String currentHandValue: currentHand) { // stores each value in Currenthand as currenthandvalue
            if (currentHandValue.equals(lookingFor)){ // if it equals item we're lookign for looking for
                parsingResults++;
            }
        }
        return parsingResults;
    }

    static String determineHandType (int[] determiningHand) {
        int numof5s = 0;
        int numof4s = 0;
        int numof3s = 0;

    }
}
