package mergesortgradlev1;

import java.util.Arrays;

public class MergeSortDescp {

    /**
     * Performs the merge sort algorithm on the specified array segment.
     *
     * @param array      The array to be sorted.
     * @param startIndex The starting index of the array segment.
     * @param endIndex   The ending index of the array segment.
     */
    public static void performMergeSort(int[] array, int startIndex, int endIndex) {
        // Base case: if the segment has 1 or fewer elements, it's already sorted
        if (startIndex < endIndex) {
            int middleIndex = startIndex + (endIndex - startIndex) / 2; // Find the middle point (avoids integer
                                                                        // overflow)

            // Recursively sort the first half
            performMergeSort(array, startIndex, middleIndex); // Left half

            // Recursively sort the second half
            performMergeSort(array, middleIndex + 1, endIndex); // Right half

            // Merge the sorted halves
            mergeSortedHalves(array, startIndex, middleIndex, endIndex);
        }
    }

    /**
     * Merges two sorted subarrays of the specified array.
     *
     * @param array       The array containing the subarrays.
     * @param startIndex  The starting index of the left subarray.
     * @param middleIndex The ending index of the left subarray and the middle
     *                    point.
     * @param endIndex    The ending index of the right subarray.
     */
    public static void mergeSortedHalves(int[] array, int startIndex, int middleIndex, int endIndex) {
        int leftSize = middleIndex - startIndex + 1;
        int rightSize = endIndex - middleIndex;

        // Create temporary arrays
        int[] leftArray = Arrays.copyOfRange(array, startIndex, middleIndex + 1);
        int[] rightArray = Arrays.copyOfRange(array, middleIndex + 1, endIndex + 1);

        // Merge the temporary arrays back into array[startIndex..endIndex]
        int leftIndex = 0, rightIndex = 0, mergedIndex = startIndex;
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                array[mergedIndex++] = leftArray[leftIndex++];
            } else {
                array[mergedIndex++] = rightArray[rightIndex++];
            }
        }

        // Copy remaining elements of leftArray[] if any
        while (leftIndex < leftSize) {
            array[mergedIndex++] = leftArray[leftIndex++];
        }

        // Copy remaining elements of rightArray[] if any
        while (rightIndex < rightSize) {
            array[mergedIndex++] = rightArray[rightIndex++];
        }
    }
}
