package graph;

/**
 * Undirected, Weighted Graph
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class UWGraph<T> extends BaseGraph<T> {

    /**
     * Add edges, assuming input has each edge only once
     * creates two edges, one for each direction
     * if input has entries for each direction, or
     * different costs for each direction, use DWGraph
     * @param v1 Vertex 1
     * @param v2 Vertex 2
     * @param cost Cost of edge
     */
    @Override
    public void addEdge(Vertex<T> v1, Vertex<T> v2, double cost) {
        if (v1 == null || v2 == null) return;
        Edge<T> e1 = new Edge<>(v1, v2, cost);
        Edge<T> e2 = new Edge<>(v2, v1, cost);
        v1.addAdjacent(e1);
        v2.addAdjacent(e2);
        numEdges+= 2;
    }

    @Override
    public void addEdge(T from, T to, double cost) {
        vertices.putIfAbsent(from, new Vertex<T>(from));
        vertices.putIfAbsent(to, new Vertex<T>(to));
        this.addEdge(vertices.get(from), vertices.get(to), cost);
    }
}
