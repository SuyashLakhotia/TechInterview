# Sorting Algorithms

## Selection Sort ([GIF](assets/Selection-Sort.gif))

- Selection sort finds the minimum element in the unsorted part of the array and swaps it with the first element in the unsorted part of the array.
- The sorted part of the array grows from left to right with every iteration.
- After `i` iterations, the first `i` elements of the array are sorted.
- Sorts in-place. Not stable.<sup>[1](#footnote1)</sup>

### Algorithm

```java
void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        int min = i;
        for (int j = i; j < arr.length; j++) {
            if (arr[j] <= arr[min]) min = j;
        }

        if (min != i) {
            swap(arr, i, min);
        }
    }
}
```

### Time Complexity

- **Best Case:** `O(n^2)`
- **Average Case:** `O(n^2)`
- **Worst Case:** `O(n^2)`

## Bubble Sort ([GIF](assets/Bubble-Sort.gif))

- In every iteration, bubble sort compares every couplet, moving the larger element to the right as it iterates through the array.
- The sorted part of the array grows from right to left with every iteration.
- After `i` iterations, the last `i` elements of the array are the largest and sorted.
- Sorts in-place. Stable.<sup>[1](#footnote1)</sup>

### Algorithm

```java
void bubbleSort(int[] arr) {
    boolean swapped = true;
    int j = 0;

    while (swapped) {
        swapped = false;
        for (int i = 1; i < arr.length - j; i++) {
            if (arr[i - 1] > arr[i]) {
                swap(arr, i - 1, i);
                swapped = true;
            }
        }
        j++;
    }
}
```

### Time Complexity

- **Best Case:** `O(n)`
- **Average Case:** `O(n^2)`
- **Worst Case:** `O(n^2)`

## Insertion Sort ([GIF](assets/Insertion-Sort.gif))

- In every iteration, insertion sort takes the first element in the unsorted part of the array, finds the location it belongs to within the sorted part of the array and inserts it there.
- The sorted part of the array grows from left to right with every iteration.
- After `i` iterations, the first `i` elements of the array are sorted.
- Sorts in-place. Stable.<sup>[1](#footnote1)</sup>

### Algorithm

```java
void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        for (int j = i; j > 0; j--) {
            if (arr[j - 1] > arr[j]) {
                swap(arr, j - 1, j);
            } else {
                break;
            }
        }
    }
}
```

### Time Complexity

- **Best Case:** `O(n)`
- **Average Case:** `O(n^2)`
- **Worst Case:** `O(n^2)`

## Merge Sort ([GIF](assets/Merge-Sort.png))

- Uses the *divide & conquer* approach.
- Merge sort divides the original array into smaller arrays recursively until the resulting subarrays have one element each.
- Then, it starts merging the divided subarrays by comparing each element and moving the smaller one to the left of the merged array.
- This is done recursively till all the subarrays are merged into one sorted array.
- Requires `O(n)` space. Stable.<sup>[1](#footnote1)</sup>

### Algorithm

```java
void mergesort(int[] arr) {
    int[] helper = new int[arr.length];
    mergesort(arr, helper, 0, arr.length - 1);
}

void mergesort(int[] arr, int[] helper, int low, int high) {
    // Check if low is smaller than high, if not then the array is sorted.
    if (low < high) {
        int mid = low + ((high - low) / 2);        // Get index of middle element
        mergesort(arr, helper, low, mid);          // Sort left side of the array
        mergesort(arr, helper, mid + 1, high);     // Sort right side of the array
        merge(arr, helper, low, mid, high);        // Combine both sides
    }
}

void merge(int[] arr, int[] helper, int low, int mid, int high) {
    // Copy both halves into a helper array.
    for (int i = low; i <= high; i++) {
        helper[i] = arr[i];
    }

    int helperLeft = low;
    int helperRight = mid + 1;
    int current = low;

    // Iterate through helper array. Compare the left and right half, copying back
    // the smaller element from the two halves into the original array.
    while (helperLeft <= mid && helperRight <= high) {
        if (helper[helperLeft] <= helper[helperRight]) {
            arr[current] = helper[helperLeft];
            helperLeft++;
        } else {
            arr[current] = helper[helperRight];
            helperRight++;
        }
        current++;
    }

    // Copy the rest of the left half of the array into the target array. Right half
    // is already there.
    while (helperLeft <= mid) {
        arr[current] = helper[helperLeft];
        current++;
        helperLeft++;
    }
}
```

### Time Complexity

- **Best Case:** `O(n log n)`
- **Average Case:** `O(n log n)`
- **Worst Case:** `O(n log n)`

## Quicksort ([GIF](assets/Quicksort.gif))

- Quicksort starts by selecting one element as the *pivot*. The array is then divided into two subarrays with all the elements smaller than the pivot on the left side of the pivot and all the elements greater than the pivot on the right side.
- It recursively repeats this process on the left side until it is comparing only two elements at which point the left side is sorted.
- Once the left side is sorted, it performs the same recursive operation on the right side.
- Quicksort is the fastest general purpose in-memory sorting algorithm in practice.
    - Best case occurs when the pivot always splits the array into equal halves.
    - Usually used in conjunction with Insertion Sort when the subarrays become smaller and *almost* sorted.
- Requires `O(log n)` space on average. Not stable.<sup>[1](#footnote1)</sup>

### Algorithm

```java
void startQuicksort(int[] arr) {
    quicksort(arr, 0, arr.length - 1);
}

void quicksort(int[] arr, int low, int high) {
    if (low >= high) return;

    int mid = low + ((high - low) / 2);
    int pivot = arr[mid];  // pick pivot point

    int i = low, j = high;
    while (i <= j) {
        // Find element on left that should be on right.
        while (arr[i] < pivot) i++;

        // Find element on right that should be on left.
        while (arr[j] > pivot) j--;

        // Swap elements and move left and right indices.
        if (i <= j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    // Sort left half.
    if (low < i - 1)
        quicksort(arr, low, i - 1);

    // Sort right half.
    if (i < high)
        quicksort(arr, i, high);
}
```

### Time Complexity

- **Best Case:** `O(n log n)`
- **Average Case:** `O(n log n)`
- **Worst Case:** `O(n^2)`

## Heap Sort ([GIF](assets/Heap-Sort.gif))

- Heap sort takes the maximum element in the array and places it at the end of the array.
- At every iteration, the maximum element from the unsorted part of the array is selected by taking advantage of the binary heap data structure and placed at the end. Then, the unsorted part is heapified and the process is repeated.
- After `i` iterations, the last `i` elements of the array are sorted.
- Sorts in-place. Not stable.<sup>[1](#footnote1)</sup>

### Binary Heap (Array Implementation)

- We can implement a binary heap with `n` nodes using an array with the following conditions:
    - The left child of `nodes[i]` is `nodes[2i + 1]`.
    - The right child of `nodes[i]` is `nodes[2i + 2]`.
    - `nodes[i]` is a leaf if `2i + 1` > `n`.
- Therefore, in a binary max heap, `nodes[i]` > `nodes[2i + 1]` & `nodes[i]` > `nodes[2i + 2]`.

### Algorithm

```java
void heapSort(int[] arr) {
    int n = arr.length;

    // Construct initial max-heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // Extract an element one by one from heap
    for (int i = n - 1; i >= 0; i--) {
        // Move current root to end
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        // Call heapify() on the reduced heap
        heapify(arr, i, 0);
    }
}

// Heapifies a subtree rooted at arr[i]. n is the size of the entire heap.
void heapify(int arr[], int n, int i) {
    int largest = i;  // initialize largest as root
    int l = 2*i + 1;  // left child
    int r = 2*i + 2;  // right child

    // If left child is larger than root
    if (l < n && arr[l] > arr[largest])
        largest = l;

    // If right child is larger than largest so far
    if (r < n && arr[r] > arr[largest])
        largest = r;

    // If largest is not root
    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        // Recursively heapify the affected subtree
        heapify(arr, n, largest);
    }
}
```

### Time Complexity

- **Best Case:** `O(n log n)`
- **Average Case:** `O(n log n)`
- **Worst Case:** `O(n log n)`

## Bucket Sort

- Bucket sort is a sorting algorithm that works by distributing the elements of an array into a number of buckets.
- Each bucket is then sorted individually, either using a different sorting algorithm or by recursively applying the bucket sorting algorithm.

### Time Complexity

- **Average Case:** `O(n + k)` (where `k` is the number of buckets)
- **Worst Case:** `O(n^2)`

## Radix Sort

- Radix sort is a sorting algorithm for integers (and some other data types) that groups the numbers by each digit from left to right (most significant digit radix sort) or right to left (least significant digit radix sort) on every pass.
- This process is repeated for each subsequent digit until the whole array is sorted.

### Time Complexity

- **Worst Case:** `O(kn)` (where `k` is the number of passes of the algorithm)

---

<a name="footnote1">[1](#footnote1)</a>: A sorting algorithm is said to be **stable** if two objects with equal keys appear in the same order in sorted output as they appear in the input array to be sorted.
