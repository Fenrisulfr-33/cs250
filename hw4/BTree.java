package cs250.hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BTree implements TreeStructure {

    public BTree() {
        // Constructor for B-tree
    }

    @Override
    public void insert(Integer num) {
        // Implementation for inserting a value into the B-tree
    }

    @Override
    public boolean remove(Integer num) {
        // Implementation for removing a value from the B-tree
        return false;
    }

    @Override
    public long get(Integer num) {
        // Implementation for retrieving a value from the B-tree
        return 0;
    }

    @Override
    public int findMaxDepth() {
        // Implementation for finding the maximum depth of the B-tree
        return 0;
    }

    @Override
    public int findMinDepth() {
        // Implementation for finding the minimum depth of the B-tree
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File(args[0]);
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        TreeStructure tree = new BTree();
        Random rng = new Random(42);
        ArrayList<Integer> nodesToRemove = new ArrayList<>();
        ArrayList<Integer> nodesToGet = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            Integer lineInt = Integer.parseInt(line);
            tree.insert(lineInt);
            Integer rand = rng.nextInt(10);
            if (rand < 5)
                nodesToRemove.add(lineInt);
            else if (rand >= 5)
                nodesToGet.add(lineInt);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }
        System.out.println("Max depth: " + tree.findMaxDepth());
        System.out.println("Min depth: " + tree.findMinDepth());
        for (Integer num : nodesToRemove) {
            tree.remove(num);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }
        System.out.println("Max depth: " + tree.findMaxDepth());
        System.out.println("Min depth: " + tree.findMinDepth());
    }

    /**
     * Example output:
     * 
     * -1643699032 inserted at 15715236838213
     * 1614093892 inserted at 15715236870816
     * -1000272748 inserted at 15715236880460
     * -1907667521 inserted at 15715236889689
     * 1912735862 inserted at 15715236906432
     * -1142304848 inserted at 15715236948494
     * 1564342588 inserted at 15715236967429
     * 624763728 inserted at 15715236988701
     * -200006731 inserted at 15715237022938
     * -284011743 inserted at 15715237052359
     * Max depth: 2
     * Min depth: 2
     * -1643699032 inserted at 15715236838213
     * 1614093892 inserted at 15715236870816
     * -1000272748 inserted at 15715236880460
     * -1907667521 inserted at 15715236889689
     * 1912735862 inserted at 15715236906432
     * -1142304848 inserted at 15715236948494
     * 1564342588 inserted at 15715236967429
     * 624763728 inserted at 15715236988701
     * -200006731 inserted at 15715237022938
     * -284011743 inserted at 15715237052359
     * Max depth: 2
     * Min depth: 2
     */
}
