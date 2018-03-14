package graph;

import java.util.*;

/**
 * Search class. performs the graph search for the given type.
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class Search<T> {

    /**
     * true if it is desired to have some information
     * printed to console
     */
    private final boolean verbose;

    private final Set<Vertex<T>> visited;
    private final Map<Vertex<T>, Vertex<T>> parents;

    public Search() {
        this(false);
    }

    public Search(boolean verbose) {
        visited = new HashSet<>();
        parents = new HashMap<>();
        this.verbose = verbose;
    }

    public List<T> search(Vertex<T> start, Vertex<T> goal, SearchType type) {
        // ensure markers are empty
        visited.clear();
        parents.clear();

        boolean found = searchPaths(start, goal, type);

        if (!found) {
            if (verbose) System.out.println("No path found.");
            return null;
        }

        return constructPath(start, goal);
    }

    public boolean isConnected(Vertex<T> start, Vertex<T> goal) {
        visited.clear();
        parents.clear();

        return searchPaths(start, goal, SearchType.DFS);

    }

    private boolean searchPaths(Vertex<T> start, Vertex<T> goal, SearchType type) {
        SearchableList<Vertex<T>> queue = SearchableListFactory.getQueue(type);
        int nodesVisited = 0;
        boolean found = false;
        queue.add(start);
        boolean unweighted = type==SearchType.BFS || type == SearchType.DFS;
        if (!unweighted) {
            start.setCost(0.0d);
        }
        while (!queue.isEmpty()) {
            Vertex<T> curr = queue.remove();
            nodesVisited++;
            if (unweighted) {
                if (curr.equals(goal)) {
                    found = true;
                    break;
                }
                searchNeighborsUnweighted(curr, queue);
            } else {
                if (!visited.contains(curr)) {
                    visited.add(curr);
                    if (curr.equals(goal)) {
                        found = true;
                        break;
                    }
                    searchNeighborsWeighted(curr, queue, type, goal);
                }
            }
        }
        if (verbose) System.out.println("Nodes visited for search type " + type.name() + " : " + nodesVisited);
        return found;
    }
    private void searchNeighborsUnweighted(Vertex<T> curr, SearchableList<Vertex<T>> queue) {

        // iterate through adjacent numEdges
        for (Edge<T> each : curr.getAdjacent()) {
            // get Vertex edge is pointing To
            Vertex<T> neighbor = each.getTo();
            if (!visited.contains(neighbor)) {
                // mark & set parent
                visited.add(neighbor);
                parents.put(neighbor, curr);
                // hook
                //nodeSearched.accept(neighbor.getPoint());
                // add to queue
                queue.add(neighbor);
            }
        }
    }

    private void searchNeighborsWeighted(Vertex<T> curr, SearchableList<Vertex<T>> queue, SearchType type, Vertex<T> goal) {
        // iterate through adjacency list
        for (Edge<T> each : curr.getAdjacent()) {

            double totalDistance = curr.getCost() + each.getCost();
            Vertex<T> neighbor = each.getTo();
            if (totalDistance < neighbor.getCost()) {
                neighbor.setCost(totalDistance);
                // find predicted distance to goal for A-star algorithm
                if (type == SearchType.A_STAR) {
                    neighbor.setPredictedCost(totalDistance + neighbor.getHeuristicDistance(goal));
                }
                parents.put(neighbor, curr);
                queue.add(neighbor);
            }
        }
    }

    private List<T> constructPath(Vertex<T> start, Vertex<T> goal) {
        LinkedList<T> path = new LinkedList<>();
        Vertex<T> current = goal;
        while (!current.equals(start)) {
            path.addFirst(current.getValue());
            current = parents.get(current);
        }
        path.addFirst(start.getValue());

        return path;
    }
}
