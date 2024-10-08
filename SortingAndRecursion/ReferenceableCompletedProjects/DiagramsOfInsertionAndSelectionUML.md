# UML and Sequence Diagrams for Insertion and Selection Sort

### UML Diagram for Insertion Sort

#### Output Log of the Insertion Sort Algorithm
```java 
int [] anArray = [5, 2, 8, 1, 3]

insertionSort(anArray)

Output Log:
Original Array for Insertion Sort: [5, 2, 8, 1, 3] // j is set to five and key is set to two 
Insertion Sort - Key selected: 2
Insertion Sort - Array after shifting: [5, 5, 8, 1, 3] // while loop encounters the condition array[j] > key, so the value of the element at the current index of j is set to the value of the element at the current index of j+1. This effectively shifts the element at the current index of j to the right by one index to the current index value of the key. 
// Two is shifted to the left and five is shifted to the right.
Insertion Sort - Array after inserting key: [2, 5, 8, 1, 3] // the while loop ends and the value of the key is set to the value of the element at the current index of j+1. This completes the first incteration of the outer loop and i is incremented by one.
Insertion Sort - Key selected: 8 // The next key is selected and the inner loop condition is checked.
Insertion Sort - Array after inserting key: [2, 5, 8, 1, 3] // The inner loop condition is not met, since key is great than the value of the element at the current index of j, and the inner loop ends.
Insertion Sort - Key selected: 1 // The next key is selected and the inner loop condition is checked.
Insertion Sort - Array after shifting: [2, 5, 8, 8, 3] // Since the value of the key is less than the value of the element at the current index of j, the value of the element at the current index of j is set to the value of the element at the current index of j+1. This effectively shifts the element at the current index of j to the right by one index to the current index value of the key.
Insertion Sort - Array after shifting: [2, 5, 5, 8, 3] // The key value is one. This value is less than all other elements in the array so it continues to shift to the beginnig of the array. 
Insertion Sort - Array after shifting: [2, 2, 5, 8, 3] // All elements of array[j]  are greater than key so j is decremented by one each iteration of the inner loop and the key value is checked against the new array[j] value each time.
Insertion Sort - Array after inserting key: [1, 2, 5, 8, 3] // J becomes -1 when the key reaches the beginning of the array and the while loop ends.
Insertion Sort - Key selected: 3 // The next key index is selected by the outer loop
Insertion Sort - Array after shifting: [1, 2, 5, 8, 8] // The inner loop array[j] is greater than key so the value of the element at the current index of j, which is one index before because j is decremented by one, set to the value of the element at the current index of j+1. This shifts the elements to the left and compares each element to the key value until the inner loop conditional is no longer met.
Insertion Sort - Array after shifting: [1, 2, 5, 5, 8] // Key value continues to shift to the left until the inner loop conditional is no longer met.
Insertion Sort - Array after inserting key: [1, 2, 3, 5, 8] // Key value is greater than the value of the element at the current index of j so the inner loop ends and the key value is set to the value of the element at the current index of j+1.
Sorted Array using Insertion Sort: [1, 2, 3, 5, 8] 

```

### UML Diagram for Selection Sort
#### Output Log of the Selection Sort Algorithm

