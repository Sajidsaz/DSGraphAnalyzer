import java.util.ArrayList;
import java.util.HashMap;
 
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
}
