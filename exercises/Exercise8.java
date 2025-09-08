package cs250.exercises;

public class Exercise8 {
        public static void main(String[] args) {
                try {
                        Integer a = Integer.valueOf(Integer.parseInt(args[0]));
                        Integer b = Integer.valueOf(Integer.parseInt(args[1]));
                        Integer c = Integer.valueOf(Integer.parseInt(args[2]));
                        if (a > 100 || b > 100 || c > 100)
                                throw new IllegalArgumentException("Args must be less than or equal to 100!");
                        Integer sum = a + b + c;
                        Integer d = sum < 100 ? null : sum;
                        Object value = d < 150 ? (d / c / b / a) : "CORRECT";
                        String valStr = (String) value;

                        System.out.println(valStr);
                } catch (ArrayIndexOutOfBoundsException error) {
                        System.out.println(error.getClass().getSimpleName());
                        System.out.println(error.getMessage());
                } catch (NumberFormatException error) {
                        System.out.println(error.getClass().getSimpleName());
                        System.out.println(error.getMessage());
                } catch (NullPointerException error) {
                        System.out.println(error.getClass().getSimpleName());
                        System.out.println(error.getMessage());
                } catch (ArithmeticException error) {
                        System.out.println(error.getClass().getSimpleName());
                        System.out.println(error.getMessage());
                } catch (ClassCastException error) {
                        System.out.println(error.getClass().getSimpleName());
                        System.out.println(error.getMessage());
                } catch (IllegalArgumentException error) {
                        System.out.println(error.getClass().getSimpleName());
                        System.out.println(error.getMessage());
                } finally {
                        System.out.println("END");
                }
        }
}