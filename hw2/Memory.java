package cs250.hw2;

import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class Memory {
    private static volatile int i = 0;

    /**
     * Task 1 Report
     * 
     * From multiple runs of the same command argument I can see their are different
     * times it takes to run the same
     * function each time. Effectively using the volatile keyword for the same
     * function doubles it's time then non
     * volatile keyword.
     * 
     * 0.0161892 > 0.0375656
     * 0.0153009 > 0.0355105
     * 0.0148529 > 0.0364333
     * 
     * @param size
     */
    public static void task1(int size) {
        long regularAvg = 0;
        long volatileAvg = 0;

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                regularAvg += i;
            } else {
                regularAvg -= i;
            }
        }
        long end = System.nanoTime();
        double regularTime = (double) (end - start) / 1000000000;

        start = System.nanoTime();
        for (i = 0; i < size; i++) {
            if (i % 2 == 0) {
                volatileAvg += i;
            } else {
                volatileAvg -= i;
            }
        }
        end = System.nanoTime();
        double volatileTime = (double) (end - start) / 1000000000;

        System.out.println("Task 1");
        System.out.println("Regular: " + regularTime + " seconds");
        System.out.println("Volatile: " + volatileTime + " seconds");
        System.out.println("Avg regular sum: " + regularAvg);
        System.out.println("Avg volatile sum: " + volatileAvg);
    }

    // TODO: task 2
    private static void task2(int size, int experiments, int seed) {
        // Create Integer array with size given
        Integer[] arr = new Integer[size];
        // Create Random with the given seed
        Random random = new Random(seed);
        // Fill the array with Random numbers
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }

        /**
         * Calculate the time to access each element in the first 10% of the array and a
         * single random element in
         * the last 10% of the array.
         * Next, maintain a sum of each of the elements accessed and report the average
         * across experiments for
         * each of the following:
         */
        long totalSum = 0;
        // Create lengths for the first 10 percent and last 10 percent
        int first10PercentIndex = (int) (size * 0.1);
        int last10PercentIndex = (int) (size * 0.9);

        double first10TotalTime = 0;
        double last10TotalTime = 0;

        long knownElementStart = 0;
        long knownElementEnd = 0;
        for (int i = 0; i < experiments; i++) {
            long sum = 0;
            // Function goes in here
            for (int j = 0; j < first10PercentIndex; j++) {
                knownElementStart = System.nanoTime();
                sum += arr[j];
                knownElementEnd = System.nanoTime();
            }
            first10TotalTime += (knownElementEnd - knownElementStart);

            int randomIndex = random.nextInt(size - last10PercentIndex) + last10PercentIndex;
            long startRandom = System.nanoTime();
            // Function goes in here
            sum += arr[randomIndex];
            long endRandom = System.nanoTime();
            last10TotalTime += (endRandom - startRandom);
            totalSum += sum;
        }

        double first10AvgTime = first10TotalTime / experiments;
        double last10AvgTime = last10TotalTime / experiments;

        System.out.println();
        System.out.println("Task 2");
        System.out.println("Avg time to access known element: " + first10AvgTime + " nanoseconds");
        System.out.println("Avg time to access random element: " + last10AvgTime + " nanoseconds");
        System.out.println("Sum: " + totalSum / experiments);
    }

    // TODO: task 3
    private static void task3(int size, int experiments, int seed) {
        Random random = new Random(seed);
        TreeSet<Integer> treeSet = new TreeSet<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < size; i++){
            treeSet.add(i);
            linkedList.add(i);
        }

        long totalTimeTreeSet = 0;
        long totalTimeLinkedList = 0;

        for (i = 0; i < experiments; i++){
            int randomIndex = random.nextInt(size);

            long startTreeTime = System.nanoTime();
            treeSet.contains(randomIndex);
            long endTreeTime = System.nanoTime();
            totalTimeTreeSet += (endTreeTime - startTreeTime);

            long startLLTime = System.nanoTime();
            linkedList.contains(randomIndex);
            long endLLTime = System.nanoTime();
            totalTimeLinkedList += (endLLTime - startLLTime);
        }

        System.out.println();
        System.out.println("Task 3");
        System.out.println("Avg time to find in set: " + totalTimeTreeSet / experiments + " nanoseconds");
        System.out.println("Avg time to find in list: " + totalTimeLinkedList / experiments + " nanoseconds");
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int experiments = Integer.parseInt(args[1]);
        int seed = Integer.parseInt(args[2]);
        // Task 1 argument command line
        // java cs250.hw2.Memory <size> <experiments> <seed>
        // java cs250/hw2/Memory.java 25000000 1 42
        task1(size);
        task2(size, experiments, seed);
        task3(size, experiments, seed);

        /**
         * java cs250/hw2/Memory.java 25000000 1 42
         * 
         * Task 1 
         * Regular: x seconds
         * Volatile: x seconds
         * Avg regular sum: -12500000
         * Avg volatile sum: -12500000
         * 
         * Task 2
         * Avg time to access known element: 100.0 nanoseconds
         * Avg time to access random element: 2700.0 nanoseconds
         * Sum: -623797425765.00
         * 
         * Task 3
         * Avg time to find in set: 0.0 nanoseconds
         * Avg time to find in list: 0.0 nanoseconds
         */

        /**
         * java cs250/hw2/Memory.java 25000000 20 42
         * 
         * Task 1 
         * Regular: x seconds
         * Volatile: x seconds
         * Avg regular sum: -12500000
         * Avg volatile sum: -12500000
         * 
         * Task 2
         * Avg time to access known element: 100.0 nanoseconds
         * Avg time to access random element: 2700.0 nanoseconds
         * Sum: -622985840515.00
         * 
         * Task 3
         * Avg time to find in set: 0.0 nanoseconds
         * Avg time to find in list: 0.0 nanoseconds
         */
    }
}