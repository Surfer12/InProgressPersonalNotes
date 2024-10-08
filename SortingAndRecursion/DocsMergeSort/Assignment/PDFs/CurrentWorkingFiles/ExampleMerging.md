```java
/**
 * Merges two sorted subarrays of the specified array.
 *
 * @param array       The array containing the subarrays.
 * @param startIndex  The starting index of the left subarray.
 * @param middleIndex The ending index of the left subarray and the middle point.
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
```

**Example Breakdown:**

Let's say we have the following array:

```
[38, 27, 43, 3, 9, 82, 10]
```

After the recursive division, we have two sorted subarrays:

```
leftArray: [27, 38, 43]
rightArray: [3, 9, 10, 82]
```

Now, the `mergeSortedHalves` method will merge these two subarrays back into the original array.

**Step 1: Initialization**

- `leftIndex = 0`, `rightIndex = 0`, `mergedIndex = 0`

**Step 2: Merging Loop**

- **Iteration 1:**
    - `leftArray[leftIndex] = 27`, `rightArray[rightIndex] = 3`
    - `27 > 3`, so `array[mergedIndex++] = rightArray[rightIndex++]`
    - `array[0] = 3`, `leftIndex = 0`, `rightIndex = 1`, `mergedIndex = 1`

- **Iteration 2:**
    - `leftArray[leftIndex] = 27`, `rightArray[rightIndex] = 9`
    - `27 > 9`, so `array[mergedIndex++] = rightArray[rightIndex++]`
    - `array[1] = 9`, `leftIndex = 0`, `rightIndex = 2`, `mergedIndex = 2`

- **Iteration 3:**
    - `leftArray[leftIndex] = 27`, `rightArray[rightIndex] = 10`
    - `27 > 10`, so `array[mergedIndex++] = rightArray[rightIndex++]`
    - `array[2] = 10`, `leftIndex = 0`, `rightIndex = 3`, `mergedIndex = 3`

- **Iteration 4:**
    - `leftArray[leftIndex] = 27`, `rightArray[rightIndex] = 82`
    - `27 < 82`, so `array[mergedIndex++] = leftArray[leftIndex++]`
    - `array[3] = 27`, `leftIndex = 1`, `rightIndex = 3`, `mergedIndex = 4`

- **Iteration 5:**
    - `leftArray[leftIndex] = 38`, `rightArray[rightIndex] = 82`
    - `38 < 82`, so `array[mergedIndex++] = leftArray[leftIndex++]`
    - `array[4] = 38`, `leftIndex = 2`, `rightIndex = 3`, `mergedIndex = 5`

- **Iteration 6:**
    - `leftArray[leftIndex] = 43`, `rightArray[rightIndex] = 82`
    - `43 < 82`, so `array[mergedIndex++] = leftArray[leftIndex++]`
    - `array[5] = 43`, `leftIndex = 3`, `rightIndex = 3`, `mergedIndex = 6`

**Step 3: Handling Remaining Elements**

- `leftIndex = 3`, `rightIndex = 3`, `mergedIndex = 6`
- `leftIndex >= leftSize`, so the loop terminates.
- `rightIndex < rightSize`, so the remaining element in `rightArray` is copied to `array`:
    - `array[6] = 82`

**Final Result:**

The merged array is now:

```
[3, 9, 10, 27, 38, 43, 82]
```

This is the sorted version of the original array.
