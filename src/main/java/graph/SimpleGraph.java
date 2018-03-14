package graph;

import java.util.*;

/**
 * @author /u/Philboyd_Studge on 2/24/2017.
 */
public class SimpleGraph<T> {
    Map<T, Map<T, Integer>> vertices = new HashMap<>();

    int numVerts() {
        return vertices.size();
    }

    int numEdges() {
        int sum = 0;
        for (Map.Entry<T, Map<T, Integer>> each : vertices.entrySet()) {
            sum += each.getValue().size();
        }
        return sum;
    }

    void addVertex(T data) {
        vertices.putIfAbsent(data, new HashMap<>());
    }

    void addEdge(T v1, T v2, int weight) {
        addVertex(v1);
        addVertex(v2);
        vertices.get(v1).putIfAbsent(v2, weight);
    }

    List<T> BFS(T start, T goal) {
        List<T> path = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Map<T, T> parents = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        boolean found = false;
        queue.add(start);
        while (!queue.isEmpty()) {
            T current = queue.remove();
            //path.add(current);
            if (current.equals(goal)) {
                found = true;
                break;
            }
            for (T each : vertices.get(current).keySet()) {
                if (!visited.contains(each)) {
                    visited.add(each);
                    parents.put(each, current);
                    queue.add(each);
                }
            }
        }
        if (found) {
            T current = goal;
            while (!current.equals(start)) {
                path.add(current);
                current = parents.get(current);
            }
            path.add(start);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        SimpleGraph<String> g = new SimpleGraph<>();
        g.addEdge("Sacramento", "San Jose", 200);
        g.addEdge("Sacramento", "San Fransisco", 180);
        g.addEdge("San Jose", "San Fransisco", 75);
        g.addEdge("San Jose", "Santa Cruz", 40);
        g.addEdge("Santa Cruz", "Los Angeles", 250);
        g.addEdge("San Fransisco", "Fresno", 50);
        g.addEdge("Fresno", "Modesto", 70);
        g.addEdge("Modesto", "Los Angeles", 10);
        System.out.println(g.numVerts());
        System.out.println(g.numEdges());
        List<String> search = g.BFS("Sacramento", "Los Angeles");
        search.forEach(System.out::println);

    }
}
