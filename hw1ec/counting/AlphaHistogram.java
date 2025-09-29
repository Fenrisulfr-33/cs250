package cs250.ec.counting;

import java.util.HashMap;

public class AlphaHistogram {
        /**
     * 
     * @param sentence
     */
    public static void task2(String sentence) {
        int highestValue = 0;
        String newString = sentence.replace(" ", "").toLowerCase();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < newString.length(); i++) {
            char letter = newString.charAt(i);
            // add char to hashMap
            if (map.containsKey(letter)) {
                int value = map.get(letter);
                int newValue = ++value;
                if (highestValue < newValue) {
                    highestValue = newValue;
                }
                map.put(letter, value);
            } else {
                if (highestValue < 1) {
                    highestValue = 1;
                }
                map.put(letter, 1);
            }
        }

        String sortedSentence = sortSentence(newString, false);
        HashMap<Character, Integer> newMap = new HashMap<>();

        for (int i = highestValue; i > 0; i--) {
            for (int j = 0; j < sortedSentence.length(); j++) {
                Character letter = sortedSentence.charAt(j);
                // System.out.println(letter);
                if (!newMap.containsKey(letter)) {
                    Integer value = map.get(letter);
                    // System.out.println(value);
                    if (value == i) {
                        newMap.put(letter, i);
                        System.out.println(letter + ": " + value);
                    }
                }
            }
        }
    }

    public static String sortSentence(String sentence, boolean sorted) {
        if (sorted) {
            return sentence;
        } else {
            char[] unsortedSentence = sentence.toCharArray();
            boolean swap = false;
            if (!sorted) {
                for (int i = 1; i < unsortedSentence.length; i++) {
                    if (unsortedSentence[i - 1] > unsortedSentence[i]) {
                        swap = true;
                        char temp = unsortedSentence[i - 1];
                        unsortedSentence[i - 1] = unsortedSentence[i];
                        unsortedSentence[i] = temp;
                    }
                }
            }
            String nextItr = new String(unsortedSentence);
            if (swap) {
                return sortSentence(nextItr, false);
            } else {
                return sortSentence(nextItr, true);
            }
        }
    }

    public static void main(String[] args) {
        task2(args[0]);
    }
}
