package stack;

/**
 * Узел односвязного списка, использующийся в {@link PriorityStack}.
 *
 * @param <T> тип хранимого значения
 */
public class PriorityNode<T> {

    /**
     * Значение, хранящееся в узле.
     */
    public T value;

    /**
     * Приоритет элемента (меньшее число = выше приоритет).
     */
    public int priority;

    /**
     * Ссылка на следующий узел в списке.
     */
    public PriorityNode<T> next;

    /**
     * Создаёт новый узел с указанным значением и приоритетом.
     *
     * @param value    значение в узле
     * @param priority приоритет элемента
     */
    public PriorityNode(T value, int priority) {
        this.value = value;
        this.priority = priority;
        this.next = null;
    }
}
