import java.util.Arrays;
import java.util.Scanner;
 
/**
 * Queue (FIFO - First In, First Out) built manually as a circular array.
 * The array doubles in size when it becomes full, so enqueue never fails.
 */
public class MyQueue {
 
    private int[] data;
    private int front = 0;     // index of the first element
    private int rear = 0;      // index where the next element will be placed
    private int count = 0;     // number of elements stored
 
    public MyQueue() {
        this(10);
    }
 
    public MyQueue(int initialCapacity) {
        data = new int[Math.max(1, initialCapacity)];
    }
 
    public boolean isEmpty() {
        return count == 0;
    }
 
    public int size() {
        return count;
    }
 
    /** Adds a value at the rear of the queue. O(1) on average. */
    public void enqueue(int value) {
        if (count == data.length) {
            grow();
        }
        data[rear] = value;
        rear = (rear + 1) % data.length;     // wrap around the end of the array
        count++;
    }
 
    /** Doubles the array and copies the elements in queue order. */
    private void grow() {
        int[] bigger = new int[data.length * 2];
        for (int i = 0; i < count; i++) {
            bigger[i] = data[(front + i) % data.length];
        }
        data = bigger;
        front = 0;
        rear = count;
    }
 
    /** Removes and returns the front value. O(1). Fails clearly if the queue is empty. */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue Underflow: the queue is empty, nothing to dequeue.");
        }
        int value = data[front];
        front = (front + 1) % data.length;
        count--;
        return value;
    }
 
    /** Returns the front value without removing it. O(1). */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty: there is no front element.");
        }
        return data[front];
    }
 
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        StringBuilder line = new StringBuilder("FRONT -> ");
        for (int i = 0; i < count; i++) {
            line.append(data[(front + i) % data.length]).append(" -> ");
        }
        line.append("REAR");
        System.out.println(line);
    }
 
    /** Queue submenu. Returns when the user chooses "Return to Main Menu". */
    public void runMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek (Front)");
            System.out.println("4. Display Queue");
            System.out.println("5. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> {
                    int value = InputUtil.readInt(sc, "Enter value to enqueue: ");
                    enqueue(value);
                    System.out.println("Enqueued " + value + ". Queue size is now " + size() + ".");
                }
                case 2 -> {
                    try {
                        int value = dequeue();
                        System.out.println("Dequeued " + value + ". Queue size is now " + size() + ".");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        System.out.println("Front element is " + peek() + ".");
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