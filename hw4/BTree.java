package cs250.hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BTree implements TreeStructure {
    private BTreeNode root;

    public BTree() {
        // Constructor for B-tree
        this.root = null;
    }

    @Override
    public void insert(Integer num) {
        // Implementation for inserting a value into the B-tree
        if (root == null) {
            root = new BTreeNode(true);
            root.keys[0] = num;
            root.timeInserted[0] = System.nanoTime();
            root.numKeys = 1;
        } else if (root.numKeys == BTreeNode.Max - 1) {
            // Split root
            BTreeNode newRoot = new BTreeNode(false);
            newRoot.children[0] = root;
            splitChild(newRoot, 0);
            root = newRoot;
            insertNotFull(root, num);
        } else {
            insertNotFull(root, num);
        }
    }

    /**
     * Helper method to split a full child node
     * 
     * @param parent the parent node
     * @param index  the index of the child to split
     */
    private void splitChild(BTreeNode parent, int index) {
        BTreeNode fullChild = parent.children[index];
        int T = BTreeNode.Max / 2; // T = 32 when Max = 64

        BTreeNode newChild = new BTreeNode(fullChild.isLeaf);
        newChild.numKeys = T - 1; // 31 keys

        // move last T-1 keys from fullChild to newChild
        for (int j = 0; j < T - 1; j++) {
            newChild.keys[j] = fullChild.keys[j + T];
            newChild.timeInserted[j] = fullChild.timeInserted[j + T];

            fullChild.keys[j + T] = null;
            fullChild.timeInserted[j + T] = 0L;
        }

        // move children if fullChild is not a leaf
        if (!fullChild.isLeaf) {
            for (int j = 0; j < T; j++) {
                newChild.children[j] = fullChild.children[j + T];
                fullChild.children[j + T] = null;
            }
        }

        // fullChild now keeps the first T-1 keys
        fullChild.numKeys = T - 1;

        // make room in parent.children[]
        for (int j = parent.numKeys; j >= index + 1; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        parent.children[index + 1] = newChild;

        // shift parent keys/time to make room for middle key
        for (int j = parent.numKeys - 1; j >= index; j--) {
            parent.keys[j + 1] = parent.keys[j];
            parent.timeInserted[j + 1] = parent.timeInserted[j];
        }

        // move median key from fullChild up into parent
        parent.keys[index] = fullChild.keys[T - 1];
        parent.timeInserted[index] = fullChild.timeInserted[T - 1];

        // (optional) clear the median in fullChild – it’s conceptually removed
        fullChild.keys[T - 1] = null;
        fullChild.timeInserted[T - 1] = 0L;

        parent.numKeys++;
    }

    /**
     * Helper method to insert a key into a node not full
     * 
     * @param node the node to insert into
     * @param num  the key to insert
     */
    private void insertNotFull(BTreeNode node, Integer num) {
        int i = node.numKeys - 1;

        if (node.isLeaf) {
            // shift keys/time to make room
            while (i >= 0 && num < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                node.timeInserted[i + 1] = node.timeInserted[i];
                i--;
            }
            node.keys[i + 1] = num;
            node.timeInserted[i + 1] = System.nanoTime();
            node.numKeys++;
        } else {
            // find child to descend into
            while (i >= 0 && num < node.keys[i]) {
                i--;
            }
            i++; // child index

            // if child is full, split first
            if (node.children[i].numKeys == BTreeNode.Max - 1) { // MAX_KEYS
                splitChild(node, i);
                // after split, decide which of the two children to use
                if (num > node.keys[i]) {
                    i++;
                }
            }

            insertNotFull(node.children[i], num);
        }
    }

    @Override
    public boolean remove(Integer num) {
        if (root == null)
            return false;

        boolean removed = removeRec(root, num);

        // clean up root if it becomes empty
        if (root.numKeys == 0) {
            if (root.isLeaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
        return removed;
    }

    /**
     * Helper method to remove a key recursively
     * 
     * @param node current node
     * @param num  key to remove
     * @return true if the key was removed, false otherwise
     */
    private boolean removeRec(BTreeNode node, Integer num) {
        if (node == null)
            return false;

        int i = 0;
        while (i < node.numKeys && num > node.keys[i]) {
            i++;
        }

        // key found in this node
        if (i < node.numKeys && num.equals(node.keys[i])) {
            if (node.isLeaf) {
                // shift left
                for (int j = i; j < node.numKeys - 1; j++) {
                    node.keys[j] = node.keys[j + 1];
                    node.timeInserted[j] = node.timeInserted[j + 1];
                }
                node.keys[node.numKeys - 1] = null;
                node.timeInserted[node.numKeys - 1] = 0L;
                node.numKeys--;
                return true;
            } else {
                // internal node: replace with successor in right subtree
                BTreeNode succ = node.children[i + 1];
                while (!succ.isLeaf) {
                    succ = succ.children[0];
                }
                // take first key from successor
                node.keys[i] = succ.keys[0];
                node.timeInserted[i] = succ.timeInserted[0];
                // now delete that key from the successor leaf
                return removeRec(succ, node.keys[i]);
            }
        } else {
            // not found in this node: go down if possible
            if (node.isLeaf) {
                return false;
            }
            return removeRec(node.children[i], num);
        }
    }

    @Override
    public long get(Integer num) {
        if (root == null) {
            return -1; // Tree is empty
        }
        return getRec(root, num);
    }

    /**
     * Helper method to get the insertion time of a key recursively
     * 
     * @param node current node
     * @param num  key to find
     * @return insertion time or -1 if not found
     */
    private long getRec(BTreeNode node, Integer num) {
        if (node == null) {
            return -1;
        }
        int i = 0;
        while (i < node.numKeys && num > node.keys[i]) {
            i++;
        }
        if (i < node.numKeys && num.equals(node.keys[i])) {
            return node.timeInserted[i];
        }
        if (node.isLeaf) {
            return -1;
        }
        return getRec(node.children[i], num);
    }

    @Override
    public int findMaxDepth() {
        return findMaxDepthRec(root);
    }

    private int findMaxDepthRec(BTreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf) {
            return 1;
        }
        int maxDepth = 0;
        for (int i = 0; i <= node.numKeys; i++) {
            int childDepth = findMaxDepthRec(node.children[i]);
            if (childDepth > maxDepth) {
                maxDepth = childDepth;
            }
        }
        return maxDepth + 1;
    }

    @Override
    public int findMinDepth() {
        return findMinDepthRec(root);
    }

    private int findMinDepthRec(BTreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf) {
            return 1;
        }
        int minDepth = Integer.MAX_VALUE;
        for (int i = 0; i <= node.numKeys; i++) {
            int childDepth = findMinDepthRec(node.children[i]);
            if (childDepth < minDepth) {
                minDepth = childDepth;
            }
        }
        return minDepth + 1;
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
