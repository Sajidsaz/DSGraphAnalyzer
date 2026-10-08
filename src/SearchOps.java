import java.util.Scanner;
 
/**
 * Searching algorithms: linear search and binary search.
 * Both count their steps (comparisons) and measure their running time.
 */
public class SearchOps {
 
    /** Checks elements one by one from the start. Time complexity O(n). */
    public static SearchResult linearSearch(int[] arr, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int foundIndex = -1;
 
        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == target) {
                foundIndex = i;
                break;
            }
        }
        return new SearchResult(foundIndex, steps, System.nanoTime() - start);
    }
 
    /**
     * Repeatedly halves the search range. The array MUST be sorted.
     * Time complexity O(log n).
     */
    public static SearchResult binarySearch(int[] arr, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int foundIndex = -1;
        int low = 0;
        int high = arr.length - 1;
 
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                foundIndex = mid;
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(foundIndex, steps, System.nanoTime() - start);
    }
 
    private static void printResult(String name, int target, SearchResult result) {
        String outcome = result.isFound() ? "found at index " + result.getIndex() : "not found";
        System.out.printf("%-14s -> %d %s | Steps: %,d | Time: %s ns%n",
                name, target, outcome, result.getSteps(), ResultLog.formatTime(result.getTimeNanos()));
    }
 
    /** Searching submenu. It works on the array data from the Array component. */
    public static void runMenu(Scanner sc, ArrayOps array) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- SEARCHING OPERATIONS ---------------");
            System.out.println("1. Linear Search (on Array data)");
            System.out.println("2. Binary Search (on Array data)");
            System.out.println("3. Compare Linear vs Binary (large generated data)");
            System.out.println("4. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> {
                    if (array.isEmpty()) {
                        System.out.println("Array is empty. Add data in Array Operations first "
                                + "(or use option 3).");
                    } else {
                        int target = InputUtil.readInt(sc, "Enter value to search for: ");
                        SearchResult result = linearSearch(array.toArray(), target);
                        printResult("Linear Search", target, result);
                        ResultLog.add("Search (array data)", "Linear Search",
                                result.getSteps(), result.getTimeNanos());
                    }
                }
                case 2 -> {
                    if (array.isEmpty()) {
                        System.out.println("Array is empty. Add data in Array Operations first "
                                + "(or use option 3).");
                    } else {
                        boolean ready = true;
                        if (!array.isSorted()) {
                            System.out.println("Binary search only works on SORTED data. "
                                    + "The array is not sorted.");
                            if (InputUtil.readYesNo(sc, "Sort the array now?")) {
                                array.sort();
                                System.out.println("Array sorted.");
                            } else {
                                ready = false;
                            }
                        }
                        if (ready) {
                            int target = InputUtil.readInt(sc, "Enter value to search for: ");
                            SearchResult result = binarySearch(array.toArray(), target);
                            printResult("Binary Search", target, result);
                            ResultLog.add("Search (array data)", "Binary Search",
                                    result.getSteps(), result.getTimeNanos());
                        }
                    }
                }
                case 3 -> {
                    int n = InputUtil.readIntInRange(sc,
                            "How many elements to generate (10 to 5,000,000)? ", 10, 5_000_000);
                    int[] sorted = new int[n];
                    for (int i = 0; i < n; i++) {
                        sorted[i] = i * 2;          // already sorted: 0, 2, 4, ...
                    }
                    int target = sorted[n - 1];     // last element = worst case for linear search
                    System.out.println("Searching for " + target + " (the LAST element) in "
                            + n + " sorted numbers:");
                    SearchResult linear = linearSearch(sorted, target);
                    SearchResult binary = binarySearch(sorted, target);
                    printResult("Linear Search", target, linear);
                    printResult("Binary Search", target, binary);
                    String label = "Search n=" + String.format("%,d", n);
                    ResultLog.add(label, "Linear Search", linear.getSteps(), linear.getTimeNanos());
                    ResultLog.add(label, "Binary Search", binary.getSteps(), binary.getTimeNanos());
                    System.out.println("Why? Linear search is O(n): about n steps. "
                            + "Binary search is O(log n): about log2(n) steps.");
                }
                case 4 -> back = true;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }
}