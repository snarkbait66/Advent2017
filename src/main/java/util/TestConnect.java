package util;

import graph.DGraph;

public class TestConnect {
    public static void main(String[] args) {
        DGraph<Integer> graph = new DGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(9, 3);
        graph.addEdge(3, 5);
        graph.addEdge(5, 7);
        graph.addEdge(9, 11);
        graph.addEdge(2, 7);
        System.out.println(graph.isConnected(1, 2));
        System.out.println(graph.isConnected(1, 3));
        System.out.println(graph.isConnected(1, 9));
        System.out.println(graph.isConnected(1, 7));
    }
}