```java
int [] anArray = [5, 2, 8, 1, 3]

Output Log:
selectionSort(anArray)
Original Array for Selection Sort: [5, 2, 8, 1, 3]
Selection Sort - Starting index: 0 // i begins at 0
Selection Sort - Current j: 1, Current minIndex: 1, Current array: [5, 2, 8, 1, 3] // j begins at 1, minIndex is incremented to 1
Selection Sort - Current j: 2, Current minIndex: 1, Current array: [5, 2, 8, 1, 3] // inner loop continues until the end of the array (condition j < n)
Selection Sort - Current j: 3, Current minIndex: 3, Current array: [5, 2, 8, 1, 3] // element value one is less than the current minIndex value so minIndex is set to the j index. Boolean swapped is set to true.
Selection Sort - Current j: 4, Current minIndex: 3, Current array: [5, 2, 8, 1, 3] // j continues to the end of the array. 
// This highlights why the time complexity of selection sort is O(n^2) as it has to iterate through the array entirely in the inner loop as each element [i] of the outer loop is compared to the all of the other elements of the array using the inner loop during each iteration of the outer loop.
Selection Sort - Swapped elements at indices 0 and 3: [1, 2, 8, 5, 3]
Selection Sort - Starting index: 1
Selection Sort - Current j: 2, Current minIndex: 1, Current array: [1, 2, 8, 5, 3]
Selection Sort - Current j: 3, Current minIndex: 1, Current array: [1, 2, 8, 5, 3]
Selection Sort - Current j: 4, Current minIndex: 1, Current array: [1, 2, 8, 5, 3]
Selection Sort - Starting index: 2
Selection Sort - Current j: 3, Current minIndex: 3, Current array: [1, 2, 8, 5, 3]
Selection Sort - Current j: 4, Current minIndex: 4, Current array: [1, 2, 8, 5, 3]
Selection Sort - Swapped elements at indices 2 and 4: [1, 2, 3, 5, 8]
Selection Sort - Starting index: 3
Selection Sort - Current j: 4, Current minIndex: 3, Current array: [1, 2, 3, 5, 8]
Sorted Array using Selection Sort: [1, 2, 3, 5, 8]
```

Yes, the comments provided in the code are correct and accurately describe the functionality of the code. Here's a breakdown:

1. **Null Check**:
    ```java
    if (array == null) {
        throw new IllegalArgumentException("Array cannot be null"); // throws an exception if the array is null
    }
    ```
    - The comment correctly states that an exception is thrown if the array is null.

2. **Array Length**:
    ```java
    int n = array.length; // sets n to the length of the array
    ```
    - The comment correctly states that [`n`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A31%2C%22character%22%3A12%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") is set to the length of the array.

3. **Outer Loop**:
    ```java
    for (int i = 0; i < n - 1; i++) { // sets i as zero, sets the condition for i to stop when it reaches n-1, which
                                      // is the last index of the zero indexed array, and sets the increment of i by
                                      // 1
    ```
    - The comment correctly explains that [`i`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A32%2C%22character%22%3A17%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") starts at zero, the loop runs until [`i`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A32%2C%22character%22%3A17%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") reaches [`n-1`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A31%2C%22character%22%3A12%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition"), and [`i`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A32%2C%22character%22%3A17%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") is incremented by 1 in each iteration.

4. **Minimum Index**:
    ```java
    int minIndex = i; // sets i as minimum index value of the array,
    ```
    - The comment correctly states that [`minIndex`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A35%2C%22character%22%3A16%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") is initially set to [`i`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A32%2C%22character%22%3A17%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition").

5. **Logging**:
    ```java
    System.out.println("Selection Sort - Starting index: " + i); // prints the starting index value of the array, which is always zero in the first logging since the loop is only in the first iteration
    ```
    - The comment correctly explains that the starting index value is printed, and it is zero in the first iteration.

6. **Inner Loop**:
    ```java
    for (int j = i + 1; j < n; j++) { // sets j as i+1, which is the element one index ahead of i, sets the condition for j to stop when it reaches the value of n, which is the total length of the array. This is set to this value because j is an index value ahead of i, so it will reach the last index of the array, and sets the increment of j by 1
    ```
    - The comment correctly explains that [`j`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A38%2C%22character%22%3A21%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") starts at [`i + 1`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A32%2C%22character%22%3A17%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition"), the loop runs until [`j`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A38%2C%22character%22%3A21%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") reaches [`n`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A31%2C%22character%22%3A12%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition"), and [`j`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fe%3A%2FCis232Decoupled%2FRecursionSortingProject%2Fsrc%2FAssignment%2FSortAlgo%2FInsertionAndSelectionSort.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A38%2C%22character%22%3A21%7D%7D%5D%2C%222c7086ff-dd3a-4b95-8c4b-fd42667ee015%22%5D "Go to definition") is incremented by 1 in each iteration.

The comments are accurate and provide a clear explanation of the code's functionality.