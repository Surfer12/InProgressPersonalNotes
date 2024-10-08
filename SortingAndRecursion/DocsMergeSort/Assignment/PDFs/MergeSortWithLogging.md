package mergesortgradlev1;

import java.util.Arrays;
import java.util.logging.Logger;

public class MergeSortWithLogging {
    private static final Logger logger = Logger.getLogger(MergeSortWithLogging.class.getName());

    public static void performMergeSort(int[] array, int startIndex, int endIndex) {
        logger.info("Sorting array segment: " + Arrays.toString(Arrays.copyOfRange(array, startIndex, endIndex + 1)));
        
        if (startIndex < endIndex) {
            int middleIndex = startIndex + (endIndex - startIndex) / 2;

            // Recursively sort the first half
            performMergeSort(array, startIndex, middleIndex);

            // Recursively sort the second half
            performMergeSort(array, middleIndex + 1, endIndex);

            // Merge the sorted halves
            mergeSortedHalves(array, startIndex, middleIndex, endIndex);
        }
    }

    public static void mergeSortedHalves(int[] array, int startIndex, int middleIndex, int endIndex) {
        int leftSize = middleIndex - startIndex + 1;
        int rightSize = endIndex - middleIndex;

        int[] leftArray = Arrays.copyOfRange(array, startIndex, middleIndex + 1);
        int[] rightArray = Arrays.copyOfRange(array, middleIndex + 1, endIndex + 1);

        logger.info("Merging left: " + Arrays.toString(leftArray) + " and right: " + Arrays.toString(rightArray));

        int leftIndex = 0, rightIndex = 0, mergedIndex = startIndex;
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                array[mergedIndex++] = leftArray[leftIndex++];
            } else {
                array[mergedIndex++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < leftSize) {
            array[mergedIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightSize) {
            array[mergedIndex++] = rightArray[rightIndex++];
        }

        logger.info("Merged array segment: " + Arrays.toString(Arrays.copyOfRange(array, startIndex, endIndex + 1)));
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 3};
        logger.info("Initial array: " + Arrays.toString(array));
        performMergeSort(array, 0, array.length - 1);
        logger.info("Sorted array: " + Arrays.toString(array));
    }
}
