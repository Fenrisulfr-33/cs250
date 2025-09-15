package cs250.helpers;

import java.util.Arrays;

public class ExerciseHelper {
    public void transposeMatrix(int[][] arr) {
        // Get the dimensions of the original matrix
        int rows = arr.length;
        int cols = arr[0].length;

        // Create a new matrix for the transpose with swapped dimensions
        // dimensions can be non squared and still create a opposite rectangle
        int[][] transposedMatrix = new int[cols][rows];

        // Populate the transposed matrix by swapping rows and columns
        // eg. 0,1 swaps 1,0 and 0,2 swaps 2,0 and so on...
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = arr[i][j];
            }
        }
        System.out.println(Arrays.deepToString(transposedMatrix));
    }
}
