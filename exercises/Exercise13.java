package cs250.exercises;

import java.util.ArrayList;
import java.util.LinkedList;

public class Exercise13 {
    public double[][] compareLists(int[] arr, int searchVal) {
        double[][] results = {
                { 0.0, 0.0, 0.0 },
                { 0.0, 0.0, 0.0 },
                { 0.0, 0.0, 0.0 },
        };
        // create and array list over 10k values
        ArrayList<Integer> arrayList = new ArrayList<>();
        // create a linked list over 10k values
        // Linked list do not have a size as a data type
        LinkedList<Integer> linkedList = new LinkedList<>();
        // fill each list with the values from the arr
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
            linkedList.add(arr[i]);
        }

        results[0][0] = arrayContains(arr, searchVal);
        results[0][1] = arrayRemove(arr, searchVal);
        results[0][2] = arrayAdd(arr, searchVal);
        results[1][0] = arrayListContains(arrayList, searchVal);
        results[1][1] = arrayListRemove(arrayList, searchVal);
        results[1][2] = arrayListAdd(arrayList, searchVal);
        results[2][0] = linkedListContains(linkedList, searchVal);
        results[2][1] = linkedListRemove(linkedList, searchVal);
        results[2][2] = linkedListAdd(linkedList, searchVal);

        return results;
    }

    public double arrayContains(int[] array, int searchVal) {
        // Check if the list contains the value for searchVal
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchVal) {
                break;
            }
        }
        long endTime = System.nanoTime();
        // return the value as a double in seconds
        return (endTime - startTime) / 1000000000.0;
    }

    public double arrayRemove(int[] array, int searchVal) {
        int[] newArray = new int[array.length - 1];
        // implement a new index for newArray since it won't match after the delete
        int newArrayIndex = 0;
        // Remove searchVal from the list
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchVal) {
                continue;
            } else {
                newArray[newArrayIndex++] = array[i];
            }
            
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public double arrayAdd(int[] array, int searchVal) {
        int[] newArray = new int[array.length + 1];
        // Remove searchVal from the list
        long startTime = System.nanoTime();
        newArray[0] = searchVal;
        for (int i = 1; i < array.length; i++) {
            newArray[i] = array[i];
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public double arrayListContains(ArrayList<Integer> arrayList, int searchVal) {
        // Check if the list contains the value for searchVal
        long startTime = System.nanoTime();
        arrayList.contains(searchVal);
        long endTime = System.nanoTime();
        // return the value as a double in seconds
        return (endTime - startTime) / 1000000000.0;
    }

    public double arrayListRemove(ArrayList<Integer> arrayList, int searchVal) {
        // Remove searchVal from the list
        long startTime = System.nanoTime();
        arrayList.remove(searchVal);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public double arrayListAdd(ArrayList<Integer> arrayList, int searchVal) {
        // Remove searchVal from the list
        long startTime = System.nanoTime();
        arrayList.addFirst(searchVal);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public double linkedListContains(LinkedList<Integer> LinkedList, int searchVal) {
        // Check if the list contains the value for searchVal
        long startTime = System.nanoTime();
        LinkedList.contains(searchVal);
        long endTime = System.nanoTime();
        // return the value as a double in seconds
        return (endTime - startTime) / 1000000000.0;
    }

    public double linkedListRemove(LinkedList<Integer> LinkedList, int searchVal) {
        // Remove searchVal from the list
        long startTime = System.nanoTime();
        LinkedList.remove(searchVal);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public double linkedListAdd(LinkedList<Integer> LinkedList, int searchVal) {
        // Remove searchVal from the list
        long startTime = System.nanoTime();
        LinkedList.addFirst(searchVal);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
    }

}
