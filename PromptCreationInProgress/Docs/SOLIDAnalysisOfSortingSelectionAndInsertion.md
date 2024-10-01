1. **Single Responsibility Principle (SRP):** The `InsertionAndSelectionSort` class currently has two methods, `insertionSort` and `selectionSort`. This violates SRP because the class is responsible for two different sorting algorithms. To fix this, create separate classes for each sorting algorithm:

   ```java
   // src/Assignment/SortAlgo/InsertionSort.java
   public class InsertionSort {
       public static void sort(int[] array) {
           // ... implementation ...
       }
   }

   // src/Assignment/SortAlgo/SelectionSort.java
   public class SelectionSort {
       public static void sort(int[] array) {
           // ... implementation ...
       }
   }
   ```

2. **Open/Closed Principle (OCP):** The current implementation is not open for extension without modification. If you want to add a new sorting algorithm, you need to modify the `InsertionAndSelectionSort` class. To fix this, you can use an interface to define a common sorting method and implement it in different classes:

   ```java
   // src/Assignment/SortAlgo/SortingAlgorithm.java
   public interface SortingAlgorithm {
       void sort(int[] array);
   }

   // src/Assignment/SortAlgo/InsertionSort.java
   public class InsertionSort implements SortingAlgorithm {
       @Override
       public void sort(int[] array) {
           // ... implementation ...
       }
   }

   // src/Assignment/SortAlgo/SelectionSort.java
   public class SelectionSort implements SortingAlgorithm {
       @Override
       public void sort(int[] array) {
           // ... implementation ...
       }
   }
   ```

3. **Liskov Substitution Principle (LSP):** The current implementation does not guarantee that subclasses can be used interchangeably with the base class. For example, if you create a new sorting algorithm that throws a different exception, it will not be compatible with the existing code. To fix this, you can define a common exception class for all sorting algorithms:

   ```java
   // src/Assignment/SortAlgo/SortingException.java
   public class SortingException extends Exception {
       public SortingException(String message) {
           super(message);
       }
   }

   // src/Assignment/SortAlgo/InsertionSort.java
   public class InsertionSort implements SortingAlgorithm {
       @Override
       public void sort(int[] array) throws SortingException {
           // ... implementation ...
       }
   }

   // src/Assignment/SortAlgo/SelectionSort.java
   public class SelectionSort implements SortingAlgorithm {
       @Override
       public void sort(int[] array) throws SortingException {
           // ... implementation ...
       }
   }
   ```

4. **Interface Segregation Principle (ISP):** The `SortingAlgorithm` interface currently defines a single method, `sort`. If you want to add new functionality to sorting algorithms, you need to modify the interface. To fix this, you can create separate interfaces for different functionalities:

   ```java
   // src/Assignment/SortAlgo/Sortable.java
   public interface Sortable {
       void sort(int[] array) throws SortingException;
   }

   // src/Assignment/SortAlgo/Loggable.java
   public interface Loggable {
       void log(String message);
   }

   // src/Assignment/SortAlgo/InsertionSort.java
   public class InsertionSort implements Sortable, Loggable {
       @Override
       public void sort(int[] array) throws SortingException {
           // ... implementation ...
       }

       @Override
       public void log(String message) {
           // ... implementation ...
       }
   }

   // src/Assignment/SortAlgo/SelectionSort.java
   public class SelectionSort implements Sortable, Loggable {
       @Override
       public void sort(int[] array) throws SortingException {
           // ... implementation ...
       }

       @Override
       public void log(String message) {
           // ... implementation ...
       }
   }
   ```

5. **Dependency Inversion Principle (DIP):** The current implementation depends on concrete classes, such as `Arrays`. To fix this, you can use interfaces to abstract dependencies:

   ```java
   // src/Assignment/SortAlgo/ArrayUtils.java
   public interface ArrayUtils {
       String toString(int[] array);
   }

   // src/Assignment/SortAlgo/DefaultArrayUtils.java
   public class DefaultArrayUtils implements ArrayUtils {
       @Override
       public String toString(int[] array) {
           return Arrays.toString(array);
       }
   }

   // src/Assignment/SortAlgo/InsertionSort.java
   public class InsertionSort implements Sortable, Loggable {
       private final ArrayUtils arrayUtils;

       public InsertionSort(ArrayUtils arrayUtils) {
           this.arrayUtils = arrayUtils;
       }

       @Override
       public void sort(int[] array) throws SortingException {
           // ... implementation ...
           System.out.println("Insertion Sort - Array after inserting key: " + arrayUtils.toString(array));
       }

       // ... other methods ...
   }

   // src/Assignment/SortAlgo/SelectionSort.java
   public class SelectionSort implements Sortable, Loggable {
       private final ArrayUtils arrayUtils;

       public SelectionSort(ArrayUtils arrayUtils) {
           this.arrayUtils = arrayUtils;
       }

       @Override
       public void sort(int[] array) throws SortingException {
           // ... implementation ...
           System.out.println("Selection Sort - Swapped elements at indices " + i + " and " + minIndex + ": " + arrayUtils.toString(array));
       }

       // ... other methods ...
   }
   ```

6. **Refactoring for Clarity and Performance:**

   - **Remove Redundant Comments:** Many comments are repetitive and do not add value. Remove them to improve readability.
   - **Use Descriptive Variable Names:** Use clear and descriptive variable names to make the code self-documenting.
   - **Extract Methods:** Extract complex logic into separate methods to improve code organization and readability.
   - **Optimize Loops:** Use more efficient loop structures and avoid unnecessary iterations.

7. **Testing and Error Management:**

   - **Unit Tests:** Write comprehensive unit tests for each sorting algorithm, including edge cases and boundary conditions.
   - **Error Handling:** Use custom exceptions to provide more context in error scenarios.

8. **Documentation:**

   - **Javadoc Comments:** Use Javadoc comments to document classes, methods, and parameters.
   - **UML Diagrams:** Create UML diagrams to visualize the class relationships and interactions.
