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
                System.out.println(handvalues[i]);
            }

            String s = MethodStorage.determineHandType(handvalues);
            // Handvalues contains the OCCURENCE DATA of each Card (Ordered as in Word of Card) [Ie: 0,0,1,2,0,0...]

        }
    }
}

