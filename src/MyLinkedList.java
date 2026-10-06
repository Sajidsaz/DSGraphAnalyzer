import java.util.Scanner;
 
/**
 * Singly linked list of integers, built manually with Node objects.
 * Every operation records how many nodes it visited in lastSteps.
 */
public class MyLinkedList {
 
    private Node head = null;
    private int size = 0;
    private long lastSteps = 0;     // nodes visited by the most recent operation
 
    public boolean isEmpty() {
        return head == null;
    }
 
    public int size() {
        return size;
    }
 
    public long getLastSteps() {
        return lastSteps;
    }
 
    /** Adds a value at the front of the list. O(1). */
    public void insertAtHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        lastSteps = 1;
    }
 
    /** Adds a value at the end of the list. O(n) because we must walk to the last node. */
    public void insertAtTail(int value) {
        Node newNode = new Node(value);
        lastSteps = 0;
        if (head == null) {
            head = newNode;
            lastSteps = 1;
        } else {
            Node current = head;
            lastSteps = 1;
            while (current.next != null) {
                current = current.next;
                lastSteps++;
            }
            current.next = newNode;
        }
        size++;
    }
 
    /**
     * Inserts a value so that it ends up at the given position (0 = front, size = end).
     * Returns false if the position is invalid.
     */
    public boolean insertAt(int position, int value) {
        if (position < 0 || position > size) {
            return false;
        }
        if (position == 0) {
            insertAtHead(value);
            return true;
        }
        Node current = head;
        lastSteps = 1;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            lastSteps++;
        }
        Node newNode = new Node(value);
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return true;
    }
 
    /** Deletes the first node that holds value. Returns false if it is not in the list. */
    public boolean delete(int value) {
        lastSteps = 0;
        if (head == null) {
            return false;
        }
        lastSteps++;
        if (head.data == value) {          // special case: deleting the head node
            head = head.next;
            size--;
            return true;
        }
        Node previous = head;
        Node current = head.next;
        while (current != null) {
            lastSteps++;
            if (current.data == value) {
                previous.next = current.next;   // bypass the node being deleted
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }
 
    /** Linear search through the nodes. Returns the position (0-based) or -1 if not found. */
    public int search(int value) {
        lastSteps = 0;
        Node current = head;
        int position = 0;
        while (current != null) {
            lastSteps++;
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }
 
    public void display() {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }
        StringBuilder line = new StringBuilder("HEAD -> ");
        Node current = head;
        while (current != null) {
            line.append(current.data).append(" -> ");
            current = current.next;
        }
        line.append("null");
        System.out.println(line);
        System.out.println("Size: " + size);
    }
 
    /** Linked list submenu. Returns when the user chooses "Return to Main Menu". */
    public void runMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- LINKED LIST OPERATIONS ---------------");
            System.out.println("1. Insert at Head");
            System.out.println("2. Insert at Tail");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete a Value");
            System.out.println("5. Search for a Value");
            System.out.println("6. Display List");
            System.out.println("7. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> {
                    int value = InputUtil.readInt(sc, "Enter value to insert at head: ");
                    insertAtHead(value);
                    System.out.println("Inserted " + value + " at the head. Steps: " + lastSteps);
                    ResultLog.add("Linked List Insert", "Insert at Head", lastSteps);
                }
                case 2 -> {
                    int value = InputUtil.readInt(sc, "Enter value to insert at tail: ");
                    insertAtTail(value);
                    System.out.println("Inserted " + value + " at the tail. Steps: " + lastSteps);
                    ResultLog.add("Linked List Insert", "Insert at Tail", lastSteps);
                }
                case 3 -> {
                    int position = InputUtil.readIntInRange(sc,
                            "Enter position (0 to " + size + "): ", 0, size);
                    int value = InputUtil.readInt(sc, "Enter value to insert: ");
                    insertAt(position, value);
                    System.out.println("Inserted " + value + " at position " + position
                            + ". Steps: " + lastSteps);
                    ResultLog.add("Linked List Insert", "Insert at Position", lastSteps);
                }
                case 4 -> {
                    if (isEmpty()) {
                        System.out.println("Linked list is empty. Nothing to delete.");
                    } else {
                        int value = InputUtil.readInt(sc, "Enter value to delete: ");
                        if (delete(value)) {
                            System.out.println("Deleted " + value + ". Steps: " + lastSteps);
                            ResultLog.add("Linked List Delete", "Traverse + relink", lastSteps);
                        } else {
                            System.out.println(value + " was not found in the list.");
                        }
                    }
                }
                case 5 -> {
                    if (isEmpty()) {
                        System.out.println("Linked list is empty. Nothing to search.");
                    } else {
                        int value = InputUtil.readInt(sc, "Enter value to search for: ");
                        long start = System.nanoTime();
                        int position = search(value);
                        long elapsed = System.nanoTime() - start;
                        if (position == -1) {
                            System.out.println(value + " was not found in the list.");
                        } else {
                            System.out.println("Found " + value + " at position " + position + ".");
                        }
                        System.out.println("Steps: " + lastSteps
                                + " | Time: " + ResultLog.formatTime(elapsed) + " ns");
                        ResultLog.add("Linked List Search", "Linear traversal", lastSteps, elapsed);
                    }
                }
                case 6 -> display();
                case 7 -> back = true;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
        }
    }
}
