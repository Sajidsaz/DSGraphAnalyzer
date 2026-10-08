/**
 * Holds the outcome of one search: where it was found,
 * how many comparisons (steps) it took and how long it took.
 */
public class SearchResult {
 
    private final int index;        // -1 means "not found"
    private final long steps;
    private final long timeNanos;
 
    public SearchResult(int index, long steps, long timeNanos) {
        this.index = index;
        this.steps = steps;
        this.timeNanos = timeNanos;
    }
 
    public int getIndex() {
        return index;
    }
 
    public boolean isFound() {
        return index != -1;
    }
 
    public long getSteps() {
        return steps;
    }
 
    public long getTimeNanos() {
        return timeNanos;
    }
}