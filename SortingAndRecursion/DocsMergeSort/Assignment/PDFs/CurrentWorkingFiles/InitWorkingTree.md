# Merge Sort Functionality and Key Components

## General Overview of Merge Sort

- Merge Sort is a *divide-and-conquer* algorithm used for sorting arrays or lists of large data sets.
 - This works well for large data sets as it has a time complexity of O(n log n), which is faster than O(n^2) for large datasets. 
 - An example of this is the use of Merge Sort in the Java Collections Framework, where it is used to sort lists of large data sets. This is the Arraylist.sort() method as well as the Collections.sort() method. 
 - It works by recursively dividing the array into smaller subarrays until each subarray contains a single element. This division is crucial as it simplifies the sorting process, allowing for easier merging later on.
- Once the array is divided, the algorithm begins the merging process, where it combines the subarrays back together in a sorted manner. This merging continues until the entire array is sorted.
- Merge Sort is renowned for its efficiency and predictable performance, making it a popular choice for various applications. It operates with a time complexity of O(n log n) in the average and worst cases, which is significantly better than simpler algorithms like Bubble Sort or Insertion Sort, especially for large datasets.
- One of the key advantages of Merge Sort is its *stability*, meaning that it maintains the relative order of equal elements, which is essential in certain applications where the order of similar items matters.
- Additionally, Merge Sort is well-suited for linked lists and can be implemented in a way that requires minimal additional space, making it a versatile choice for different data structures.
- Its predictable performance and stability make Merge Sort a preferred algorithm in various applications, including sorting large datasets, external sorting (where data is too large to fit into memory), and in scenarios where consistent performance is critical.

 Here's how it typically operates:

1. **Initial Call**: The `mergeSort` function is called with the entire array. For example, if the array is `[38, 27, 43, 3, 9, 82, 10]`, the initial call is `mergeSort(arr, 0, 6)`.

2. **Dividing the Array**: The array is divided into two halves:
   - The left half: `mergeSort(arr, 0, mid)` where `mid` is the midpoint of the array.
   - The right half: `mergeSort(arr, mid + 1, right)`.

3. **Recursive Division**: This process continues recursively:
   - Each call to `mergeSort` further divides the subarrays until the base case is reached (when the subarray has one element).
   - For example, the array `[38, 27, 43, 3, 9, 82, 10]` will be divided into:
     - `[38, 27, 43]` and `[3, 9, 82, 10]`
     - Then `[38, 27]` and `[43]`. As well as `[3, 9]` and `[82, 10]`. 
     - `[38]` and `[27]` are next. Then `[3]` and `[9]`. Finally `[82]` and `[10]`.

4. **Order of Subarrays**: The order of subarrays created during this process is systematic and follows a natural order based on the indices:
   - The left subarray is always processed before the right subarray.
   - The order of elements within each subarray is preserved from the original array until the merging step.

5. **Merging**: Once the base case is reached, the merging process begins:
   - The subarrays are merged back together in sorted order.
   - For example, merging `[38]` and `[27]` results in `[27, 38]`, and then merging this with `[43]` results in `[27, 38, 43]`.

6. **Final Order**: The final merged array will be sorted in ascending order, as the merging process ensures that elements are combined in a way that maintains order.

### Summary of basic process
- **Creation of Subarrays**: Subarrays are created in a systematic, natural order based on the original array's indices. 
- **Order**: Initially, the order of elements within each subarray reflects their position in the original array. The final order after merging is sorted.

#### Key concepts 
- Stability: Merge Sort maintains the relative order of equal elements, which is crucial for maintaining the relative order of equal elements in the sorted array.


## Technical Analysis of Overview

### Ordering of Subarrays

During the merge process, the order of elements in the subarrays is determined by comparing the elements of the two sorted subarrays. The algorithm ensures that the smallest elements are placed first in the merged array, maintaining the overall sorted order.

1. **Comparison Logic:**
   - The `mergeSortedHalves` method is responsible for merging two sorted subarrays. It uses a while loop to compare the elements of the left and right subarrays:
   ```java
   while (leftIndex < leftSize && rightIndex < rightSize) {
       if (leftArray[leftIndex] <= rightArray[rightIndex]) {
           array[mergedIndex++] = leftArray[leftIndex++];
       } else {
           array[mergedIndex++] = rightArray[rightIndex++];
       }
   }
   ```
   - In this snippet, the algorithm checks if the current element in the left subarray (`leftArray[leftIndex]`) is less than or equal to the current element in the right subarray (`rightArray[rightIndex]`). If true, it places the left element into the main array and increments the respective index.

