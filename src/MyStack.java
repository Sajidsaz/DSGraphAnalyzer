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

}