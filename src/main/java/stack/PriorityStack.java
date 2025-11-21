package stack;

public class PriorityStack<T> {
    public PriorityNode<T> head;

    public int size() {
        if (this.head == null) {
            return 0;
        }
        int size = 1;

        PriorityNode<T> current = this.head;
        while (current.next != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    public void push(T value, int priority) {
        PriorityNode<T> newNode = new PriorityNode<>(value, priority);

        if (head == null || priority < head.priority) {
            newNode.next = head;
            head = newNode;
            return;
        }

        PriorityNode<T> current = this.head;

        while (current.next != null && current.next.priority < priority) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public T pop() {
        if (this.head == null) {
            return null;
        }

        PriorityNode<T> current = this.head;
        this.head = current.next;

        current.next = null;
        return current.value;
    }

    public T peek() {
        if (this.head == null) {
            return null;
        }

        return this.head.value;
    }
}
