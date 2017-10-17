# Linked List

```java
class LinkedList {    
    class Node {
        Node next;
        int val;

        Node(int val) {
            this.val = val;
        }
    }

    Node head;

    void printList() {
        Node n = head;
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    void append(int val) {
        if (head == null) {
            head = new Node(val);
        } else {
            Node last = head;
            while (last.next != null) last = last.next;
            last.next = new Node(val);
        }
    }

    void delete(int val) {
        if (head == null) return;

        if (head.val == val) {
            head = head.next;
            return;
        }

        Node node = head;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }

    Node search(int val) {
        Node node = head;
        while (node != null) {
            if (node.val == val) return node;
            node = node.next;
        }
        return null;
    }

    void reverse() {
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    void setHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
    }
}
```
