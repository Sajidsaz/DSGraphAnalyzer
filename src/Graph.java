import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
 
/**
 * Undirected graph stored as an adjacency list.
 * Each vertex has a name (for example a city) and an index (0, 1, 2, ...).
 * adjacency.get(i) holds the indexes of all neighbours of vertex i.
 */
public class Graph {
 
    private final ArrayList<String> names = new ArrayList<>();
    private final HashMap<String, Integer> indexByName = new HashMap<>();   // lower-case name -> index
    private final ArrayList<ArrayList<Integer>> adjacency = new ArrayList<>();
    private int edgeCount = 0;
 
    public int getVertexCount() {
        return names.size();
    }
 
    public int getEdgeCount() {
        return edgeCount;
    }
 
    public String getName(int index) {
        return names.get(index);
    }
 
    /** Returns the index of a vertex name (not case-sensitive), or -1 if it does not exist. */
    public int indexOf(String name) {
        Integer index = indexByName.get(name.trim().toLowerCase());
        return index == null ? -1 : index;
    }
 
    /** Adds a vertex. Returns false if the name is empty or already exists. */
    public boolean addVertex(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String cleaned = name.trim();
        if (indexOf(cleaned) != -1) {
            return false;
        }
        indexByName.put(cleaned.toLowerCase(), names.size());
        names.add(cleaned);
        adjacency.add(new ArrayList<>());
        return true;
    }
 
    public boolean hasEdge(int u, int v) {
        return adjacency.get(u).contains(v);
    }
 
    /**
     * Adds an undirected edge between vertex indexes u and v.
     * Returns false for invalid vertices, a self-loop or a duplicate edge.
     */
    public boolean addEdge(int u, int v) {
        int n = names.size();
        if (u < 0 || v < 0 || u >= n || v >= n || u == v || hasEdge(u, v)) {
            return false;
        }
        adjacency.get(u).add(v);
        adjacency.get(v).add(u);
        edgeCount++;
        return true;
    }
 
    public void clear() {
        names.clear();
        indexByName.clear();
        adjacency.clear();
        edgeCount = 0;
    }
 
    public void display() {
        if (names.isEmpty()) {
            System.out.println("Graph is empty. Add some vertices first.");
            return;
        }
        System.out.println("Graph: " + names.size() + " vertices, " + edgeCount + " edges");
        System.out.println("(Adjacency list: vertex -> its neighbours)");
        for (int i = 0; i < names.size(); i++) {
            StringBuilder line = new StringBuilder(String.format("%-14s -> ", names.get(i)));
            ArrayList<Integer> neighbours = adjacency.get(i);
            if (neighbours.isEmpty()) {
                line.append("(no neighbours)");
            }
            for (int j = 0; j < neighbours.size(); j++) {
                if (j > 0) {
                    line.append(", ");
                }
                line.append(names.get(neighbours.get(j)));
            }
            System.out.println(line);
        }
    }
 
