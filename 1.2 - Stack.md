# Stack

```java
class Stack {
    class StackNode {
        int val;
        StackNode next;

        public StackNode(int val) {
            this.val = val;
        }
    }

    StackNode top;

    int pop() {
        if (top == null) throw new EmptyStackException();
        int val = top.val;
        top = top.next;
        return val;
    }

    void push(int val) {
        StackNode node = new StackNode(val);
        node.next = top;
        top = node;
    }

    int peek() {
        if (top == null) throw new EmptyStackException();
        return top.val;
    }

    boolean isEmpty() {
        return top == null;
    }
}
```
