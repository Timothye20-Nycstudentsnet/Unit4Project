import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileData = "";
        try {
            File f = new File("src/data");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                fileData += line + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


        String[] lines = fileData.split("\n"); // splits line to line
        HandClass[] allHands = new HandClass[lines.length];
        int fivOfAKind = 0; // Stores these for Part 1.
        int fouOfAKind = 0;
        int fullHouse = 0;
        int throfAKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;
        int index = 0;
        for (String line : lines) {
            int handClassIndex = 0; //This is so that we can organize handclassees by integer too

            String[] hand = line.split("\\|", 2); // Split line into Array of 2 items. the Cardnames (String) & The betting power)

            int handsStrength = Integer.parseInt(hand[1].trim()); //Store betting power in this int
            String handNames = hand[0]; //String of all the Cardnames so u can split them into an array. We can store this to parse through later.
            String[] handArray = handNames.split(","); //HandArray contains an array of all the CardNames

            String[] wordOfCard = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
            int[] handvaluesArray = new int[13]; // Handvalues contains the OCCURENCE DATA of each Card (Ordered as in Word of Card)
            String handvaluesString = ""; // Saving as both an array and string because sometimes we'll need item by item


            allHands[index] = new HandClass(handsStrength, handArray); //Creates a HandClass object in the allHands Array at point index
            HandClass currentHandObject = allHands[index]; // We can refer to it as CurrentHandObject

            for (int i = 0; i < wordOfCard.length; i++) {
                handvaluesArray[i] = currentHandObject.parseThroughHandResults(i, handArray); // This assigns occurence data (zero 2's. one 3, zero 4's. two 5... = 0102...)
                handvaluesString += handvaluesArray[i]; //If we can just get handvalues as a string we can then just parse normally using .IndexOf and other string commands instead of complicated array shenangigans
            }

            currentHandObject.setHandOccurences(handvaluesString); // Saves this to the Object
            currentHandObject.determineHandType(handvaluesString); // Determines handtype based on HandValues




            String handType = currentHandObject.getHandType(); // HandType defines itself as the result of determinginghandtype process. Aka its wha stores the type of ahdn it is

            if (handType == "5oAK") {
                fivOfAKind++;
            } else if (handType == "FH") {
                fullHouse++;
            } else if (handType == "4oAK") {
                fouOfAKind++;
            } else if (handType == "3oAK") {
                throfAKind++;
            } else if (handType == "2p") {
                twoPair++;
            } else if (handType == "1p") {
                onePair++;
            } else {
                highCard++;
            }
            index++;

        }
        System.out.println("Number of five of a kind hands: " + fivOfAKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fouOfAKind);
        System.out.println("Number of three of a kind hands: " + throfAKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);

        // System.out.println(Arrays.toString(allHands));

        for (HandClass handClassinArray: allHands){
            String wordOfCardString = "2345678910JackQueenKingAce";
            int preliminaryRank; // The idea is Immediately rank using the variables. All the 50aks tie for first, the FH's for the spot under.. etc
            if (handClassinArray.handType == "5oAK") {
                preliminaryRank = 1;
            } else if (handClassinArray.handType.equals("FH")) {
                preliminaryRank = 1 + fivOfAKind;
            } else if (handClassinArray.handType.equals("4oAK")) {
                preliminaryRank = 1 + fivOfAKind + fullHouse;
            } else if (handClassinArray.handType.equals("3oAK")) {
                preliminaryRank = 1 + fivOfAKind + fullHouse + fouOfAKind;
            } else if (handClassinArray.handType.equals("2p")) {
                preliminaryRank = 1 + fivOfAKind + fullHouse + fouOfAKind + throfAKind;
            } else if (handClassinArray.handType.equals("1p")) {
                preliminaryRank = 1 + fivOfAKind + fullHouse + fouOfAKind + throfAKind + twoPair;
            } else {
                preliminaryRank = 1 + fivOfAKind + fullHouse + fouOfAKind + throfAKind + twoPair + onePair;
            }

            handClassinArray.setRank(preliminaryRank);
            System.out.println("Type: " + handClassinArray.handType + "Rank: " + preliminaryRank);
            // Ok now we need to organize them based within the rank
            // My idea is to take the first object & Parse within wordofCard to compare. [Hand1.Item1.Index > hand2.Item1 Index] and the loser would increase rank by 1
            // For Each object (Go up against every other) if Handtypes are equal and it loses out then rank goes up by 1.

            boolean completedMatchup = false; // turn this true to end the loop
            int handitemindex = 0; // we'll make an index tha goes through the first 4 items for comparisons sake
            int comparisonsRankCounter = 0; // this counts how many times it LOSES a matchup. we only add when it loses cause we only need to know how many other hands its worse than
            for (HandClass comparisonHand: allHands){
                if (handClassinArray == comparisonHand) {
                    continue; // Skips same hand
                }

                System.out.println("FirstItem Hand" + Arrays.toString(handClassinArray.handArray));
                System.out.println("Comparison Hand" + Arrays.toString(comparisonHand.handArray)); // The comparison hand is a duplicate of the first item hand!!!
                String firstHandArrayItem = handClassinArray.handArray[handitemindex];
                String comparisonHandArrayItem = comparisonHand.handArray[handitemindex]; // These give u the current item

                int locationOfFirstHandItem = wordOfCardString.indexOf(firstHandArrayItem);
                System.out.println("Location of item " + firstHandArrayItem + " = " + locationOfFirstHandItem);
                int locationOfComparisonHandItem = wordOfCardString.indexOf(comparisonHandArrayItem);
                System.out.println("Location of Comparison item " + comparisonHandArrayItem + " = " + locationOfComparisonHandItem);


                while (completedMatchup != true) {
                    if (!handClassinArray.handType.equals(comparisonHand.handType)) { // if theyre NOT even the same hand, end early
                        completedMatchup = true;
                        System.out.println("Incompatable hand type, skip");
                    } else if (locationOfFirstHandItem > locationOfComparisonHandItem) { // If they're the same hand & The comparison item has the lesser index (appears earlier)
                        comparisonsRankCounter ++; // Loss
                        completedMatchup = true;
                        System.out.println("Loss");
                    } else if (locationOfFirstHandItem < locationOfComparisonHandItem) {
                        completedMatchup = true; // Victory. Skip
                        System.out.println("Victory");
                    } else { // In this case it appears to be a tie
                        handitemindex++; // Go to next number
                        System.out.println("Tie. Index is now " + handitemindex);
                        firstHandArrayItem = handClassinArray.handArray[handitemindex];
                        comparisonHandArrayItem = comparisonHand.handArray[handitemindex]; // These give u the current item
                        locationOfFirstHandItem = wordOfCardString.indexOf(firstHandArrayItem);
                        locationOfComparisonHandItem = wordOfCardString.indexOf(comparisonHandArrayItem); // Redefine everything so you can recompare
                        System.out.println("Location of item " + firstHandArrayItem + " = " + locationOfFirstHandItem);
                        System.out.println("Location of Comparison item " + comparisonHandArrayItem + " = " + locationOfComparisonHandItem);

                        // No completed matchup because they should keep going until something is decided. Each hand is unique so there WILL eventually be an item where they'll differ
                    }
                }

                preliminaryRank += comparisonsRankCounter;
                handClassinArray.setRank(preliminaryRank);
                // The current item has ran through all its matches

                System.out.println("Matchups Lost: " + comparisonsRankCounter + "Final Rank: " + preliminaryRank);

            }

        }
    }
}

