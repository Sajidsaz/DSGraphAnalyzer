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
}
