package cs250.exercises;

import java.sql.Array;
import java.util.Arrays;
import java.util.Random;

public class Exercise6 {
    /**
     * In the main method, read three argument passed in through the command line
     * the 1st is the type of either (‘int’, ‘float’, ‘char’)
     * the 2nd is the x
     * the 3rd is the y
     * Construct the appropriate array of dimensions 4 x 3 with the correct type. If
     * int, fill all with a random int. If float, fill all with a random float. If
     * char, fill all with a random character.
     * Print to stdout (in the same order):
     * the 2D array using “Arrays.deepToString()”. You will have to import
     * “java.util.Arrays”
     * the value at (x, y)
     * the array at index (x - y) in sorted order
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Initialize a new random class
        Random random = new Random();
        // Using 3 arguments from the command line where the first is the type of number
        // second is x, the amount of columns, and y the amount of rows
        // create the appropriate sized matrix of random numbers
        String typeOfNumber = args[0];
        int rows = Integer.parseInt(args[1]); // Number of rows
        int cols = Integer.parseInt(args[2]); // Number of columns
        // if else for the three types on inputs
        // Print out the array af random values after creation
        if (typeOfNumber.equals("int")) {
            int[][] twoDimensionalArray = new int[rows][cols];
            System.out.println(Arrays.deepToString(twoDimensionalArray));
            System.out.println(twoDimensionalArray[rows][cols]);
        } else if (typeOfNumber.equals("float")) {
            float[][] twoDimensionalArray = new float[rows][cols];
            System.out.println(Arrays.deepToString(twoDimensionalArray));
            System.out.println(twoDimensionalArray[rows][cols]);
        } else if (typeOfNumber.equals("char")) {
            char[][] twoDimensionalArray = new char[rows][cols];
            System.out.println(Arrays.deepToString(twoDimensionalArray));
            System.out.println(twoDimensionalArray[rows][cols]);
        }
    }
}