package cs250.exercises;

import java.util.Arrays;

public class Exercise7 {
        public static void main(String[] args) {
                // Parse the two arguments from the command line as integers
                int start = Integer.parseInt(args[0]);
                int end = Integer.parseInt(args[1]);
                // Create a square matrix of dimensions (end - start + 1) x (end - start + 1)
                int matrix[][] = new int[end - start + 1][end - start + 1];
                // Keep the rowMultiplier outside of the loop
                int rowMultiplier = start;
                for (int row = 0; row < matrix.length; row++) {
                        // create a col multiplier that increases with each loop but resets on row
                        // switch
                        int colMultiplier = start;
                        for (int col = 0; col < matrix[row].length; col++) {
                                // multiply row multiplier by col multiplier
                                matrix[row][col] = rowMultiplier * colMultiplier;
                                colMultiplier++;
                        }
                        rowMultiplier++;

                }
                // Print out the matrix
                System.out.println(Arrays.deepToString(matrix));
                // Print out the sum of the diagonal row from top right to bottom left
                int sum = 0;
                int rowIndex = 0;
                int colIndex = end - start;
                for (int row = 0; row < matrix.length; row++) {
                        sum += matrix[rowIndex][colIndex];
                        rowIndex += 1;
                        colIndex -= 1;
                }
                System.out.println(sum);
        }
}