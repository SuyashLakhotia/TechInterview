# Data Structures

## Primitive Data Types (Java)

Data Type |       Description       | Default  |  Size
:-------: | :---------------------: | :------: | :-----:
`boolean` |    `true` or `false`    | `false`  |  1 bit
 `char`   |    Unicode character    | `\u0000` | 16 bits
 `byte`   | twos complement integer |   `0`    |  8 bits
 `short`  | twos complement integer |   `0`    | 16 bits
  `int`   | twos complement integer |   `0`    | 32 bits
 `long`   | twos complement integer |   `0`    | 64 bits
 `float`  | IEEE 754 floating point |  `0.0`   | 32 bits
`double`  | IEEE 754 floating point |  `0.0`   | 64 bits

## Array

- Stores data elements based on a sequential (usually zero-based) index.

### Important Points

- Optimal for indexing. Bad for searching, inserting and deleting.
- **Linear Arrays**, or one dimensional arrays, are the most basic.
- **Two Dimensional Arrays** have `x` & `y` indices like a grid or nested arrays.
- **Dynamic Arrays** are like one dimensional arrays but have reserved space for additional elements.
    - If a dynamic array is full, it copies its contents to a larger array (usually 2x in size).

### Time Complexity

- **Access:** `O(1)`
- **Search:** `O(n)`
- **Insertion at End of Dynamic Array:** `O(1)` amortized

### Java

