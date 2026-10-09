/**
 * Holds the outcome of one graph traversal (BFS or DFS).
 */
public class TraversalResult {
 
    private final String algorithm;
    private final String order;        // e.g. "Colombo -> Kandy -> Galle"
    private final int visitedCount;
    private final long steps;
    private final long timeNanos;
 
    public TraversalResult(String algorithm, String order, int visitedCount,
                           long steps, long timeNanos) {
        this.algorithm = algorithm;
        this.order = order;
        this.visitedCount = visitedCount;
        this.steps = steps;
        this.timeNanos = timeNanos;
    }
 
    public String getAlgorithm() {
        return algorithm;
    }
 
    public String getOrder() {
        return order;
    }
 
    public int getVisitedCount() {
        return visitedCount;
    }
 
    public long getSteps() {
        return steps;
    }
 
    public long getTimeNanos() {
        return timeNanos;
    }
}
