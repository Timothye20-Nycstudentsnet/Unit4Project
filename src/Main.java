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


        System.out.println(fileData);
        String[] lines = fileData.split("/n"); // splits line to line

        int namingConvention = 0;
        boolean linePasses;
        int successfulLines = 0; // outside to avoid resetting every line
        for (String line : lines) {
            namingConvention++;


            String[] hand = line.split("\\|", 2); // split hand into "X,X,X,X,X" & "HandStrength"
            int handsStrength = Integer.parseInt(hand[1]);
            Hand objectName = new Hand(hand[0], handsStrength);

             /* System.out.println(Arrays.toString(numbers));
            int[] values = new int[numbers.length];

            for (int i = 0; i < values.length; i++) {
                values[i] = Integer.parseInt(numbers[i]);
            }
            
              */
        }
        System.out.println("Answer: " + successfulLines);

    }
}