    UML Diagram for Merge Sort Process
    ======================================

    The UML diagram below represents the `MergeSortWithLogging` class and its methods. This diagram illustrates the relationships and flow of the merge sort algorithm.

    ```mermaid
    classDiagram
    class MergeSortWithLogging {
            +void performMergeSort(int[] array, int startIndex, int endIndex)
            +void mergeSortedHalves(int[] array, int startIndex, int middleIndex, int endIndex)
            +static void main(String[] args)
    }
    ```

    Additional Visualizations
    ---------------------------

    1. **Flowchart for `performMergeSort` Method:**
    - Start
    - Check if `startIndex < endIndex`
    - Yes: Calculate `middleIndex`
    - Recursively call `performMergeSort` for the left half
    - Recursively call `performMergeSort` for the right half
    - Call `mergeSortedHalves`
    - End

    ```mermaid
    flowchart TD
    A[Start]-->B{startIndex < endIndex}
    B--Yes-->C[Calculate middleIndex]
    C-->D[Call performMergeSort for left half]
    D-->E[Call performMergeSort for right half]
    E-->F[Call mergeSortedHalves]
    B--No-->G[End]
    ```

    2. **Flowchart for `mergeSortedHalves` Method:**
    - Start
    - Create `leftArray` and `rightArray`
    - Log merging process
    - While merging:
    - Compare elements and merge
    - Log merged result
    - End

    ```mermaid
    flowchart TD
    A[Start]-->B[Create leftArray and rightArray]
    B-->C[Log merging process]
    C-->D{While leftIndex < leftSize and rightIndex < rightSize}
    D--Yes-->E[Compare elements and merge]
    D--No-->F[Copy remaining elements]
    F-->G[Log merged result]
    G-->H[End]
    ```

    Summary
    The new `MergeSortWithLogging.java` file includes logging statements to track the sorting process. UML diagrams and flowcharts provide visual representations of the algorithm's structure and flow. This approach enhances understanding and debugging capabilities, making it easier to follow the algorithm's execution.

    If you need further modifications or additional artifacts, feel free to ask!