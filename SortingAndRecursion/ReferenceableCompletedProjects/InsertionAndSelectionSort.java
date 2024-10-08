package ReferenceableCompletedProjects;

import java.util.Arrays;

/**
 * This class provides implementations of the Insertion Sort and Selection Sort
 * algorithms.
 * It includes methods to sort an array of integers using these algorithms,
 * along with detailed
 * logging of the sorting process for educational purposes.
 */

public class InsertionAndSelectionSort {

    /**
     * Sorts the given array using the Insertion Sort algorithm.
     *
     * @param array the array to be sorted
     * @throws IllegalArgumentException if the array is null
     *
     *                                  The Insertion Sort algorithm sorts an array
     *                                  by building a sorted subarray one element at
     *                                  a time.
     *                                  It iterates through the array, selecting
     *                                  each element (key) and inserting it into the
     *                                  correct
     *                                  position in the sorted subarray. The method
     *                                  prints the key selected and the state of the
     *                                  array
     *                                  after each insertion for debugging purposes.
     */

    public static void insertionSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null"); // throws an exception if the array is null
        }
        int n = array.length; // sets n to the length of the array

        for (int i = 1; i < n; i++) { // sets i as 1, sets the condition for i to stop when it reaches n, which is the
                                      // total length of the array, and sets the increment of i by 1
            int key = array[i]; // sets key as the value of the element at the current index of i
            int j = i - 1; // sets j as i-1, which is the element one index behind i

            // This code segment selects the current element to be inserted into the sorted
            // subarray. The variable `key` holds the value to be inserted, while `j`
            // represents the index of the last element in the sorted subarray.

            System.out.println("Insertion Sort - Key selected: " + key);

            while (j >= 0 && array[j] > key) {// A while loop is to run that will continue to run as long as the index
                                              // of j is greater than or equal to zero and the value of the element at
                                              // the current index of j is greater than the value of the key. i.e if
                                              // it's in the incorrect natural order.
                array[j + 1] = array[j]; // sets the value of the element at the current index of j to the value of the
                                         // element at the current index of j+1. This effectively shifts the element at
                                         // the current index of j to the right by one index to the current index value
                                         // of the key.
                // This loop shifts elements encountered that greater than `key` to the right
                // until the condition of the while loop is no longer met.
                // This means that the key value

                j = j - 1; // decrements j by 1, this serves to ensure that the while loop will continue
                           // from the previous index of j until the condition of the while loop is no
                           // longer met.
                // The while continues until an element less than or equal to `key` is found
                //

                System.out.println("Insertion Sort - Array after shifting: " + Arrays.toString(array));
            }
            array[j + 1] = key;
            System.out.println("Insertion Sort - Array after inserting key: " + Arrays.toString(array));
        }
    }

    /**
     * /**
     * Sorts the given array using the Selection Sort algorithm.
     *
     * @param array the array to be sorted
     * @throws IllegalArgumentException if the array is null
     *
     *                                  The Selection Sort algorithm sorts an array
     *                                  by repeatedly finding the minimum element
     *                                  from the
     *                                  unsorted part and putting it at the
     *                                  beginning. It uses two nested loops to
     *                                  iterate through the
     *                                  array and swap elements as necessary. The
     *                                  method prints the starting index, current
     *                                  minimum index,
     *                                  and the state of the array at each step of
     *                                  the sorting process for debugging purposes.
     *
     *                                  The selection sort algorithm sorts an array
     *                                  by repeatedly finding the minimum element
     *                                  (considering ascending order) from the
     *                                  unsorted part and putting it at the
     *                                  beginning.
     *                                  It uses two nested loops to iterate through
     *                                  the array and swap the elements according to
     *                                  whether or not the values of the two
     *                                  elements are greater or less than each
     *                                  other.
     *                                  If they are in the incorrect natural order,
     *                                  which is not in ascending order, the
     *                                  elements at the current index of the nested
     *                                  loop and the minimum index of the outer loop
     *                                  are swapped.
     * 
     *                                  1. The subarray which is already sorted.
     *                                  2. The remaining subarray which is unsorted.
     * 
     *                                  In every iteration of the selection sort,
     *                                  the minimum element (considering ascending
     *                                  order)
     *                                  from the unsorted subarray is picked and
     *                                  moved to the sorted subarray.
     * 
     *                                  The method prints the starting index, the
     *                                  current minimum index, and the array at each
     *                                  step
     *                                  of the sorting process for debugging
     *                                  purposes.
     */
    public static void selectionSort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null"); // throws an exception if the array is null
        }
        int n = array.length; // sets n to the length of the array
        for (int i = 0; i < n - 1; i++) { // sets i as zero, sets the condition for i to stop when it reaches n-1, which
                                          // is the last index of the zero indexed array, and sets the increment of i by
                                          // 1
            int minIndex = i;// sets i as minimum index value of the array,
            boolean swapped = false; // boolean to check if a swap has occurred
            System.out.println("Selection Sort - Starting index: " + i); // prints the starting index value of the
                                                                         // array, which is always zero in the first
                                                                         // logging since the loop is only in the first
                                                                         // iteration

            for (int j = i + 1; j < n; j++) { // sets j as i+1, which is the element one index ahead of i, sets the
                                              // condition for j to stop when it reaches the value of n, which is the
                                              // total length of the array. This is set to this value because j is an
                                              // index value ahead of i, so it will reach the last index of the array
                                              // ahead of i,
                                              // and sets the increment of j by 1
                if (array[j] < array[minIndex]) { // condition to check if the value of the element at the current index
                                                  // of j is less than the value of the element at the minimum index.
                                                  // This will be true if the value of the element at the current index
                                                  // of j is less than the value of the element at the minimum index
                    minIndex = j; // sets the index of j as the new minimum index since the value of array[j]
                                  // ahead of the minimum index array[i] is less than the value at the minimum
                                  // index
                    swapped = true; // set swapped to true since a new minimum was found
                    // Since the element ahead of the minimum index is less than the element at the
                    // minimum index, the index values of the two elements are swapped to be in
                    // ascending order, and the swapped boolean is set to true.
                }
                System.out.println(
                        "Selection Sort - Current j: " + j + ", Current minIndex: " + minIndex + ", Current array: "
                                + Arrays.toString(array));
            }

            if (swapped) { // only triggers if a swap has occurred
                int temp = array[minIndex]; // sets the value of the element at the minimum index to a temporary
                                            // variable
                array[minIndex] = array[i]; // sets the value of the element at the minimum index to the value of the
                                            // element at the current index of i since the origional value of the
                                            // element at the minimum index was stored in the temporary variable
                array[i] = temp; // sets the value of the element at array[i] as the temperatory storage
                                 // variable, which is the value that was swapped and pulled from the j index.
                System.out.println("Selection Sort - Swapped elements at indices " + i + " and " + minIndex + ": "
                        + Arrays.toString(array)); // prints the swapped elements at the indices of i and minIndex
            }
        }
    }

    public void insertionSortInstance(int[] array) {
        insertionSort(array);
    }

    public void selectionSortInstance(int[] array) {
        selectionSort(array);
    }

    public static void analyzeTimeComplexity() {
        System.out.println("Insertion Sort: O(n^2) in the worst case, O(n) in the best case.");
        System.out.println("Selection Sort: O(n^2) in all cases.");
    }

    public static void main(String[] args) {
        int[] array1 = { 5, 2, 8, 1, 3 };
        System.out.println("Original Array for Insertion Sort: " + Arrays.toString(array1));
        insertionSort(array1);
        System.out.println("Sorted Array using Insertion Sort: " + Arrays.toString(array1));

        int[] array2 = { 5, 2, 8, 1, 3 };
        System.out.println("Original Array for Selection Sort: " + Arrays.toString(array2));
        selectionSort(array2);
        System.out.println("Sorted Array using Selection Sort: " + Arrays.toString(array2));

        int[] staticArray1 = { 5, 2, 8, 1, 3 };
        System.out.println("Static Insertion Sort:");
        insertionSort(staticArray1);
        System.out.println(Arrays.toString(staticArray1));

        int[] staticArray2 = { 5, 2, 8, 1, 3 };
        System.out.println("Static Selection Sort:");
        selectionSort(staticArray2);
        System.out.println(Arrays.toString(staticArray2));

        InsertionAndSelectionSort sorter = new InsertionAndSelectionSort();
        int[] instanceArray1 = { 5, 2, 8, 1, 3 };
        System.out.println("Instance Insertion Sort:");
        sorter.insertionSortInstance(instanceArray1);
        System.out.println(Arrays.toString(instanceArray1));

        int[] instanceArray2 = { 5, 2, 8, 1, 3 };
        System.out.println("Instance Selection Sort:");
        sorter.selectionSortInstance(instanceArray2);
        System.out.println(Arrays.toString(instanceArray2));

        analyzeTimeComplexity();
    }
}
