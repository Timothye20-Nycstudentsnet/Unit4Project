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
            int[] handvaluesArray = new int[wordOfCard.length]; // Handvalues contains the OCCURENCE DATA of each Card (Ordered as in Word of Card)
            String handvaluesString = ""; // Saving as both an array and string because sometimes we'll need item by item


            for (int i = 0; i < wordOfCard.length; i++) {
                handvaluesArray[i] = HandClass.parseThroughHandResults(i, handArray, wordOfCard); // This assigns occurence data (zero 2's. one 3, zero 4's. two 5... = 0102...)
                handvaluesString += handvaluesArray[i]; //If we can just get handvalues as a string we can then just parse normally
            }


            allHands[index] = new HandClass(handsStrength, handArray, handvaluesString);
            String handType = HandClass.determineHandType(handvaluesString); // HandType defines itself as the result of determinginghandtype process. Aka its wha stores the type of ahdn it is

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

        for (HandClass HandClass: allHands){

        }
    }
}

