package stack;

public class PriorityNode<T> {
    public T value;
    public int priority;
    public PriorityNode<T> next;

    public PriorityNode(T value, int priority) {
        this.value = value;
        this.priority = priority;
        this.next = null;
    }
}
