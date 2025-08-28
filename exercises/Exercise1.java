package cs250.exercises;

public class Exercise1 {
    // Print the first element in the command line
    public static void main(String[] args) {
        if (args.length > 0){
            System.out.println(args[0]);
        }
    }
}