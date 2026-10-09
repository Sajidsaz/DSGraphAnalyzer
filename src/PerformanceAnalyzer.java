 
/**
 * Runs the performance comparisons and prints them as tables.
 * Steps are exact counts. Times (nanoseconds) change from run to run.
 */
public class PerformanceAnalyzer {
 
    private static final int[] SEARCH_SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final int[] GRAPH_SIZES = {100, 1_000, 10_000};
 
    private static void printHeader(String title) {
        System.out.println();
        System.out.println("=====================================================================");
        System.out.println(" " + title);
        System.out.println("=====================================================================");
        System.out.printf("%-24s %-16s %-14s %-14s%n", "Operation", "Algorithm", "Steps", "Time (ns)");
        System.out.println("---------------------------------------------------------------------");
    }
 
    private static void printRow(String operation, String algorithm, long steps, long nanos) {
        System.out.printf("%-24s %-16s %-14s %-14s%n", operation, algorithm,
                String.format("%,d", steps), ResultLog.formatTime(nanos));
    }
 
    /** Linear vs binary search on sorted arrays of growing size (worst case: last element). */
    public static void compareSearching() {
        printHeader("PERFORMANCE COMPARISON - SEARCHING");
        for (int n : SEARCH_SIZES) {
            int[] sorted = new int[n];
            for (int i = 0; i < n; i++) {
                sorted[i] = i * 2;
            }
            int target = sorted[n - 1];
 
            SearchResult linear = SearchOps.linearSearch(sorted, target);
            SearchResult binary = SearchOps.binarySearch(sorted, target);
 
            String label = "Search n=" + String.format("%,d", n);
            printRow(label, "Linear Search", linear.getSteps(), linear.getTimeNanos());
            printRow(label, "Binary Search", binary.getSteps(), binary.getTimeNanos());
            ResultLog.add(label, "Linear Search", linear.getSteps(), linear.getTimeNanos());
            ResultLog.add(label, "Binary Search", binary.getSteps(), binary.getTimeNanos());
        }
        System.out.println("=====================================================================");
        System.out.println("Linear search is O(n): steps grow with n.");
        System.out.println("Binary search is O(log n): steps grow very slowly (about log2 n).");
        System.out.println("Note: steps are exact; time changes on every run, so trust steps first.");
    }
}
