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

}