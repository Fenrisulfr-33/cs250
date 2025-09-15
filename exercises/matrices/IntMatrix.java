package matrices;

import java.util.Random;

public class IntMatrix {
    public int[][] generateIntMatrix() {
        Random random = new Random();
        int[][] matrix = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Generate a random number within the specified range [min, max]
                matrix[i][j] = random.nextInt();
            }
        }

        return matrix;
    }
}