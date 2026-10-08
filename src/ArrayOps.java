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
 
    /** True if the elements are in ascending order (needed for binary search). */
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (data[i - 1] > data[i]) {
                return false;
            }
        }
        return true;
    }
 
    /** Insertion sort. Returns the number of comparisons made. */
    public long sort() {
        long steps = 0;
        for (int i = 1; i < size; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0) {
                steps++;
                if (data[j] > key) {
                    data[j + 1] = data[j];
                    j--;
                } else {
                    break;
                }
            }
            data[j + 1] = key;
        }
        return steps;
    }
 
    /** Replaces the contents with count random numbers from 1 to maxValue. */
    public void fillRandom(int count, int maxValue) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            data[i] = random.nextInt(maxValue) + 1;
        }
        size = count;
    }
 
    public void clear() {
        size = 0;
    }
 
    /** Array submenu. Returns when the user chooses "Return to Main Menu". */
    public void runMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Position");
            System.out.println("3. Delete at Position");
            System.out.println("4. Search for a Value (Linear Search)");
            System.out.println("5. Display Array");
            System.out.println("6. Sort Array (Insertion Sort)");
            System.out.println("7. Fill with Random Numbers");
            System.out.println("8. Clear Array");
            System.out.println("9. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> {
                    if (isFull()) {
                        System.out.println("Array is full (capacity " + CAPACITY
                                + "). Delete something first.");
                    } else {
                        int value = InputUtil.readInt(sc, "Enter value to insert: ");
                        insertAtEnd(value);
                        System.out.println("Inserted " + value + " at index " + (size - 1) + ".");
                    }
                }
                case 2 -> {
                    if (isFull()) {
                        System.out.println("Array is full (capacity " + CAPACITY
                                + "). Delete something first.");
                    } else {
                        int position = InputUtil.readIntInRange(sc,
                                "Enter position (0 to " + size + "): ", 0, size);
                        int value = InputUtil.readInt(sc, "Enter value to insert: ");
                        int shifts = insertAt(position, value);
                        System.out.println("Inserted " + value + " at index " + position
                                + ". Elements shifted: " + shifts);
                        ResultLog.add("Array Insert (position)", "Shift right", shifts);
                    }
                }
                case 3 -> {
                    if (isEmpty()) {
                        System.out.println("Array is empty. Nothing to delete.");
                    } else {
                        int position = InputUtil.readIntInRange(sc,
                                "Enter position to delete (0 to " + (size - 1) + "): ", 0, size - 1);
                        int shifts = deleteAt(position);
                        System.out.println("Deleted element at index " + position
                                + ". Elements shifted: " + shifts);
                        ResultLog.add("Array Delete", "Shift left", shifts);
                    }
                }
                case 4 -> {
                    if (isEmpty()) {
                        System.out.println("Array is empty. Nothing to search.");
                    } else {
                        int target = InputUtil.readInt(sc, "Enter value to search for: ");
                        SearchResult result = SearchOps.linearSearch(toArray(), target);
                        if (result.isFound()) {
                            System.out.println("Found " + target + " at index "
                                    + result.getIndex() + ".");
                        } else {
                            System.out.println(target + " was not found in the array.");
                        }
                        System.out.println("Steps: " + result.getSteps()
                                + " | Time: " + ResultLog.formatTime(result.getTimeNanos()) + " ns");
                        ResultLog.add("Array Search", "Linear Search",
                                result.getSteps(), result.getTimeNanos());
                    }
                }
                case 5 -> display();
                case 6 -> {
                    if (isEmpty()) {
                        System.out.println("Array is empty. Nothing to sort.");
                    } else {
                        long steps = sort();
                        System.out.println("Array sorted. Comparisons made: " + steps);
                        ResultLog.add("Array Sort", "Insertion Sort", steps);
                    }
                }
                case 7 -> {
                    int count = InputUtil.readIntInRange(sc,
                            "How many random numbers (1 to " + CAPACITY + ")? ", 1, CAPACITY);
                    fillRandom(count, 1000);
                    System.out.println("Array filled with " + count + " random numbers (1-1000).");
                }
                case 8 -> {
                    clear();
                    System.out.println("Array cleared.");
                }
                case 9 -> back = true;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }
    }
}