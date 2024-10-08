package mergesortgradlev1;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeSortWithEnhancedLogging {
    private static final Logger logger = Logger.getLogger(MergeSortWithEnhancedLogging.class.getName());

    public static void performMergeSort(int[] array, int startIndex, int endIndex) {
        logger.log(Level.INFO, "Sorting array segment: {0}", Arrays.toString(Arrays.copyOfRange(array, startIndex, endIndex + 1)));
        
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

        logger.log(Level.INFO, "Merging left: {0} and right: {1}", new Object[]{Arrays.toString(leftArray), Arrays.toString(rightArray)});

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

        logger.log(Level.INFO, "Merged array segment: {0}", Arrays.toString(Arrays.copyOfRange(array, startIndex, endIndex + 1)));
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 3};
        logger.log(Level.INFO, "Initial array: {0}", Arrays.toString(array));
        performMergeSort(array, 0, array.length - 1);
        logger.log(Level.INFO, "Sorted array: {0}", Arrays.toString(array));
    }
}