2. **Handling Remaining Elements:**
   - After the main comparison loop, any remaining elements in either subarray are copied to the main array:
   ```java
   while (leftIndex < leftSize) {
       array[mergedIndex++] = leftArray[leftIndex++];
   }
   while (rightIndex < rightSize) {
       array[mergedIndex++] = rightArray[rightIndex++];
   }
   ```
   - This ensures that if one subarray is exhausted before the other, the remaining elements are added in their original order, preserving the stability of the sort.

### Conclusion

The ordering of subarrays in Merge Sort is achieved through systematic comparisons and merging of elements, ensuring that the final array is sorted. The algorithm's design allows it to efficiently handle large datasets while maintaining stability, making it a robust choice for various sorting tasks.

## Code of Classic Merge Sort

```java
public static void mergeSort(int[] array) {
    if (array == null || array.length <= 1) {
        return;
    }
    int[] tempArray = new int[array.length];
    mergeSortHelper(array, tempArray, 0, array.length - 1);
}

private static void mergeSortHelper(int[] array, int[] tempArray, int left, int right) {
    if (left < right) {
        int middle = left + (right - left) / 2;
        mergeSortHelper(array, tempArray, left, middle);
        mergeSortHelper(array, tempArray, middle + 1, right);
        merge(array, tempArray, left, middle, right);
    }
}

private static void merge(int[] array, int[] tempArray, int left, int middle, int right) {
    for (int i = left; i <= right; i++) {
        tempArray[i] = array[i];
    }
    int i = left;
    int j = middle + 1;
    int k = left;
    while (i <= middle && j <= right) {
        if (tempArray[i] <= tempArray[j]) {
            array[k++] = tempArray[i++];
        } else {
            array[k++] = tempArray[j++];
        }
    }
    while (i <= middle) {
        array[k++] = tempArray[i++];  
    }
}
```

### Key Characteristics
- **Stable**: Maintains the relative order of equal elements.
- **Consistent Performance**: O(n log n) time complexity for all cases.
- **External Sorting**: Efficient for large datasets that do not fit into memory.

- The stability of Merge Sort means that equal elements retain their original order, which is crucial for maintaining the relative order of equal elements in the sorted array.
- The consistency of Merge Sort ensures that its performance is predictable and reliable, making it a valuable tool for sorting tasks in various applications. This is highlighted by its consistent time complexity of O(n log n), which is efficient and reliable for sorting large datasets.

## Key Components of the Merge Sort Algorithm with provided analysis and alterations 

### 1. `performMergeSort` Method

The `performMergeSort` method is the core of the Merge Sort algorithm. It recursively divides the array into smaller segments, sorts them, and then merges the sorted segments.

```java
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
        int middleIndex = startIndex + (endIndex - startIndex) / 2; // Find the middle point (avoids integer overflow)

        // Recursively sort the first half
        performMergeSort(array, startIndex, middleIndex); // Left half

        // Recursively sort the second half
        performMergeSort(array, middleIndex + 1, endIndex); // Right half

        // Merge the sorted halves
        mergeSortedHalves(array, startIndex, middleIndex, endIndex);
    }
}
```
**Explanation:**

- **Base Case:** The method first checks if the current segment of the array has more than one element (`startIndex < endIndex`). If not, the segment is already sorted.
  
- **Finding the Middle:** It calculates the `middleIndex` to divide the array into two halves. Using `startIndex + (endIndex - startIndex) / 2` prevents potential integer overflow.
  
- **Recursive Sorting:** The method recursively calls itself to sort the left half (`startIndex` to `middleIndex`) and the right half (`middleIndex + 1` to `endIndex`).
  
- **Merging:** After sorting both halves, it calls the `mergeSortedHalves` method to merge the two sorted subarrays into a single sorted segment.


### 2. `mergeSortedHalves` Method

The `mergeSortedHalves` method is responsible for merging two sorted subarrays into a single sorted segment. It ensures that the combined array maintains the sorted order.

### `mergeSortedHalves` Method

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
**Explanation:**

- **Determining Sizes:** The method calculates the sizes of the left and right subarrays (`leftSize` and `rightSize`) based on the provided indices.
  
- **Creating Temporary Arrays:** It creates temporary arrays `leftArray` and `rightArray` by copying the relevant segments from the original array using `Arrays.copyOfRange`.
  
- **Merging Process:**
  - Initializes three pointers: `leftIndex`, `rightIndex`, and `mergedIndex` to track positions in the left array, right array, and the main array, respectively.
  - Iterates through both temporary arrays, comparing elements and placing the smaller one into the main array.
  
- **Copying Remaining Elements:** After the main loop, if there are any remaining elements in either `leftArray` or `rightArray`, they are copied into the main array to complete the merge.

## Time and Space Complexity of Merge Sort

