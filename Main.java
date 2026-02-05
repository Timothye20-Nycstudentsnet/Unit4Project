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
        int fivOfAKind = 0;
        int fouOfAKind = 0;
        int fullHouse = 0;
        int throfAKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;
        for (String line : lines) {

            String[] hand = line.split("\\|", 2); // Split line into Array of 2 items. the Cardnames (String) & The betting power)

            int handsStrength = Integer.parseInt(hand[1].trim()); //Store betting power in this int
            String handNames = hand[0]; //String of all the Cardnames so u can split them into an array
            String[] handArray = handNames.split(","); //HandArray contains an array of all the CardNames

            String[] wordOfCard = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
            int[] handvalues = new int[wordOfCard.length]; // Handvalues contains the OCCURENCE DATA of each Card (Ordered as in Word of Card)
            String handvaluesString = "";


            for (int i = 0; i < wordOfCard.length; i++) {
                handvalues[i] = MethodStorage.parseThroughHandResults(i, handArray, wordOfCard);
                handvaluesString += handvalues[i]; //If we can just get handvalues as a string we can then just parse normally
            }

            String handType = MethodStorage.determineHandType(handvaluesString);

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

        }
        System.out.println("Number of five of a kind hands: " + fivOfAKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fouOfAKind);
        System.out.println("Number of three of a kind hands: " + throfAKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);

    }
}

