# Assignment 17: Fibonacci and Factorial Implementations

## Table of Contents
1. [Introduction](#introduction)
2. [Time and Space Complexity](#time-and-space-complexity)
3. [Fibonacci Sequence](#fibonacci-sequence)
   - [Recursive Implementation](#recursive-fibonacci)
   - [Iterative Implementation](#iterative-fibonacci)
   - [Comparison](#fibonacci-comparison)
4. [Factorial Sequence](#factorial-sequence)
   - [Recursive Implementation](#recursive-factorial)
   - [Iterative Implementation using Iterator Interface](#iterative-factorial)
   - [Comparison](#factorial-comparison)
5. [Conclusion](#conclusion)

## Introduction

This document explores the implementation of two fundamental mathematical sequences: the Fibonacci sequence and the factorial sequence. We'll examine both recursive and iterative approaches for each, analyzing their time and space complexities, and discussing the pros and cons of each method.

## Time and Space Complexity

Before diving into the implementations, let's define time and space complexity:

- **Time Complexity**: A measure of the amount of time an algorithm takes to complete as a function of the input size. It's typically expressed using Big O notation, which describes the upper bound of the growth rate of the algorithm's running time.

- **Space Complexity**: A measure of the amount of memory an algorithm uses as a function of the input size. Like time complexity, it's often expressed using Big O notation.

## Fibonacci Sequence

The Fibonacci sequence is defined as follows:
- F(0) = 0
- F(1) = 1
- F(n) = F(n-1) + F(n-2) for n > 1

### Recursive Fibonacci

```java
public static int fibonacciRecursive(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
}
```

**Time Complexity**: O(2^n)
**Space Complexity**: O(n)

**Why Recursive?**
- The recursive implementation directly translates the mathematical definition into code, making it intuitive and easy to understand.
- It demonstrates the concept of divide-and-conquer, breaking the problem into smaller subproblems.

**Why Not Recursive?**
- It has exponential time complexity, making it inefficient for large values of n.
- It recalculates the same values multiple times, leading to redundant computations.

### Iterative Fibonacci

```java
public static int fibonacciIterative(int n) {
    if (n <= 1) {
        return n;
    }
    int a = 0, b = 1, c;
    for (int i = 2; i <= n; i++) {
        c = a + b;
        a = b;
        b = c;
    }
    return b;
}
```

**Time Complexity**: O(n)
**Space Complexity**: O(1)

**Why Iterative?**
- It's much more efficient than the recursive approach, with linear time complexity.
- It uses constant extra space, making it memory-efficient.
- It avoids redundant calculations by storing and updating previous results.

**Why Not Iterative?**
- It's less intuitive compared to the recursive approach, as it doesn't directly mirror the mathematical definition.
- It requires careful management of variables to keep track of previous values.

### Fibonacci Comparison

The iterative approach is generally preferred for calculating Fibonacci numbers due to its superior time and space efficiency. However, the recursive method can be useful for educational purposes or when clarity of code is prioritized over performance for small inputs.

## Factorial Sequence

The factorial of a non-negative integer n, denoted as n!, is the product of all positive integers less than or equal to n.

### Recursive Factorial

```java
public static int factorialRecursive(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorialRecursive(n - 1);
}
```

**Time Complexity**: O(n)
**Space Complexity**: O(n) due to the call stack

**Why Recursive?**
- It closely mirrors the mathematical definition of factorial, making it intuitive.
- It demonstrates the concept of recursion clearly.

**Why Not Recursive?**
- It can lead to stack overflow for very large inputs due to the deep call stack.
- It has higher space complexity compared to the iterative approach.

### Iterative Factorial using Iterator Interface

```java
public class FactorialIterator implements Iterator<Integer> {
    private int current = 1;
    private int n;

    public FactorialIterator(int n) {
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        return current <= n;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = 1;
        for (int i = 1; i <= current; i++) {
            result *= i;
        }
        current++;
        return result;
    }
}

// Usage
public static int factorialIterative(int n) {
    FactorialIterator iterator = new FactorialIterator(n);
    int result = 1;
    while (iterator.hasNext()) {
        result = iterator.next();
    }
    return result;
}
```

**Time Complexity**: O(n^2) due to the nested loop in the `next()` method
**Space Complexity**: O(1)

**Why Iterative with Iterator?**
- It demonstrates the use of the Iterator interface, which is a common pattern in Java for traversing collections.
- It allows for lazy evaluation of factorial values, which can be useful in certain scenarios.
- It has constant space complexity, making it memory-efficient.

**Why Not Iterative with Iterator?**
- The implementation shown has quadratic time complexity, which is less efficient than a simple iterative approach.
- It's more complex than necessary for simply calculating a single factorial value.

### Factorial Comparison

For simple factorial calculations, a basic iterative approach (not shown here) would be most efficient, with O(n) time complexity and O(1) space complexity. The recursive approach is intuitive but can lead to stack overflow for large inputs. The Iterator-based approach demonstrates an interesting design pattern but is less efficient for calculating a single factorial value.

## Conclusion

Both the Fibonacci and factorial sequences can be implemented using recursive and iterative approaches. The choice between them depends on factors such as performance requirements, code readability, and specific use cases. Generally, iterative solutions offer better performance and memory usage, while recursive solutions can provide clearer, more intuitive implementations at the cost of efficiency.

When designing algorithms, it's crucial to consider both time and space complexity to create efficient solutions, especially for large inputs. The examples provided here demonstrate how different approaches to the same problem can yield vastly different performance characteristics.
