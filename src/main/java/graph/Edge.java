package graph;

/**
 * Edge class
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public final class Edge<T> {

    private final Vertex<T> from;
    private final Vertex<T> to;
    private final double cost;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this(from, to, 0.0d);
    }

    public Edge(Vertex<T> from, Vertex<T> to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Vertex<T> getTo() {
        return to;
    }
    public double getCost() {
        return cost;
    }
}
