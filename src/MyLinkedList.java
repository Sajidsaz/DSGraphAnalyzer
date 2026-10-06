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
}