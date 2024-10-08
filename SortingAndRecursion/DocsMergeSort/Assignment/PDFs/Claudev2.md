# CIS232 Assignment 9: Merge Sort and Sorting Algorithm Analysis

## Introduction

This document provides a comprehensive analysis of the Merge Sort algorithm, including its implementation, pseudocode, and a comparison with other sorting 
## Table of Contents

1. [Merge Sort Overview](#merge-sort-overview)
2. [Pseudocode of Merge Sort](#pseudocode-of-merge-sort)
3. [Merge Sort Implementation](#merge-sort-implementation)
4. [Time and Space Complexity](#time-and-space-complexity)
5. [Comparison with Other Sorting Algorithms](#comparison-with-other-sorting-algorithms)
6. [Practical Applications](#practical-applications)
7. [Conclusion](#conclusion)

## Merge Sort Overview

Merge Sort is a classic divide-and-conquer algorithm used for sorting arrays or lists. It works by recursively dividing the array into smaller subarrays until each subarray contains a single element (which is inherently sorted), and then merging these subarrays to produce new sorted subarrays until the entire array is sorted.





## Merge Sort Implementation

Here is a Java implementation of the Merge Sort algorithm:

```java
public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        
        mergeSort(left);
        mergeSort(right);
        
        merge(array, left, right);
    }
    
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
```

## Time and Space Complexity

- **Time Complexity**: O(n log n) for all cases (best, average, and worst).
- **Space Complexity**: O(n) due to the temporary arrays used for merging.

Merge Sort is efficient for large datasets and is often preferred in scenarios where stability is required.

## Comparison with Other Sorting Algorithms

### Insertion Sort

- **Time Complexity**: 
  - Best Case: O(n) (when the array is already sorted)
  - Worst Case: O(n^2) (when the array is reverse sorted)
- **Space Complexity**: O(1) (in-place sorting)
- **Stability**: Yes

### Selection Sort

- **Time Complexity**: 
  - Best Case: O(n^2)
  - Worst Case: O(n^2)
- **Space Complexity**: O(1) (in-place sorting)
- **Stability**: No

### Comparison Table

| Algorithm    | Time Complexity (Best) | Time Complexity (Worst) | Space Complexity | Stable |
|--------------|------------------------|-------------------------|-------------------|--------|
| Merge Sort   | O(n log n)             | O(n log n)              | O(n)              | Yes    |
| Insertion Sort| O(n)                   | O(n^2)                  | O(1)              | Yes    |
| Selection Sort| O(n^2)                 | O(n^2)                  | O(1)              | No     |

## Practical Applications

1. **Merge Sort**:
   - External sorting of large datasets that don't fit into memory.
   - Sorting linked lists.
   - Inversion count problem.

2. **Insertion Sort**:
   - Small datasets or nearly sorted arrays.
   - Online algorithms where items are sorted as they are received.

3. **Selection Sort**:
   - Simple implementation for educational purposes.
   - When memory writes are expensive compared to reads.

## Conclusion

Understanding the Merge Sort algorithm and its characteristics is crucial for choosing the right sorting method for specific scenarios. Merge Sort offers consistent performance for large datasets, while Insertion Sort can be efficient for small or partially sorted arrays. Selection Sort, while simple, is generally less efficient but can be useful in certain memory-constrained environments.

# Code Citations
``` 
## License: unknown
https://github.com/joanjanku2000/leetcode/tree/fbf9422e8107f52558b6a4d17f90786f3cb6fecd/src/leet/merge/sort/Solution.java

```
[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    for (int i = 0; i < n1; ++i)
        leftArray[i] = array[left + i];
    for (int j = 0;
```


## License: unknown
https://github.com/azepeda2/DataStructuresReview/tree/eefd4a19c0f58c4be912fd3a400368a3d0b5e88b/MergeSort.java

```
, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    for (int i = 0; i < n1; +
```


## License: unknown
https://github.com/JavaScriptJacksn/SortManager/tree/9bdb3954b9933c1cf00a6be94d07a6074abdaad1/src/main/java/com/sparta/jjackson/sorters/MergeSorter.java

```
int[n1];
    int[] rightArray = new int[n2];

    for (int i = 0; i < n1; ++i)
        leftArray[i] = array[left + i];
    for (int j = 0; j < n2; +
```


## License: unknown
https://github.com/lucifercr07/Playground/tree/8d16e7514a7d72df7d2661999301aea7cdee8449/src/main/java/arrays/sort/MergeSort.java

```
n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    for (int i = 0; i < n1; ++i)
        leftArray[i
```


## License: unknown
https://github.com/codewarrior33/TutorialSet/tree/6471fb79805ad1364421e970c2323c458633b649/Search%26Sort/Sorting.java

```
merge(int[] array, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];
```
```