- [Oracle Docs - Array](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [TutorialsPoint - Array](https://www.tutorialspoint.com/java/java_arrays.htm)
- [Oracle Docs - ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [TutorialsPoint - ArrayList](https://www.tutorialspoint.com/java/java_arraylist_class.htm)

## Linked List

- Stores data using **nodes** that have a datum and pointers to other nodes.

### Important Points

- Designed to optimize insertion and deletion. Slow at indexing and searching.
- **Singly Linked Lists** have nodes that reference the next node.
- **Doubly Linked Lists** have nodes that also reference the previous node.
- **Circularly Linked Lists** are linked lists whose *tail* references the *head*.
- **Stacks** are commonly implemented using linked lists.
    - Stacks are **last in, first out (LIFO)** data structures. `push()` & `pop()`.
    - Implemented with a linked list where the head is the only place for insertion and removal.
- **Queues** are commonly implemented using linked lists.
    - Queues are **first in, first out (FIFO)** data structures. `enqueue()` & `dequeue()`.
    - Implemented with a linked list that only removes from the head and adds to the tail.
- **Double-Ended Queues** or **Deques** are also commonly implemented using linked lists.
    - Deques allow elements to be added and removed from either the head or tail of the queue.

### Time Complexity

- **Access:** `O(n)`
- **Search:** `O(n)`
- **Insertion:** `O(1)`
- **Deletion:** `O(1)`

### Java

- [Oracle Docs - LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)
- [TutorialsPoint - LinkedList](https://www.tutorialspoint.com/java/util/java_util_linkedlist.htm)
- [TutorialsPoint - Iterator](https://www.tutorialspoint.com/java/java_using_iterator.htm)
- [Oracle Docs - Stack](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html)
- [Oracle Docs - Queue](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html)
- [Oracle Docs - Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html)

## Hash Table

- Stores data as key-value pairs in a direct access table.
- **Hash Functions** accept a key (from an arbitrarily sized dataset) and map it to an output i.e. hash code (from a fixed sized dataset). This hash code is then mapped to an index for storage.
    - This is known as **hashing**, whose motivation is to assign a unique index to every possible key.
    - This is done because the actual key space may be too large while only a fraction of those keys may appear.

### Important Points

- Designed to optimize searching, insertion and deletion.
- A good **hash function** must:
    - return a value within the hash table range.
    - achieve an even distribution of indices from the keys that actually occur.
    - be easy and quick to compute.
- **Hash Collisions** occur when a hash function returns the same hash code for two distinct keys or two different hash codes are mapped to the same index.
    - Most hash functions have this problem.
    - **Closed Address Hashing -** Chain together the keys that generate the same index in a linked list (`O(n)`) or binary search tree (`O(log n)`).
        - **Load Factor** is `n/h`, where `n` is the number of records and `h` is the number of hash cells.
    - **Open Address Hashing -** Store all the elements in the hash table and handle hash collisions by **rehashing** i.e. look for an alternative slot.
        - **Linear Probing -** Insert the colliding record in the next slot recursively. However, this can result in long runs of occupied slots.
        - **Quadratic Probing/Double Hashing -** Use two hash functions to hash the key twice in case of a collision.
        - Using open address hashing requires a hash cell to be marked as *obsolete* when a record is deleted to avoid stopping search.
- Hashes are important for associative arrays and database indexing.

### Time Complexity

- **Search:** Average Case: `O(1)`, Worst Case: `O(n)`
- **Insertion:** Average Case: `O(1)`, Worst Case: `O(n)`
- **Deletion:** Average Case: `O(1)`, Worst Case: `O(n)`

### Java

- **`HashMap`:** `<K, V>`, no guarantee about iteration order, `O(1)`.
- **`TreeMap`:** `<K, V>`, iteration according to natural order of keys or externally supplied `Comparator`, `O(log n)`.
- **`LinkedHashMap`:** `<K, V>`, iteration according to insertion order, `O(1)`.
- **`HashSet`:** `<T>`, no guarantee about iteration order, `O(1)`.
- **`TreeSet`:** `<T>`, iteration according to natural order of keys or externally supplied `Comparator`, `O(log n)`.
- **`LinkedHashSet`:** `<T>`, iteration according to insertion order, `O(1)`.

#### Links

- [TutorialsPoint - HashMap](https://www.tutorialspoint.com/java/java_hashmap_class.htm)
- [Oracle Docs - HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- [Oracle Docs - TreeMap](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)
- [Oracle Docs - LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html)
- [TutorialsPoint - HashSet](https://www.tutorialspoint.com/java/java_hashset_class.htm)
- [Oracle Docs - HashSet](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html)
- [Oracle Docs - TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html)
- [Oracle Docs - LinkedHashSet](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashSet.html)

## Tree

- A tree is a data structure composed of nodes.
- Each tree has one *root node* that has zero or more child nodes and each of these child nodes has zero or more child nodes.
- Nodes without children are called *leaves*.
- A tree cannot contain cycles and nodes may not have links back to their parent nodes.

## Binary Tree

- Is a tree data structure where every node has at most two children.
    - There is one left and one right child node.

### Important Points

- A **complete binary tree** is one where every level of the tree is fully filled, except for perhaps the last level. To the extent that the last level is filled, it is filled from left to right.
- A **full binary tree** is one where every node has either zero or two children.
- A **perfect binary tree** is one that is both full and complete. Perfect binary trees must have exactly `2^k - 1` nodes, where `k` is the number of levels.
- A **degenerate tree** is an unbalanced tree, which if entirely one-sided is essentially a linked list.
- Used to implement [**binary search trees**](1.1%20-%20Binary%20Search%20Tree.md) & [**binary heaps**](1.2%20-%20Binary%20Heap.md).

## Trie (Prefix Tree)

- Is a variant of an n-ary tree in which characters are stored at each node. Each path down the tree represents a word.

### Important Points

- `*` nodes (or `null` nodes) are used to indicate complete words.
- A node in a trie could have anywhere from 1 through `ALPHABET_SIZE + 1` children (or 0 through `ALPHABET_SIZE` children if a boolean value is used to indicate `*` nodes).
- Tries are usually used to store words for quick prefix lookups. While a hash table can quickly look up whether a string is a valid word, it cannot quickly verify if a string is a prefix of any valid words.

### Time Complexity

- **Validate Prefix:** `O(n)` (where `n` is length of prefix)

### Java

- [Java Implementation of Trie](1.3%20-%20Trie.java)

## Graph

- A linked list based data structure where links (edges) can exist between any two nodes (vertices).
- Edges may be directed/undirected and weighted/unweighted. The graph may be cyclic.
- **LL-based Implementation:**
    - A 1-D array is used to represent the vertices.
    - A linked list or array is used for each vertex `v`, which contains the vertices that are adjacent to `v` (adjacency list).
    - Vertices adjacent to a vertex can be found quickly.
    - In an undirected graph, edges will be stored twice.
- **Array-based Implementation:**
    - A 1-D array is used to represent the vertices.
    - A 2-D boolean array (adjacency matrix) is used to represent the edges.
    - Connectivity between two vertices can be verified quickly.
    - In an undirected graph, the adjacency matrix will be symmetrical.
- An adjacency matrix is usually better to store dense graphs while an adjacency list is better to store sparse graphs.
- Traversal in an adjacency list implementation is more efficient than an adjacency matrix implementation since it's not necessary to iterate through all the nodes to find a node's neighbors.

### Important Points

- Two nodes are **adjacent** if they are connected by an edge.
- The **degree** of a node `i` is the number of nodes adjacent to `i`.
- A **path** is a sequence of edges that connect two nodes.
- A **connected graph** is a graph where a path exists between any two nodes.
- A **complete graph** is a graph where every vertex is directly connected to every other vertex.
- Traversal is similar to trees but vertices must be marked as visited (due to multiple possible paths) and all adjacent vertices must be visited (as opposed to just two in binary trees).

### Time Complexity

- **Storage Size:** Adjacency List: `O(|V| + |E|)`, Adjacency Matrix: `O(|V|^2)`
- **Add Vertex:** Adjacency List: `O(1)`, Adjacency Matrix: `O(|V|^2)`
- **Add Edge:** Adjacency List: `O(1)`, Adjacency Matrix: `O(1)`
- **Remove Vertex:** Adjacency List: `O(|V| + |E|)`, Adjacency Matrix: `O(|V|^2)`
- **Remove Edge:** Adjacency List: `O(|E|)`, Adjacency Matrix: `O(1)`
- **Query for Adjacency:** Adjacency List: `O(|V|)`, Adjacency Matrix: `O(1)`
