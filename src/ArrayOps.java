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
}