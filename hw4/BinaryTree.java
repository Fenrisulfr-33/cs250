package cs250.hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTree implements TreeStructure {
    private Node root;

    public BinaryTree() {
        // Constructor for Binary Tree
        this.root = null;
    }

    @Override
    public void insert(Integer num) {
        // Implementation for inserting a value into the binary tree
        Node newNode = new Node(num, System.nanoTime());
        if (root == null) {
            root = newNode;
        } else {
            // insert node recursively
            insertRec(root, newNode);
        }
    }

    /**
     * Helper method to insert a node recursively
     * 
     * @param current current node
     * @param newNode new node to be inserted
     */
    private void insertRec(Node current, Node newNode) {
        if (newNode.num < current.num) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                insertRec(current.left, newNode);
            }
        } else if (newNode.num > current.num) {
            if (current.right == null) {
                current.right = newNode;
            } else {
                insertRec(current.right, newNode);
            }
        }
    }

    @Override
    public boolean remove(Integer num) {
        // Implementation for removing a value from the binary tree
        if (root == null) {
            return false; // Tree is empty
        }
        deleteRec(root, num);
        return false;
    }

    /**
     * Helper method to delete a node recursively
     * 
     * @param current current node
     * @param num     number to be deleted
     * @return the modified current node
     */
    private Node deleteRec(Node current, Integer num) {
        if (current == null) {
            return null;
        }

        if (num.equals(current.num)) {
            // Node to delete found
            if (current.left == null && current.right == null) {
                return null; // No children
            }
            if (current.left == null) {
                return current.right; // One child (right)
            }
            if (current.right == null) {
                return current.left; // One child (left)
            }
            // Two children
            Integer smallestValue = findSmallestValue(current.right);
            current.num = smallestValue;
            current.right = deleteRec(current.right, smallestValue);
            return current;
        }
        if (num < current.num) {
            current.left = deleteRec(current.left, num);
            return current;
        }
        current.right = deleteRec(current.right, num);
        return current;
    }

    /**
     * Helper method to find the smallest value in a subtree
     * 
     * @param root root of the subtree
     * @return the smallest value
     */
    private Integer findSmallestValue(Node root) {
        return root.left == null ? root.num : findSmallestValue(root.left);
    }

    @Override
    public long get(Integer num) {
        // Implementation for retrieving a value from the binary tree
        if (root == null) {
            return -1; // Tree is empty
        } else {
            Node result = getRec(root, num);
            if (result != null) {
                return result.timeInserted;
            } else {
                return -1; // Not found
            }
        }
    }

    /**
     * Helper method to get a node recursively
     * 
     * @param current current node
     * @param num     number to be found
     * @return the node if found, null otherwise
     */   
    private Node getRec(Node current, Integer num) {
        if (current == null) {
            return null;
        }
        if (num.equals(current.num)) {
            return current;
        }
        if (num < current.num) {
            return getRec(current.left, num);
        } else {
            return getRec(current.right, num);
        }
    }

    @Override
    public int findMaxDepth() {
        // Implementation for finding the maximum depth of the binary tree
        return findMaxDepthRec(root);
    }

    /**
     * Helper method to find the maximum depth recursively
     * 
     * @param current current node
     * @return maximum depth from current node
     */
    private int findMaxDepthRec(Node current) {
        if (current == null) {
            return 0;
        }
        int leftDepth = findMaxDepthRec(current.left);
        int rightDepth = findMaxDepthRec(current.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public int findMinDepth() {
        // Implementation for finding the minimum depth of the binary tree
        return findMinDepthRec(root);
    }

    /**
     * Helper method to find the minimum depth recursively
     * 
     * @param current current node
     * @return minimum depth from current node
     */
    private int findMinDepthRec(Node current) {
        if (current == null) {
            return 0;
        }
        int leftDepth = findMinDepthRec(current.left);
        int rightDepth = findMinDepthRec(current.right);
        return Math.min(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File(args[0]);
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        TreeStructure tree = new BinaryTree();
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

        /**
         * Example output:
         * 
         * -1643699032 inserted at 13017969196679
         * 1614093892 inserted at 13017969230068
         * -1000272748 inserted at 13017969239743
         * -1907667521 inserted at 13017969249045
         * 1912735862 inserted at 13017969258249
         * -1142304848 inserted at 13017969303508
         * 1564342588 inserted at 13017969321896
         * 624763728 inserted at 13017969341361
         * -200006731 inserted at 13017969372713
         * -284011743 inserted at 13017969401098
         * Max depth: 33
         * Min depth: 5
         * -1643699032 inserted at 13017969196679
         * 1614093892 inserted at 13017969230068
         * -1000272748 inserted at 13017969239743
         * -1907667521 inserted at 13017969249045
         * 1912735862 inserted at 13017969258249
         * -1142304848 inserted at 13017969303508
         * 1564342588 inserted at 13017969321896
         * 624763728 inserted at 13017969341361
         * -200006731 inserted at 13017969372713
         * -284011743 inserted at 13017969401098
         * Max depth: 28
         * Min depth: 6
         */
    }
}
