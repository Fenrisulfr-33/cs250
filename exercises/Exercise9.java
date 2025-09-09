package cs250.exercises;

public class Exercise9 {
        public static int[] sortInts(int[] arr) {
                boolean swap = false;
                for (int i = 1; i < arr.length; i++) {
                        if (arr[i] < arr[i - 1]) {
                                swap = true;
                                int temp = arr[i];
                                arr[i] = arr[i - 1];
                                arr[i - 1] = temp;
                        }
                }
                if (swap == true) {
                        sortInts(arr);
                }
                return arr;
        }

        public String[] sortStrings(String[] arr) {
                boolean swap = false;
                for (int i = 1; i < arr.length; i++) {
                        if (arr[i - 1].compareTo(arr[i]) > 0){
                                swap = true;
                                String temp = arr[i];
                                arr[i] = arr[i - 1];
                                arr[i - 1] = temp;
                        }
                }
                if (swap == true) {
                        sortStrings(arr);
                }
                return arr;
        }

        public static void main(String[] args) {
                String[] stringArr = {"Bear", "Cow", "Ant", "Deer", "Chicken"};
                int[] intArr = {3, 2, 4, 1, 6, 5};
                // String[] sortedStringArr = sortStrings(stringArr);
                int[] sortedIntArr = sortInts(intArr);
                // System.out.println(Arrays.toString(sortedStringArr));
                // System.out.println(Arrays.toString(sortedIntArr));
        }
}