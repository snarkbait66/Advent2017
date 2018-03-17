package graph;

import java.util.*;

/**
 * BaseGraph abstract class
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public abstract class BaseGraph<T> implements Graph<T> {

    protected Map<T, Vertex<T>> vertices;
    protected int numEdges;

    public BaseGraph() {
        vertices = new HashMap<>();
        numEdges = 0;
    }

    @Override
    public boolean addVertex(T value) {
        if (value==null || vertices.containsKey(value)) return false;
        vertices.put(value, new Vertex<>(value));
        return true;
    }

    @Override
    public void addEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) return;
        from.addAdjacent(new Edge<>(from, to));
        numEdges++;
    }

    @Override
    public void addEdge(T from, T to) {
        vertices.putIfAbsent(from, new Vertex<>(from));
        vertices.putIfAbsent(to, new Vertex<>(to));
        addEdge(vertices.get(from), vertices.get(to));
    }

    @Override
    public void addEdge(T from, T to, double cost) {
        addEdge(from, to);
    }

    @Override
    public void addEdge(Vertex<T> from, Vertex<T> to, double cost) {
        addEdge(from, to);
    }

    @Override
    public List<T> search(T start, T end, SearchType type) {
        return search(vertices.get(start), vertices.get(end), type);
    }

    @Override
    public List<T> search(T start, T end, SearchType type, boolean verbose) {
        return search(vertices.get(start), vertices.get(end), type, verbose);
    }

    @Override
    public List<T> search(Vertex<T> start, Vertex<T> end, SearchType type) {
        Search<T> search = new Search<>();
        List<T> path = search.search(start, end, type);
        if (type != SearchType.BFS && type !=SearchType.DFS) reset();
        return path;
    }

    @Override
    public List<T> search(Vertex<T> start, Vertex<T> end, SearchType type, boolean verbose) {
        Search<T> search = new Search<>(verbose);
        List<T> path = search.search(start, end, type);
        if (type != SearchType.BFS && type !=SearchType.DFS) reset();
        return path;
    }

    /**
     * returns true is vertex points for data start and goal
     * are connected in the graph, using a DFS. data points must already
     * be defined vertices in the graph.
     * Unlike the search methods, does not return the constructed path.
     * @param start T data for starting vertex
     * @param goal T data for goal vertex
     * @return true is connected
     */
    public boolean isConnected(T start, T goal) {
        Vertex<T> u = vertices.get(start);
        Vertex<T> v = vertices.get(goal);
        if (u.equals(null) || v.equals(null)) {
            throw new NullPointerException("Vertex does not exist!");
        }
        return isConnected(u, v);
    }

    /**
     * Returns true if Vertices start and goal are connected
     * in the graph, using a DFS. Vertices must exist in the
     * graph.
     * @param start vertex to start
     * @param goal vertex to end
     * @returntrue if connected
     */
    public boolean isConnected(Vertex<T> start, Vertex<T> goal) {
        Search<T> search = new Search<>();
        return search.isConnected(start, goal);
    }


    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public int getNumEdges() {
        return numEdges;
    }

    @Override
    public Set<T> getVertices() {
        return new HashSet<>(vertices.keySet());
    }

    @Override
    public int size() {
        return getNumVertices() + getNumEdges();
    }

    private void reset() {
        for (Map.Entry<T, Vertex<T>> each : vertices.entrySet()) {
            each.getValue().setCost(Double.POSITIVE_INFINITY);
            each.getValue().setPredictedCost(Double.POSITIVE_INFINITY);
        }
    }

}
