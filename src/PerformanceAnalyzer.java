import java.util.Random;
import java.util.Scanner;
 
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
 
    /** Builds a connected test graph: a ring of v vertices plus v extra random edges. */
    private static Graph buildTestGraph(int v) {
        Graph graph = new Graph();
        for (int i = 0; i < v; i++) {
            graph.addVertex("V" + i);
        }
        for (int i = 0; i < v; i++) {
            graph.addEdge(i, (i + 1) % v);              // ring keeps the graph connected
        }
        Random random = new Random(42);                  // fixed seed = same graph every run
        for (int i = 0; i < v; i++) {
            graph.addEdge(random.nextInt(v), random.nextInt(v));
        }
        return graph;
    }
 
    /** BFS vs DFS on test graphs of growing size, both starting from vertex V0. */
    public static void compareGraphTraversal() {
        printHeader("PERFORMANCE COMPARISON - GRAPH TRAVERSAL");
        for (int v : GRAPH_SIZES) {
            Graph graph = buildTestGraph(v);
            TraversalResult bfs = graph.bfs(0);
            TraversalResult dfs = graph.dfs(0);
 
            String label = "Graph V=" + String.format("%,d", v)
                    + " E=" + String.format("%,d", graph.getEdgeCount());
            printRow(label, "BFS", bfs.getSteps(), bfs.getTimeNanos());
            printRow(label, "DFS", dfs.getSteps(), dfs.getTimeNanos());
            ResultLog.add(label, "BFS", bfs.getSteps(), bfs.getTimeNanos());
            ResultLog.add(label, "DFS", dfs.getSteps(), dfs.getTimeNanos());
        }
        System.out.println("=====================================================================");
        System.out.println("BFS and DFS are both O(V + E): they visit every vertex and edge once.");
        System.out.println("Their step counts are close; the real difference is the visiting ORDER.");
        System.out.println("(DFS can pop a few already-visited vertices, "
                + "so its count may be slightly higher.)");
    }
 
    /** Performance submenu. Returns when the user chooses "Return to Main Menu". */
    public static void runMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- PERFORMANCE COMPARISON ---------------");
            System.out.println("1. Compare Searching (Linear vs Binary)");
            System.out.println("2. Compare Graph Traversal (BFS vs DFS)");
            System.out.println("3. Run Both Comparisons");
            System.out.println("4. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> compareSearching();
                case 2 -> compareGraphTraversal();
                case 3 -> {
                    compareSearching();
                    compareGraphTraversal();
                }
                case 4 -> back = true;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }
}