### Time Complexity: **O(n log n)**

- **Divide Phase:** 
  - The array is recursively divided into two halves until each subarray has only one element.
  - The depth of the recursion tree is `log n`, where `n` is the number of elements in the array.

- **Conquer Phase (Merging):**
  - At each level of recursion, the merging process takes linear time `O(n)` since each element is compared and merged.
  
- **Overall Complexity:**
  - Combining both phases, the total time complexity becomes `O(n log n)` for all cases (best, average, and worst).

### Space Complexity: **O(n)**

- **Auxiliary Space for Temporary Arrays:**
  - The algorithm uses temporary arrays (`leftArray` and `rightArray`) to store the divided segments during the merge process.
  - At any point, the total size of these temporary arrays is proportional to the size of the input array, resulting in `O(n)` space.
  
- **Recursive Call Stack:**
  - The depth of the recursion stack is `log n`, and each recursive call consumes constant space.
  - Therefore, the space used by the call stack is `O(log n)`.
  
- **Dominant Factor:**
  - The auxiliary space for temporary arrays (`O(n)`) dominates the space used by the call stack (`O(log n)`), making the overall space complexity `O(n)`.

**Conclusion:**

The Merge Sort algorithm offers a consistent and efficient sorting mechanism with a time complexity of `O(n log n)` and a space complexity of `O(n)`. Its divide-and-conquer approach makes it highly effective for large datasets, and the algorithm's stability ensures that equal elements retain their original order post-sorting.


### `performMergeSort` Method

```java
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
        int middleIndex = startIndex + (endIndex - startIndex) / 2; // Find the middle point (avoids integer overflow)

        // Recursively sort the first half
        performMergeSort(array, startIndex, middleIndex); // Left half

        // Recursively sort the second half
        performMergeSort(array, middleIndex + 1, endIndex); // Right half

        // Merge the sorted halves
        mergeSortedHalves(array, startIndex, middleIndex, endIndex);
    }
}
```

**Explanation:**

- **Base Case:** The method first checks if the current segment of the array has more than one element (`startIndex < endIndex`). If not, the segment is already sorted.
  
- **Finding the Middle:** It calculates the `middleIndex` to divide the array into two halves. Using `startIndex + (endIndex - startIndex) / 2` prevents potential integer overflow.
  
- **Recursive Sorting:** The method recursively calls itself to sort the left half (`startIndex` to `middleIndex`) and the right half (`middleIndex + 1` to `endIndex`).
  
- **Merging:** After sorting both halves, it calls the `mergeSortedHalves` method to merge the two sorted subarrays into a single sorted segment.

### `mergeSortedHalves` Method

```java:app/src/main/java/mergesortgradlev1/MergeSortClaudeAlterV1.java
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

**Explanation:**

- **Determining Sizes:** The method calculates the sizes of the left and right subarrays (`leftSize` and `rightSize`) based on the provided indices.
  
- **Creating Temporary Arrays:** It creates temporary arrays `leftArray` and `rightArray` by copying the relevant segments from the original array using `Arrays.copyOfRange`.
  
- **Merging Process:**
  - Initializes three pointers: `leftIndex`, `rightIndex`, and `mergedIndex` to track positions in the left array, right array, and the main array, respectively.
  - Iterates through both temporary arrays, comparing elements and placing the smaller one into the main array.
  
- **Copying Remaining Elements:** After the main loop, if there are any remaining elements in either `leftArray` or `rightArray`, they are copied into the main array to complete the merge.

## Time and Space Complexity of Merge Sort

### Time Complexity: **O(n log n)**

- **Divide Phase:** 
  - The array is recursively divided into two halves until each subarray has only one element.
  - The depth of the recursion tree is `log n`, where `n` is the number of elements in the array.

- **Conquer Phase (Merging):**
  - At each level of recursion, the merging process takes linear time `O(n)` since each element is compared and merged.
  
- **Overall Complexity:**
  - Combining both phases, the total time complexity becomes `O(n log n)` for all cases (best, average, and worst).

### Space Complexity: **O(n)**

- **Auxiliary Space for Temporary Arrays:**
  - The algorithm uses temporary arrays (`leftArray` and `rightArray`) to store the divided segments during the merge process.
  - At any point, the total size of these temporary arrays is proportional to the size of the input array, resulting in `O(n)` space.
  
- **Recursive Call Stack:**
  - The depth of the recursion stack is `log n`, and each recursive call consumes constant space.
  - Therefore, the space used by the call stack is `O(log n)`.
  
- **Dominant Factor:**
  - The auxiliary space for temporary arrays (`O(n)`) dominates the space used by the call stack (`O(log n)`), making the overall space complexity `O(n)`.

