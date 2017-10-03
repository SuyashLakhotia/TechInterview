# Algorithms

## Types of Algorithms

### Iterative

- An algorithm that is called repeatedly but for a finite number of times, each time being a single iteration.
- Often used to move incrementally through a dataset.

### Recursive

- An algorithm that calls itself in its definition.
- The **recursive case** in a conditional statement is used to trigger the recursion.
- The **base case** in a conditional statement is used to break the recursion.
- Note that recursive algorithms can be very space inefficient as each recursive call adds a new layer to the stack.

### Greedy

- An algorithm that follows the problem solving heuristic of making the locally optimal choice at each stage with the hope of finding a global optimum.
- The general five components, taken from [Wikipedia](http://en.wikipedia.org/wiki/Greedy_algorithm#Specifics):
    - A **candidate set**, from which a solution is created.
    - A **selection function**, which chooses the best candidate to be added to the solution.
    - A **feasibility function**, which is used to determine if a candidate can be used to contribute to a solution.
    - An **objective function**, which assigns a value to a solution, or a partial solution.
    - A **solution function**, which will indicate when we have discovered a complete solution.

## Dynamic Programming

Dynamic programming is a general method for solving a problem with optimal substructure by breaking it down into overlapping subproblems.

**Top-Down:** Memoize (store) the solutions to subproblems and solve problem recursively.

```java
int fibonacci(int n) {
    return fibonacci(n, new int[n + 1]);
}

int fibonacci(int i, int[] memo) {
    if (i == 0 || i == 1) return i;

    if (memo[i] == 0) {
        memo[i] = fibonacci(i - 1, memo) + fibonacci(i - 2, memo);
    }

    return memo[i];
}
```

**Bottom-Up:** Build up subproblems from base case up and avoid recursive overhead. Order subproblems by topologically sorting the DAG of dependencies.

```java
int fibonacci(int n) {
    if (n == 0 || n == 1) return n;

    int[] memo = new int[n];
    memo[0] = 0;
    memo[1] = 1;
    for (int i = 2; i < n; i++) {
        memo[i] = memo[i - 1] + memo[i - 2];
    }

    return memo[n - 1] + memo[n - 2];
}
```

## Important Algorithms

- [Search Algorithms](2.1%20-%20Search%20Algorithms.md)
    - Sequential Search
    - Binary Search
- [Sorting Algorithms](2.2%20-%20Sorting%20Algorithms.md)
    - Selection Sort
    - Bubble Sort
    - Insertion Sort
    - Merge Sort
    - Quicksort
    - Heap Sort
    - Bucket Sort
    - Radix Sort
- [Tree & Graph Traversal Algorithms](2.3%20-%20Tree%20&%20Graph%20Traversal%20Algorithms.md)
    - Breadth-First Traversal
    - Depth-First Traversal (Pre-Order, In-Order, Post-Order)
- [Pathfinding Algorithms](2.4%20-%20Pathfinding%20Algorithms.md)
    - Dijkstra's Algorithm
    - A* Search Algorithm
    - Bellman-Ford Algorithm
    - Floyd-Warshall Algorithm
- [Other Graph Algorithms](2.5%20-%20Other%20Graph%20Algorithms.md)
    - Prim's Algorithm
    - Kruskal's Algorithm
    - Topological Sorting
