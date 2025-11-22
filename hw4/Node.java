package cs250.hw4;

public class Node {
    Integer num;
    long timeInserted;
    Node left;
    Node right;

    public Node(Integer num, long timeInserted) {
        this.num = num;
        this.timeInserted = timeInserted;
        this.left = null;
        this.right = null;
    }
}
