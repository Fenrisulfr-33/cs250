package exercises;

public class Exercise5 {
    public static void main(String[] args) {
        // if the string contains a number, print 'number'; else print 'no number'
        if (args[0].matches(".*\\d.*")) {
            System.out.println("number");
        } else {
            System.out.println("no number");
        }
        // If the string contains the letter 'a' or the letter 'b', print 'true'; else
        // print 'false'
        if (args[0].contains("a") || args[0].contains("b")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        // If the length of the string is greater than 5 and less than 10, print the
        // length in words (all lower), ie. six not 6;
        // else, print the numeric value (ideally use a switch for this).
        if (args[0].length() > 5 && args[0].length() < 10) {
            switch (args[0].length()) {
                case 6:
                    System.out.println("six");
                    break;
                case 7:
                    System.out.println("seven");
                    break;
                case 8:
                    System.out.println("eight");
                    break;
                case 9:
                    System.out.println("nine");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        } else {
            System.out.println(args[0].length());
        }
        // If the length of the string is odd, print 'odd'; if it is even, print 'even'
        if (args[0].length() % 2 == 0){
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
        // if the char at index 3 is not lowercase, print the char as lowercase; else print it in uppercase
        if (args[0].charAt(3) == args[0].toLowerCase().charAt(3)){
            System.out.println(args[0].toUpperCase().charAt(3));
        } else {
            System.out.println(args[0].toLowerCase().charAt(3));
        }
    }
}
