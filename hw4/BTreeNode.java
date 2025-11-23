package cs250.hw4;

public class BTreeNode {
    static final int ORDER = 64;                 // max children
    static final int Max = ORDER;                // you already have Max
    static final int MAX_KEYS = ORDER - 1;       // 63 keys

    Integer[] keys = new Integer[MAX_KEYS];
    long[] timeInserted = new long[MAX_KEYS];
    BTreeNode[] children = new BTreeNode[ORDER]; // 0..63 children
    int numKeys;
    boolean isLeaf;

    BTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.numKeys = 0;
    }
}
