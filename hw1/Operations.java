package hw1;

public class Operations {
    /**
     * Error handler for argument length not 3
     * @param args
     * @return  Error and status 0
     */
    public static int checkArgumentLength(String[] args) {
        if (args.length != 3) {
            System.out.println("Command must take in exactly 3 arguments");
            return 0;
        }
        return 1;
    }

    /**
     * Error handler for binary number has illegal numbers
     * @return Error and status 0
     */
    public static int binaryNumberErrorHandler(){
        System.out.println("Binary number contains illegal numbers");
        return 0;
    }

    /**
     * Check if the number passed in is binary
     * @param number
     * @return boolean
     */
    private static boolean isBinary(String number){
        if (number.contains("0b")){
            if (checkBinaryNumberForIllegalNumbers(number)){
                binaryNumberErrorHandler();
            }
        }
        return true;
    }

    /**
     * Check if number passed in is Hexadecimal
     * @param number
     * @return boolean 
     */
    private static boolean isHexadecimal(String number){
        return number.contains("0x");
    }

    /**
     * Check that the binary number only contains 1s or 0s
     * @param number
     * @return boolean
     */
    public static boolean checkBinaryNumberForIllegalNumbers(String number){
        String binaryNumber = number.substring(2);
        return !binaryNumber.contains("0") || !binaryNumber.contains("1");
    }

    // TODO: Finish the function to check hexadecimal number for illegal chars
    public static boolean checkHexadecialNumberForIllegalNumbersAndChars(String number){
        String[] legalNums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] legalChars = {"a", "b", "c", "d", "e", "f"};
        String hexadecimalNumber = number.substring(2);
        for (int i = 0; i < number.length(); i++){
        }
        return true;
    }

    public static void main(String[] args) {
        // 3 arguments will be entered into the command line
        checkArgumentLength(args);
        // check arguments for  which is a binary, decimal, or hexadecimal number
        
    }
}
