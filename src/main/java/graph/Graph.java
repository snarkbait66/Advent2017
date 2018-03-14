package graph;

import java.util.List;
import java.util.Set;

/**
 * Graph interface
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public interface Graph<T> {
    /**
     * adds a vertex to the graph without creating an edge
     * should be avoided. use addEdge.
     * @param value value of type T to add.
     * @return true if successful
     */
    boolean addVertex(T value);

    /**
     * Adds edge to unweighted graph for Vertices that already exist
     * @param from Vertex from
     * @param to Vertex to
     */
    void addEdge(Vertex<T> from, Vertex<T> to);

    /**
     * Adds edge to unweighted graph, creating new Vertices
     * @param from Value of type T from
     * @param to Value of type T to
     */
    void addEdge(T from, T to);

    /**
     * Adds Edge to weighted graph, creating new vertices
     * @param from Value of type T from
     * @param to Value of type T to
     * @param cost double weight of Edge
     */
    void addEdge(T from, T to, double cost);

    /**
     * Adds Edge to weighted graph, using existing Vertices
     * @param from Vertex from
     * @param to Vertex to
     * @param cost double weight
     */
    void addEdge(Vertex<T> from, Vertex<T> to, double cost);

    /**
     * Run search with specified algorithm
     * using references to type T
     * @param start Start value of type T
     * @param end Goal value of type T
     * @param type SearchType
     * @return List of type T containing all of the steps of the path, or null if no path
     */
    List<T> search(T start, T end, SearchType type);

    /**
     * Run search with specified algorithm
     * using references to type T
     * with option for verbosity
     * @param start Start value of type T
     * @param end Goal value of type T
     * @param type SearchType
     * @param verbose boolean true to display information to System.out
     * @return List of type T containing all of the steps of the path, or null if no path
     */
    List<T> search(T start, T end, SearchType type, boolean verbose);

    /**
     * Run search with specified algorithm
     * using Vertices
     * @param start Vertex to start
     * @param end Vertex for goal
     * @param type SearchType
     * @return List of type T containing all of the steps of the path, or null if no path
     */
    List<T> search(Vertex<T> start, Vertex<T> end, SearchType type);

    /**
     * Run search with specified algorithm
     * using Vertices with option for verbosity
     * @param start Vertex to start
     * @param end Vertex for goal
     * @param type SearchType
     * @param verbose boolean true to display information to System.out
     * @return List of type T containing all of the steps of the path, or null if no path
     */
    List<T> search(Vertex<T> start, Vertex<T> end, SearchType type, boolean verbose);

    /**
     * @return number of unique Vertices in Graph
     */
    int getNumVertices();

    /**
     * @return number of unique Edges in graph
     */
    int getNumEdges();

    /**
     * Get set of vertices as raw element
     * @return deep copy of HashSet of type T
     */
    Set<T> getVertices();

    /**
     * @return size of Graph |V| + |E|
     */
    int size();
}
