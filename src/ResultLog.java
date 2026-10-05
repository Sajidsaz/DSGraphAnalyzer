import java.util.ArrayList;

/**
 * Shared store for results. Every component calls ResultLog.add(...)
 * and menu option 8 prints everything with printAll().
 */
public class ResultLog {
 
    private static class Entry {
        final String operation;
        final String algorithm;
        final long steps;
        final long timeNanos;
 
        Entry(String operation, String algorithm, long steps, long timeNanos) {
            this.operation = operation;
            this.algorithm = algorithm;
            this.steps = steps;
            this.timeNanos = timeNanos;
        }
    }
 
    private static final ArrayList<Entry> entries = new ArrayList<>();
 
    /** Record a result with steps and execution time (nanoseconds). */
    public static void add(String operation, String algorithm, long steps, long timeNanos) {
        entries.add(new Entry(operation, algorithm, steps, timeNanos));
    }
 
    /** Record a result that has steps but no timing. */
    public static void add(String operation, String algorithm, long steps) {
        add(operation, algorithm, steps, -1);
    }
 
    public static int count() {
        return entries.size();
    }
 
    /** Formats nanoseconds with commas, or "-" when no time was measured. */
    public static String formatTime(long nanos) {
        return nanos < 0 ? "-" : String.format("%,d", nanos);
    }
 
    public static void printAll() {
        if (entries.isEmpty()) {
            System.out.println("No results recorded yet. Run some operations first.");
            return;
        }
        System.out.println("=====================================================================");
        System.out.println(" ALL RECORDED RESULTS");
        System.out.println("=====================================================================");
        System.out.printf("%-4s %-26s %-20s %-10s %-14s%n",
                "No.", "Operation", "Algorithm", "Steps", "Time (ns)");
        System.out.println("---------------------------------------------------------------------");
        int number = 1;
        for (Entry e : entries) {
            System.out.printf("%-4d %-26s %-20s %-10s %-14s%n",
                    number++, e.operation, e.algorithm,
                    String.format("%,d", e.steps), formatTime(e.timeNanos));
        }
        System.out.println("=====================================================================");
    }
}
