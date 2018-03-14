package graph;

import java.util.List;

/**
 * Undirected Graph - Unweighted.
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class UGraph<T> extends BaseGraph<T> {

    /**
     * Assumes input has each undirected edge once.
     * if input has entries for each direction, use DGraph.
     * this will create two edges. Vertices must already exist.
     * @param v1 Vertex 1
     * @param v2 Vertex 2
     */
    @Override
    public void addEdge(Vertex<T> v1, Vertex<T> v2) {
        if (v1 == null || v2 == null) return;
        v1.addAdjacent(new Edge<>(v1, v2));
        v2.addAdjacent(new Edge<>(v2, v1));
        numEdges+= 2;

    }

    /**
     * only allow DFS and BFS searches.
     * @param start Vertex to search from
     * @param end Vertex to search to
     * @param type type of search
     * @return List of type T
     */
    @Override
    public List<T> search(Vertex<T> start, Vertex<T> end, SearchType type) {
        if (type != SearchType.BFS && type != SearchType.DFS) {
            throw new IllegalArgumentException("Only unweighted searched allowed");
        }
        return super.search(start, end, type);
    }
}
