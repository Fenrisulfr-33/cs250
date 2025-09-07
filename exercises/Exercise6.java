package cs250.exercises;

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
        // Create a string of all chars to random pull from
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        // Initialize a new random class
        Random random = new Random();
        // Using 3 arguments from the command line where the first is the type of number
        // second is x, the amount of columns, and y the amount of rows
        // create the appropriate sized matrix of random numbers
        String typeOfNumber = args[0];
        int x = Integer.parseInt(args[1]); //
        int y = Integer.parseInt(args[2]); //
        // if else for the three types on inputs
        // Print out the array af random values after creation of a 4x3 2D array
        if (typeOfNumber.equals("int")) {
            // int[][] twoDimensionalArray = new int[4][3];
            int[][] twoDimensionalArray = { { random.nextInt(), random.nextInt(), random.nextInt() },
                    { random.nextInt(), random.nextInt(), random.nextInt() },
                    { random.nextInt(), random.nextInt(), random.nextInt() },
                    { random.nextInt(), random.nextInt(), random.nextInt() } };
            System.out.println(Arrays.deepToString(twoDimensionalArray));
            System.out.println(twoDimensionalArray[x][y]);
            Arrays.sort(twoDimensionalArray[x - y]);
            System.out.println(Arrays.toString(twoDimensionalArray[x - y]));
        } else if (typeOfNumber.equals("float")) {

            // float[][] twoDimensionalArray = new float[4][3];
            float[][] twoDimensionalArray = {
                    { random.nextFloat(), random.nextFloat(), random.nextFloat() },
                    { random.nextFloat(), random.nextFloat(), random.nextFloat() },
                    { random.nextFloat(), random.nextFloat(), random.nextFloat() },
                    { random.nextFloat(), random.nextFloat(), random.nextFloat() } };
            System.out.println(Arrays.deepToString(twoDimensionalArray));
            System.out.println(twoDimensionalArray[x][y]);
            Arrays.sort(twoDimensionalArray[x - y]);
            System.out.println(Arrays.toString(twoDimensionalArray[x - y]));
        } else if (typeOfNumber.equals("char")) {
            // char[][] twoDimensionalArray = new char[x][y];
            char[][] twoDimensionalArray = {
                    { alphabet.charAt(random.nextInt(0, 27)),
                            alphabet.charAt(random.nextInt(0, 26)),
                            alphabet.charAt(random.nextInt(0, 26)) },
                    { alphabet.charAt(random.nextInt(0, 27)),
                            alphabet.charAt(random.nextInt(0, 26)),
                            alphabet.charAt(random.nextInt(0, 26)) },
                    { alphabet.charAt(random.nextInt(0, 27)),
                            alphabet.charAt(random.nextInt(0, 26)),
                            alphabet.charAt(random.nextInt(0, 26)) },
                    { alphabet.charAt(random.nextInt(0, 27)),
                            alphabet.charAt(random.nextInt(0, 26)),
                            alphabet.charAt(random.nextInt(0, 26)) } };
            System.out.println(Arrays.deepToString(twoDimensionalArray));
            System.out.println(twoDimensionalArray[x][y]);
            Arrays.sort(twoDimensionalArray[x - y]);
            System.out.println(Arrays.toString(twoDimensionalArray[x - y]));
        }
    }
}