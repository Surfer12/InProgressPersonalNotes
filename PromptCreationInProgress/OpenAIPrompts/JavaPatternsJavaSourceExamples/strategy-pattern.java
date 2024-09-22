import java.util.List;

/**
 * Strategy Pattern implementation for sorting algorithms
 */
public interface SortStrategy {
    <T extends Comparable<T>> void sort(List<T> list);
}

public class BubbleSortStrategy implements SortStrategy {
    @Override
    public <T extends Comparable<T>> void sort(List<T> list) {
        // Bubble sort implementation
        System.out.println("Sorting using Bubble Sort");
        // ... (implementation details omitted for brevity)
    }
}

public class QuickSortStrategy implements SortStrategy {
    @Override
    public <T extends Comparable<T>> void sort(List<T> list) {
        // Quick sort implementation
        System.out.println("Sorting using Quick Sort");
        // ... (implementation details omitted for brevity)
    }
}

public class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public <T extends Comparable<T>> void sort(List<T> list) {
        if (strategy == null) {
            throw new IllegalStateException("Sort strategy not set");
        }
        strategy.sort(list);
    }
}

// Usage
public class StrategyPatternDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 2, 8, 1, 9);
        Sorter sorter = new Sorter();

        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sort(numbers);

        sorter.setStrategy(new QuickSortStrategy());
        sorter.sort(numbers);
    }
}
