import java.util.Scanner;

/**
 * Main console application. Shows the main menu and calls each component.
 * (Skeleton version: components are connected during integration.)
 */

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                case 1 -> System.out.println("Array module coming soon.");
                case 2 -> System.out.println("Stack module coming soon.");
                case 3 -> System.out.println("Queue module coming soon.");
                case 4 -> System.out.println("Linked List module coming soon.");
                case 5 -> System.out.println("Searching module coming soon.");
                case 6 -> System.out.println("Graph module coming soon.");
                case 7 -> System.out.println("Performance module coming soon.");
                case 8 -> ResultLog.printAll();
                case 9 -> running = false;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 9.");

            }
        }
    }
}