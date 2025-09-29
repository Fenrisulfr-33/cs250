public class Memory {
    private static volatile int i = 0;

    /**
     * Task 1 Report
     * 
     * From multiple runs of the same command argument I can see their are different times it takes to run the same
     * function each time. Effectively using the volatile keyword for the same function doubles it's time then non
     * volatile keyword.
     * 
     * 0.0161892 > 0.0375656
     * 0.0153009 > 0.0355105
     * 0.0148529 > 0.0364333
     * @param size
     */
    public static void task1(int size){
        long start;
        long end;
        double volatileTime;
        double regularTime;
        long regularAvg = 0;
        long volatileAvg = 0;

        start = System.nanoTime();
        for (int i = 0; i < size; i++){
            if (i % 2 == 0){
                regularAvg += i;
            } else {
                regularAvg -= i;
            }
        }
        end = System.nanoTime();
        regularTime = (double) (end - start) / 1000000000;

        start = System.nanoTime();
        for (i = 0; i < size; i++){
            if (i % 2 == 0){
                volatileAvg += i;
            } else {
                volatileAvg -= i;
            }
        }
        end = System.nanoTime();
        volatileTime = (double) (end - start) / 1000000000;

        System.out.println("Task 1");
        System.out.println("Regular: " + regularTime + " seconds");
        System.out.println("Volatile: " + volatileTime + " seconds");
        System.out.println("Avg regular sum: " + regularAvg);
        System.out.println("Avg regular sum: " + volatileAvg);

    }
    public static void main(String[] args) {
        // Task 1 argument command line
        // java cs250.hw2.Memory <size> <experiments> <seed>
        // java cs250.hw2.Memory 25000000 1 42
        int size = Integer.parseInt(args[0]);
        task1(size);
    }
}