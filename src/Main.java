import java.util.Scanner;
 
/**
 * Main console application. Shows the main menu and calls each component.
 */
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        // One object per data structure, shared for the whole run
        ArrayOps array = new ArrayOps();
        MyStack stack = new MyStack();
        MyQueue queue = new MyQueue();
        MyLinkedList linkedList = new MyLinkedList();
        Graph graph = new Graph();
 
        boolean running = true;
 
        while (running) {
            System.out.println();
            System.out.println("=============================================");
            System.out.println(" DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> array.runMenu(sc);
                case 2 -> stack.runMenu(sc);
                case 3 -> queue.runMenu(sc);
                case 4 -> linkedList.runMenu(sc);
                case 5 -> SearchOps.runMenu(sc, array);
                case 6 -> graph.runMenu(sc);
                case 7 -> PerformanceAnalyzer.runMenu(sc);
                case 8 -> ResultLog.printAll();
                case 9 -> running = false;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }
 
        System.out.println("Thank you for using the analyzer. Goodbye!");
        sc.close();
    }
}
