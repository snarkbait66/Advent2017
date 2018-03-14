package graph;

import java.util.List;

/**
 * Directed Graph - Unweighted.
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class DGraph<T> extends BaseGraph<T> {
    /**
     * For this graph, only allow the DFS or BFS search types.
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
