package cs250.exercises;

import java.util.Random;
import java.util.Scanner;

public class Exercise3 {
    /**
     * 
     * @param args
     * 
     *             Use the random class to generate
     *             a float in the range(10.50, 100.75) inclusive and
     *             an int in the range (10, 20) inclusive
     *             Print the integer first and then the float to stdout
     *             Use the scanner class to read two inputs from stdin
     *             (make sure to use hasNextLine, nextLine, and close)
     *             Add all 4 values and print the sum to stdout
     */
    public static void main(String[] args) {
        Random random = new Random();
        // generate a float in the range 10.50 > 100.75
        float randomFloat = random.nextFloat((float) 10.50, (float) 100.76);
        // java 8 variant is random.nextFloat(upperbound - lowerbound) + lowerbound;
        // float randomFloat = random.nextFloat((float)100.75 - (float)10.50) + 10.50; 
        // generate a int in the range 10 > 20
        int randomInt = random.nextInt(10, 21);
        // Print the Int first then the Float
        System.out.println(randomInt);
        System.out.println(randomFloat);
        // Use the scanner to get two numbers from input
        Scanner scanner = new Scanner(System.in);

        // Add all 4 numbers and print it to screen
        float total = randomFloat + randomInt;

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            total += Float.parseFloat(input);
        }
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            total += Float.parseFloat(input);
        }

        scanner.close();

        System.out.println(total);
    }

    /**
     * Test
     * 
     * Input: 31.22 65
     * 
     * Output: 19
     * 10.54
     * 125.76
     */
}
