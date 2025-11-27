package stack;

/**
 * Реализация обобщённого приоритетного стека.
 * <p>
 * Стек поддерживает вставку элементов с приоритетом: чем меньше значение приоритета,
 * тем выше элемент располагается в стеке.
 * <p>
 * Элементы хранятся во внутреннем односвязном списке, отсортированном по возрастанию приоритета.
 *
 * @param <T> тип хранимых значений
 */
public class PriorityStack<T> {

    /**
     * Ссылка на первый (верхний) элемент стека.
     */
    private PriorityNode<T> head;

    /**
     * Возвращает количество элементов в стеке.
     *
     * @return число элементов
     */
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

    /**
     * Добавляет элемент в стек с указанным приоритетом.
     * <p>
     * Приоритет определяет положение элемента: чем меньше число, тем выше элемент в стеке.
     *
     * @param value    значение элемента
     * @param priority приоритет (меньше = выше)
     */
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

    /**
     * Извлекает и удаляет верхний элемент стека.
     *
     * @return значение верхнего элемента или {@code null}, если стек пуст
     */
    public T pop() {
        if (this.head == null) {
            return null;
        }

        PriorityNode<T> current = this.head;
        this.head = current.next;

        current.next = null;
        return current.value;
    }

    /**
     * Возвращает значение верхнего элемента стека, не удаляя его.
     *
     * @return верхнее значение или {@code null}, если стек пуст
     */
    public T peek() {
        if (this.head == null) {
            return null;
        }

        return this.head.value;
    }

    /**
     * Узел односвязного списка, использующийся в {@link PriorityStack}.
     *
     * @param <T> тип хранимого значения
     */
    private static class PriorityNode<T> {

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
}
