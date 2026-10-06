import java.util.Scanner;

/**
 * Singly linked list of integers, built manually with Node objects.
 * Every operation records how many nodes it visited in lastSteps.
 */

public class MyLinkedList {

    private Node head = null;
    private int size = 0;
    private long lastSteps = 0;  //nodes visited by the most recent ops

    public boolean isempty() {
        return head  == null;
    }

    public int size() {
        return size;
    }

    public long getLastSteps() {
        return lastSteps;
    }

    /**add a value at the front of the list. 0(1) */
    public void insertAtHead(int value){
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        lastSteps = -1;
        }

        /**add a value at the end of the list. O(n) because we must walk to the last node.  */
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

}