package graph;

import java.util.List;

/**
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class Tester {
    public static void main(String[] args) {
        Graph<String> graph = new UWGraph<>();
        graph.addEdge("SAC", "LV", 5);
        graph.addEdge("LV", "PTL", 30);
        graph.addEdge("LV", "LAX", 7);
        graph.addEdge("LAX", "SLC", 3);
        graph.addEdge("SEA", "SLC", 5);
        graph.addEdge("SAC", "SEA", 6);
        System.out.println(graph.getNumVertices());
        System.out.println(graph.getNumEdges());

        List<String> list = graph.search("SAC", "SLC", SearchType.DIJKSTRA, true);
        list = graph.search("SAC","SLC", SearchType.A_STAR, true);
        list.forEach(System.out::println);
    }
}
