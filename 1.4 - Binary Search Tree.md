# Binary Search Tree

- BST is a binary tree that uses comparable keys to assign which direction a child is.
- The left child is always smaller than its parent node.
- The right child is always greater than its parent node.
- There can be no duplicate nodes.
- Designed to optimize searching and sorting.
- In-order traversal of a BST produces a sorted list.

#### Time Complexity

- **Search:** Average Case: `O(log n)`, Worst Case: `O(n)`

### Implementation in Java

```java
class BinarySearchTree {
    class Node {
        int val;
        Node right, left;

        Node(int val) {
            this.val = val;
        }
    }

    Node root;
}
```

```java
Node search(Node node, int key) {
    // Return null if not found or node if found.
    if (node == null || node.val == key)
        return node;

    // Search left subtree if key < node.val.
    if (key < node.val)
        return search(node.left, key);

    // Search right subtree if key > node.val.
    return search(node.right, key);
}
```

```java
void insert(int key) {
    root = insertRec(root, key);
}

Node insertRec(Node node, int key) {
    if (node == null) {
        node = new Node(key);
        return node;
    }

    if (key < node.key)
        node.left = insertRec(node.left, key);
    else if (key > node.key)
        node.right = insertRec(node.right, key);

    return node;
}
```

```java
void delete(int key) {
    root = deleteRec(root, key);
}

Node deleteRec(Node node, int key) {
    // If tree is empty or node doesn't exist, return null.
    if (node == null) return null;

    // Else, recursively traverse the tree.
    if (key < node.key) {
        node.left = deleteRec(node.left, key);
    } else if (key > node.key) {
        node.right = deleteRec(node.right, key);
    } else {
        // If node has only one child or no children.
        if (node.left == null)
            return node.right;
        else if (node.right == null)
            return node.left;

        // If node has two children, replace current node's value with the
        // minimum value in the node's right subtree (inorder successor).
        node.key = minValue(node.right);

        // Delete the inorder successor.
        node.right = deleteRec(node.right, node.key);
    }

    return node;
}

int minValue(Node node) {
    while (node.left != null) node = node.left;
    return node.key;
}
```

## Self-Balancing Binary Search Tree

- A self-balancing binary search tree is a BST that automatically keeps its height at a minimum regardless of arbitrary insertions and deletions. This allows for worst-case lookup performance of `O(log n)` instead of `O(n)`.
- Self-balancing binary search trees are useful to construct and maintain ordered lists, such as priority queues.
- They are also useful to store key-value pairs with an ordering based on the key alone. As opposed to hash tables, they provide enumeration of the items in key order and better worst-case performance. However, hash tables have better average case performance (`O(1)`).

### AVL Tree

- An AVL tree stores in each node the height of the subtrees rooted at this node. Then, for any node, it is possible to check if it is height balanced i.e. the height of the left subtree and right subtree should differ by no more than one.

```
balance(n) = n.left.height - n.right.height
```

```
-1 <= balance(n) <= 1
```

- During insertion, the balance of some nodes may change to -2 or 2. Therefore, when the recursive stack is unwinded after insertion, the balance is checked and fixed at each node going upwards from the inserted node.
- Imbalances are fixed by performing left or right rotations at each node. These operations do not violate the BST rule.

```
      y                                x
     / \        Right Rotation        /  \
    x   T3      – – – – – – – >      T1   y
   / \          < - - - - - - -          / \
  T1  T2         Left Rotation          T2  T3

keys(T1) < key(x) < keys(T2) < key(y) < keys(T3)
```

#### Time Complexity

- **Search:** `O(log n)`
- **Insertion:** `O(log n)`
- **Deletion:** `O(log n)`

### Red-Black Tree

- Red-black trees do not ensure quite as strict balancing as AVL trees but it is still good enough for `O(log n)` insertions, deletions & retrievals.
- They require less memory and can rebalance faster (thus, quicker insertions and deletions).
- `TreeMap` and `TreeSet` in Java are implemented using red-black trees.

#### Time Complexity

- **Search:** `O(log n)`
- **Insertion:** `O(log n)`
- **Deletion:** `O(log n)`
