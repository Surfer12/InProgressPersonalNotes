### Merge Sort and AI Tool Usage

## Table of Contents
1. Introduction
2. [Merge Sort: Algorithm Breakdown](#merge-sort-algorithm-breakdown)
3. [Comparative Analysis](#comparative-analysis)
4. Conclusion

## Introduction

This document provides a detailed technical analysis of the Merge Sort algorithm. The analysis will focus on key components of the algorithm, utilizing code snippets to illustrate crucial aspects of its implementation and operation. Additionally, the document will include insights gained from using an AI tool to explain the algorithm and its complexities.

## Merge Sort: Algorithm Breakdown

Merge Sort is a divide-and-conquer algorithm that divides the input array into two halves, recursively sorts each half, and then merges the sorted halves to produce the final sorted array.

### Key Components

#### Divide Step

```java
int mid = (left + right) / 2;
mergeSort(array, left, mid);
mergeSort(array, mid + 1, right);
```

This code segment divides the array into two halves. The `mid` index is calculated, and the `mergeSort` function is called recursively on each half.

#### Merge Function

```java
void merge(int[] array, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    for (int i = 0; i < n1; ++i)
        leftArray[i] = array[left + i];
    for (int j = 0; j < n2; ++j)
        rightArray[j] = array[mid + 1 + j];

    int i = 0, j = 0;
    int k = left;
    while (i < n1 && j < n2) {
        if (leftArray[i] <= rightArray[j]) {
            array[k] = leftArray[i];
            i++;
        } else {
            array[k] = rightArray[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        array[k] = leftArray[i];
        i++;
        k++;
    }

    while (j < n2) {
        array[k] = rightArray[j];
        j++;
        k++;
    }
}
```

This function merges two sorted subarrays into a single sorted array. It uses temporary arrays to hold the elements of the left and right subarrays, then merges them back into the original array.

### Complete Algorithm

```java
void mergeSort(int[] array, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }
}
```

This complete function demonstrates how the array is recursively divided and merged to achieve the final sorted array.

## Comparative Analysis

### Time Complexity

- Best Case: O(n log n)
- Worst Case: O(n log n)
- Average Case: O(n log n)

### Space Complexity

- Merge Sort has a space complexity of O(n) due to the additional temporary arrays used during the merge process.

### Key Differences from Other Algorithms

1. **Adaptivity**:
   - Merge Sort is not adaptive; it performs consistently regardless of the initial order of the array.

2. **Stability**:
   - Merge Sort is stable, preserving the relative order of equal elements.

3. **Number of Swaps**:
   - Merge Sort does not perform swaps in the traditional sense but rather merges elements, which can be more efficient in certain scenarios.

## Conclusion

This technical analysis has provided a detailed examination of the Merge Sort algorithm, focusing on its key components and operational characteristics. Merge Sort is efficient for large datasets due to its O(n log n) time complexity and stability. However, its O(n) space complexity can be a drawback for memory-constrained environments.

## AI Tool Usage

### Explanation of Merge Sort

Using an AI tool, we prompted it to explain the Merge Sort algorithm and its merge function in detail. The AI provided a clear breakdown of the divide-and-conquer approach, the recursive nature of the algorithm, and the merging process.

### Time and Space Complexity

The AI elaborated on the time complexity of O(n log n) for all cases and the space complexity of O(n) due to the additional temporary arrays.

### Thoughts on AI Explanations

The AI tool provided accurate and detailed explanations, which were helpful in understanding the intricacies of the Merge Sort algorithm. The ability to ask follow-up questions and receive clarifications further enhanced the learning experience.

### Submission

- **Screenshots**: Captured screenshots of the prompts and AI's answers.
- **Brief Paragraph**: Written a brief paragraph expressing thoughts on the AI's explanations.
- **Files**: Named the files appropriately as [`CIS232_firstname_lastname`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FInProgressClassProjects%2FMergeSortGradleV1%2FDocs%2FAssignment%2FPDFs%2FAssignment9CompleteInstructions.txt%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A22%2C%22character%22%3A35%7D%7D%5D%2C%22b41a8ddf-32ee-4570-a94a-772cc5eeac2d%22%5D "Go to definition").

This document serves as a comprehensive analysis of the Merge Sort algorithm, incorporating insights from AI tool usage and adhering to best practices in technical documentation.

Similar code found with 1 license type