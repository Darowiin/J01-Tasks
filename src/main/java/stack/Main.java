package stack;

public class Main {
    public static void main(String[] args) {
        PriorityStack<Integer> numberStack = new PriorityStack<>();

        numberStack.push(10, 5);
        numberStack.push(20, 2);
        numberStack.push(30, 8);
        numberStack.push(40, 2);

        System.out.println("Размер стека: " + numberStack.size());
        System.out.println("Верхний элемент: " + numberStack.peek());

        System.out.println("Извлекаем элементы по приоритету:");
        while (numberStack.size() > 0) {
            System.out.println(numberStack.pop());
        }

        PriorityStack<String> stringStack = new PriorityStack<>();

        stringStack.push("Apple", 3);
        stringStack.push("Banana", 1);
        stringStack.push("Cherry", 2);
        stringStack.push("Strawberry", 1);

        System.out.println("\nСтек строк:");
        System.out.println("Верхний элемент: " + stringStack.peek());

        System.out.println("Извлекаем элементы:");
        while (stringStack.size() > 0) {
            System.out.println(stringStack.pop());
        }
    }
}