package cs250.hw4;

public interface TreeStructure {
    /**
     * Insert a number into the tree
     * along with the current time from System.nanoTime()
     * @param num the number to insert
     */
    void insert(Integer num);

    /**
     * Remove a number from the tree
     * @param num the number to remove
     * @return true if the number was successfully removed, false otherwise
     */
    boolean remove(Integer num);

    /**
     * Get a number from the tree
     * @param num the number to get
     * @return the time that the node was inserted at
     */
    long get(Integer num);

    /**
     * Find the maximum depth of the tree
     * @return the maximum depth of the tree
     */
    int findMaxDepth();

    /**
     * Find the minimum depth of the tree
     * @return the minimum depth of the tree
     */
    int findMinDepth();
}    