    /**
     * Breadth-First Search from the start vertex. Uses our own MyQueue.
     * Visits vertices level by level. Time complexity O(V + E).
     * Steps = vertices taken from the queue + neighbours checked.
     */
    public TraversalResult bfs(int start) {
        long begin = System.nanoTime();
        boolean[] visited = new boolean[names.size()];
        MyQueue queue = new MyQueue();
        StringBuilder order = new StringBuilder();
        long steps = 0;
        int visitedCount = 0;
 
        visited[start] = true;
        queue.enqueue(start);
 
        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            steps++;
            if (visitedCount > 0) {
                order.append(" -> ");
            }
            order.append(names.get(current));
            visitedCount++;
 
            for (int neighbour : adjacency.get(current)) {
                steps++;
                if (!visited[neighbour]) {
                    visited[neighbour] = true;      // mark when added, so it is queued only once
                    queue.enqueue(neighbour);
                }
            }
        }
        long elapsed = System.nanoTime() - begin;
        return new TraversalResult("BFS", order.toString(), visitedCount, steps, elapsed);
    }
 
    /**
     * Depth-First Search from the start vertex. Uses our own MyStack.
     * Goes as deep as possible before backing up. Time complexity O(V + E).
     * Steps = vertices taken from the stack + neighbours checked.
     */
    public TraversalResult dfs(int start) {
        long begin = System.nanoTime();
        boolean[] visited = new boolean[names.size()];
        MyStack stack = new MyStack();
        StringBuilder order = new StringBuilder();
        long steps = 0;
        int visitedCount = 0;
 
        stack.push(start);
 
        while (!stack.isEmpty()) {
            int current = stack.pop();
            steps++;
            if (visited[current]) {
                continue;                           // already visited through another path
            }
            visited[current] = true;
            if (visitedCount > 0) {
                order.append(" -> ");
            }
            order.append(names.get(current));
            visitedCount++;
 
            // Push neighbours in reverse so the first neighbour is visited first
            ArrayList<Integer> neighbours = adjacency.get(current);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                steps++;
                int neighbour = neighbours.get(i);
                if (!visited[neighbour]) {
                    stack.push(neighbour);
                }
            }
        }
        long elapsed = System.nanoTime() - begin;
        return new TraversalResult("DFS", order.toString(), visitedCount, steps, elapsed);
    }
 
    /** Replaces the graph with a small map of Sri Lankan cities, handy for demos. */
    public void loadSampleGraph() {
        clear();
        String[] cities = {"Colombo", "Kandy", "Galle", "Matara", "Kurunegala",
                "Anuradhapura", "Jaffna", "Trincomalee"};
        for (String city : cities) {
            addVertex(city);
        }
        addEdge(indexOf("Colombo"), indexOf("Kandy"));
        addEdge(indexOf("Colombo"), indexOf("Galle"));
        addEdge(indexOf("Colombo"), indexOf("Kurunegala"));
        addEdge(indexOf("Galle"), indexOf("Matara"));
        addEdge(indexOf("Kandy"), indexOf("Kurunegala"));
        addEdge(indexOf("Kandy"), indexOf("Trincomalee"));
        addEdge(indexOf("Kurunegala"), indexOf("Anuradhapura"));
        addEdge(indexOf("Anuradhapura"), indexOf("Jaffna"));
        addEdge(indexOf("Anuradhapura"), indexOf("Trincomalee"));
    }
 
    private void printTraversal(TraversalResult result) {
        System.out.println(result.getAlgorithm() + " order: " + result.getOrder());
        System.out.println("Vertices visited: " + result.getVisitedCount() + " of " + names.size());
        System.out.println("Steps: " + String.format("%,d", result.getSteps())
                + " | Time: " + ResultLog.formatTime(result.getTimeNanos()) + " ns");
    }
 
    /** Asks for a start vertex. Returns its index, or -1 if the vertex does not exist. */
    private int askStartVertex(Scanner sc) {
        String name = InputUtil.readNonEmpty(sc, "Enter start vertex: ");
        int index = indexOf(name);
        if (index == -1) {
            System.out.println("Vertex \"" + name + "\" does not exist. "
                    + "Use option 3 to see the vertices.");
        }
        return index;
    }
 
    /** Graph submenu. Returns when the user chooses "Return to Main Menu". */
    public void runMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Load Sample Graph (Sri Lankan cities)");
            System.out.println("7. Return to Main Menu");
            int choice = InputUtil.readInt(sc, "Enter your choice: ");
 
            switch (choice) {
                case 1 -> {
                    String name = InputUtil.readNonEmpty(sc, "Enter vertex name: ");
                    if (addVertex(name)) {
                        System.out.println("Vertex \"" + name.trim() + "\" added.");
                    } else {
                        System.out.println("Vertex \"" + name.trim() + "\" already exists.");
                    }
                }
                case 2 -> {
                    if (names.size() < 2) {
                        System.out.println("You need at least 2 vertices before adding an edge.");
                    } else {
                        String first = InputUtil.readNonEmpty(sc, "Enter first vertex: ");
                        String second = InputUtil.readNonEmpty(sc, "Enter second vertex: ");
                        int u = indexOf(first);
                        int v = indexOf(second);
                        if (u == -1 || v == -1) {
                            System.out.println("Both vertices must already exist. "
                                    + "Use option 3 to see them.");
                        } else if (u == v) {
                            System.out.println("A vertex cannot be connected to itself.");
                        } else if (hasEdge(u, v)) {
                            System.out.println("That edge already exists.");
                        } else {
                            addEdge(u, v);
                            System.out.println("Edge added: " + getName(u) + " <-> " + getName(v));
                        }
                    }
                }
                case 3 -> display();
                case 4 -> {
                    if (names.isEmpty()) {
                        System.out.println("Graph is empty. Add vertices first "
                                + "or load the sample graph.");
                    } else {
                        int start = askStartVertex(sc);
                        if (start != -1) {
                            TraversalResult result = bfs(start);
                            printTraversal(result);
                            ResultLog.add("Graph Traversal", "BFS",
                                    result.getSteps(), result.getTimeNanos());
                        }
                    }
                }
                case 5 -> {
                    if (names.isEmpty()) {
                        System.out.println("Graph is empty. Add vertices first "
                                + "or load the sample graph.");
                    } else {
                        int start = askStartVertex(sc);
                        if (start != -1) {
                            TraversalResult result = dfs(start);
                            printTraversal(result);
                            ResultLog.add("Graph Traversal", "DFS",
                                    result.getSteps(), result.getTimeNanos());
                        }
                    }
                }
                case 6 -> {
                    loadSampleGraph();
                    System.out.println("Sample graph loaded: " + names.size()
                            + " cities, " + edgeCount + " roads.");
                }
                case 7 -> back = true;
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
        }
    }
}