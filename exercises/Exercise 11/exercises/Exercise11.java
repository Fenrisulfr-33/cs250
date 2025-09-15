package cs250.exercises;

import cs250.helpers.ExerciseHelper;
import matrices.IntMatrix;

public class Exercise11 {
    public static void main(String[] args) {
        ExerciseHelper exerciseHelper = new ExerciseHelper();
        IntMatrix intMatrix = new IntMatrix();
        int[][] matrix = intMatrix.generateIntMatrix();
        exerciseHelper.transposeMatrix(matrix);
    }

}
