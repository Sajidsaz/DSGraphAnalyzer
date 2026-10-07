import java.util.Arrays;
import java.util.Scanner;
 
/**
 * Stack (LIFO - Last In, First Out) built manually on top of an array.
 * The array doubles in size when it becomes full, so push never fails.
 */
public class MyStack {
 
    private int[] data;
    private int top = -1;      // index of the top element; -1 means the stack is empty
 
    public MyStack() {
        this(10);
    }
 
    public MyStack(int initialCapacity) {
        data = new int[Math.max(1, initialCapacity)];
    }
 
    public boolean isEmpty() {
        return top == -1;
    }
 
    public int size() {
        return top + 1;
    }
 
    /** Adds a value on top of the stack. O(1) on average. */
    public void push(int value) {
        if (top == data.length - 1) {
            grow();
        }
        top++;
        data[top] = value;
    }
 
    private void grow() {
        data = Arrays.copyOf(data, data.length * 2);
    }
    /** Removes and returns the top value. O(1). Fails clearly if the stack is empty. */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack Underflow: the stack is empty, nothing to pop.");
        }
        int value = data[top];
        top--;
        return value;
    }
 
    /** Returns the top value without removing it. O(1). */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty: there is no top element to peek.");
        }
        return data[top];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack (top to bottom):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  | " + data[i] + " |" + (i == top ? "   <-- TOP" : ""));
        }
        System.out.println("  +---+");
    }
 
    /** Stack submenu. Returns when the user chooses "Return to Main Menu". */
    public void runMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display Stack");
            System.out.println("5. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> {
                    int value = InputUtil.readInt(sc, "Enter value to push: ");
                    push(value);
                    System.out.println("Pushed " + value + ". Stack size is now " + size() + ".");
                }
                case 2 -> {
                    try {
                        int value = pop();
                        System.out.println("Popped " + value + ". Stack size is now " + size() + ".");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        System.out.println("Top element is " + peek() + ".");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> display();
                case 5 -> back = true;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }


}