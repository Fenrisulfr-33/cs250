package cs250.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exercise12 {

    /**
     * Takes in an .csv file with a col for a directory path
     * and another column for the test to be passed into the .txt
     * 
     * @param CSVInputFile
     */
    public static void CSVReader(String CSVInputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSVInputFile))) {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] csvCols = csvLine.split(",");
                String[] directory = csvCols[0].split("/");
                String directoryPath = "";
                for (int i = 0; i < directory.length; i++) {
                    if (!directory[i].contains(".txt")) {
                        // join the directory path to create directory
                        directoryPath += directory[i] + "/";
                    }
                }
                // if the directory length is less than 2 that means there is only one
                // folder at most and one .txt file so no need to make nested directories
                if (directory.length < 2) {
                    makeDirectory(directoryPath);
                } else {
                    makeDirectories(directoryPath);
                }
                // write to the directory path after it has been created the .txt file
                CSVWriter(csvCols[0], csvCols[1]);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * A CSV writer that takes in the directory path and the text to be created
     * 
     * @param CSVOutputFile
     * @param CSVFileText
     */
    public static void CSVWriter(String CSVOutputFile, String CSVFileText) {
        try (FileWriter writer = new FileWriter(CSVOutputFile);) {
            writer.write(CSVFileText);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Creates a single directory 
     * @param directoryPath
     */
    public static void makeDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        directory.mkdir();
    }

    /**
     * Creates multiple directories 
     * @param directoryPath
     */
    public static void makeDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        directory.mkdirs();
    }

    /**
     * deletes a directory from the path
     * @param directoryPath
     */
    public static void deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        // deletes directory only if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            directory.delete();
        }
    }

//    public static void deleteDirectory(String directoryPath) {
//         File directory = new File(directoryPath);
//         if (directory.isDirectory()) {
//             File[] files = directory.listFiles();
//             if (files != null) {
//                 for (File file : files) {
//                     deleteDirectory(file.getName()); // Recursive call for subdirectories
//                 }
//             }
//         }
//         directory.delete(); // Delete the file or empty directory
//     }

    /**
     * Deletes a file from the file path if it exists
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) {
        CSVReader("input.csv");
        deleteFile("input.csv");

        // create a clean up for testing purposes
        // deleteDirectory("dir3/subdir/3/file3.txt");
        // deleteDirectory("dir1/file1.txt");
        // deleteFile("file2.txt");
        // recreate the input.csv
    }

}
