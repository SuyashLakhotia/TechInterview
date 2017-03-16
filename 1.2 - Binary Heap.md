# Binary Heap

- Is a complete binary tree that satisfies the **heap property**.
    - **Heap Property:** If `A` is a parent node of `B`, then the value of node `A` is ordered with respect to the value of node `B` with the same ordering applying across the heap.
- A heap can be further classified as either a **max heap** or a **min heap**.
    - In a **min heap**, the keys of parent nodes are less than or equal to those of the children and the lowest key is in the root node.
    - In a **max heap**, the keys of parent nodes are always greater than or equal to those of the children and the highest key is in the root node.
- Heaps are crucial in several efficient graph algorithms such as Dijkstra's algorithm and sorting algorithms such as Heap Sort.
- The `PriorityQueue` class in Java is based on heaps and can be used as a heap ([Oracle Docs](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)).
- A heap can be implemented using an array, where the left child of `node[i]` is at `node[2 * i + 1]` and the right child is at `node[2 * i + 2]`.

#### Time Complexity

- **Insertion:** `O(log n)`
- **Extract Min/Max Element:** `O(1)`
    - **Fix Heap After Retrieval:** `O(log n)`

### Implementation in Java

```java
class BinaryMinHeap {
    private int[] data;
    private int heapSize;

    BinaryMinHeap(int size) {
        this.data = new int[size];
        this.heapSize = 0;
    }

    int peekMinimum() {
        if (!isEmpty()) return data[0];
        throw new HeapException("Heap is empty!");
    }

    boolean isEmpty() {
        return this.heapSize == 0;
    }

    private int getLeftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    private int getRightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    private int getParentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public class HeapException extends RuntimeException {
        public HeapException(String message) {
              super(message);
        }
    }
}
```

```java
void insert(int key) {
    if (heapSize == data.length) {
        throw new HeapException("Heap is full!");
    } else {
        heapSize++;
        heap[heapSize - 1] = key;
        heapifyUp(heapSize - 1);
    }
}

private void heapifyUp(int index) {
    if (index == 0) return;

    int parentIndex = getParentIndex(index);
    if (arr[parentIndex] > data[index]) {
        swap(index, parentIndex);
        heapifyUp(parentIndex);
    }
}
```

```java
int pollMinimum() {
    if (isEmpty()) {
        throw new HeapException("Heap is empty!");
    } else {
        int min = data[0];
        data[0] = data[heapSize - 1];
        heapSize--;
        heapifyDown(0);
        return min;
    }
}

private void heapifyDown(int index) {
    int leftChild = getLeftChildIndex(index);
    int rightChild = getRightChildIndex(index);

    if (rightChild >= heapSize) {       // no right child
        if (leftChild >= heapSize) {    // no left child
            return;
        } else {
            if (data[leftChild] < data[index]) {
                swap(leftChild, index);
                heapifyDown(leftChild);
            }
        }
    } else {
        int minIndex = data[leftChild] < data[rightChild] ? leftChild : rightChild;
        if (data[minIndex] < data[index]) {
            swap(minIndex, index);
            heapifyDown(minIndex);
        }
    }
}
```
