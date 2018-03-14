package graph;

import java.util.Comparator;

/**
 * SearchableListFactory class to return the proper list structure
 * for the different search types.
 * @author /u/Philboyd_Studge on 1/2/2016.
 */
public class SearchableListFactory<T> {

    public static <T> SearchableList<Vertex<T>> getQueue(SearchType type) {
        switch (type) {
            case BFS :
                return new SearchableQueue<>();
            case DFS :
                return new SearchableStack<>();
            case DIJKSTRA :
            case GREEDY :
            case GREEDY_K_OPT :
                return new SearchablePriorityQueue<>(Comparator.comparing(Vertex::getCost));
            case A_STAR:
                return new SearchablePriorityQueue<>(Comparator.comparing(Vertex::getPredictedCost));
        }
        return null;
    }
}
