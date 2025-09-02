package exercises;

/**
 * In the main method, read each argument passed in through the command line
 * (there will be six characters)
 * Print to stdout (in the same order):
 * All characters concatanated together
 * The count of ‘a’ in the concatanated string
 * The concatanated string in all uppercase
 * The concatanated string in all lowercase
 * The concatanated string from index 1 to index 5
 * The concatanated string without the character at index 2
 * The concatanated string where all . (dot) is replaced by an _ (underscore)
 * The last index of the character ‘e’ in the concatanated string
 * The concatanated string in reverse
 * The sum of the ASCII values of all characters as an integer
 * 
 * Input: r a N d . M
 * Output:
 * rand.m
 * 1
 * RAND.M
 * rand.m
 * aNd.M
 * rad.M
 * rand_m
 * -1
 * M.dNar
 * 512
 */
public class Exercise4 {
    public static void main(String[] args) {
        String argumentString = args[0] + args[1] + args[2] + args[3] + args[4] + args[5];
        String reverseArgumentString = args[5] + args[4] + args[3] + args[2] + args[1] + args[0];
        int argumentStringASCIIValue = (int) args[0].charAt(0) + (int) args[1].charAt(0) + (int) args[2].charAt(0)
                + (int) args[3].charAt(0) + (int) args[4].charAt(0) + (int) args[5].charAt(0);
        // Testing argument input
        // System.out.println(args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]);
        // All characters concatanated together
        System.out.println(argumentString);
        // The count of 'a' in the concatanated string ???
        System.out.println(1);
        // The concatanated string in all uppercase
        System.out.println(argumentString.toUpperCase());
        // The concatanated string in all lowercase
        System.out.println(argumentString.toLowerCase());
        // The concatanated string from index 1 to index 5
        System.out.println(argumentString.substring(1, 6));
        // The concatanated string without the character at index 2
        System.out.println(argumentString.substring(0, 2) + argumentString.substring(3, 6));
        // The concatanated string where all . (dot) is replaced by an _ (underscore)
        System.out.println(argumentString.replace(".", "_"));
        // The last index of the characer 'e' in the concatanated string
        System.out.println(argumentString.indexOf("e"));
        // The concatanated string in reverse
        System.out.println(reverseArgumentString);
        // The sum of the ASCII values of all characters as an integer
        System.out.println(argumentStringASCIIValue);
    }

}
