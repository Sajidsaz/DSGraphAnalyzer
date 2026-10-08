import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
 
/**
 * Fixed-size array of integers with insert, delete, search and display.
 * "size" is the number of elements currently stored; CAPACITY is the maximum.
 */
public class ArrayOps {
 
    public static final int CAPACITY = 100;
 
    private final int[] data = new int[CAPACITY];
    private int size = 0;
 
    public int getSize() {
        return size;
    }
 
    public boolean isEmpty() {
        return size == 0;
    }
 
    public boolean isFull() {
        return size == CAPACITY;
    }
 
    /** Returns a copy of the used part of the array (used by the search code). */
    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }
 
    /**
     * Inserts value at index (0 to size). Elements on the right move one place right.
     * Returns the number of elements shifted, or -1 if the insert is not possible.
     */
    public int insertAt(int index, int value) {
        if (isFull() || index < 0 || index > size) {
            return -1;
        }
        int shifts = 0;
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            shifts++;
        }
        data[index] = value;
        size++;
        return shifts;
    }
 
    public int insertAtEnd(int value) {
        return insertAt(size, value);
    }

        /**
     * Deletes the element at index (0 to size-1). Elements on the right move one place left.
     * Returns the number of elements shifted, or -1 if the delete is not possible.
     */
    public int deleteAt(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return -1;
        }
        int shifts = 0;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            shifts++;
        }
        size--;
        return shifts;
    }
 
    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.println("Array (" + size + " of " + CAPACITY + " used):");
        StringBuilder line = new StringBuilder("Index: ");
        StringBuilder values = new StringBuilder("Value: ");
        for (int i = 0; i < size; i++) {
            line.append(String.format("%5d", i));
            values.append(String.format("%5d", data[i]));
        }
        System.out.println(line);
        System.out.println(values);
    }

}