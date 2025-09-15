package exercises;

import java.util.HashMap;
import java.util.HashSet;
import java.io.*;

public class Exercise14 {
    private static HashMap<String, Integer> hashMap = new HashMap<>();

    /**
     * Read the input file and separate the text into strings
     * @param txtFilePath
     */
    public static void txtReader(String txtFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath))) {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] textSplit = csvLine.split(" ");
                for (int i = 0; i < textSplit.length; i++){
                    updateHashMap(textSplit[i]);
                }
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Update the hash map to increment its value if it already exists
     * @param text
     */
    public static void updateHashMap(String text) {
        if (hashMap.containsKey(text)){
            int wordOccurrences = hashMap.get(text);
            hashMap.put(text, wordOccurrences + 1);
        } else {
            hashMap.put(text, 1);
        }
    }

    /**
     * Print out the hash map as a hash set without the argument words
     * @param exemptWords
     */
    public static void printHashMapWithoutArguments(String[] exemptWords){
        for (int i = 0; i < exemptWords.length; i++){
            hashMap.remove(exemptWords[i]);
        }

        HashSet<String> hashWords = new HashSet<>();
        for (String word : hashMap.keySet()) {
            hashWords.add(word);
        }

        System.out.println(hashWords);
    }

    public static void main(String[] args) {
        txtReader("input.txt");
        System.out.println(hashMap);
        printHashMapWithoutArguments(args);
    }

}
