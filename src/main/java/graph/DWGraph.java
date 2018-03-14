package graph;

/**
 * Directed, Weighted Graph
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class DWGraph<T> extends BaseGraph<T> {

    @Override
    public void addEdge(Vertex<T> from, Vertex<T> to, double cost) {
        if (from == null || to == null) return;
        Edge<T> e = new Edge<>(from, to, cost);
        from.addAdjacent(e);
        numEdges++;
    }

    @Override
    public void addEdge(T from, T to, double cost) {
        vertices.putIfAbsent(from, new Vertex<T>(from));
        vertices.putIfAbsent(to, new Vertex<T>(to));
        this.addEdge(vertices.get(from), vertices.get(to), cost);
    }
}
