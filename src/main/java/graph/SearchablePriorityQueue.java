package graph;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * SearchablePriorityQueue for Dijkstra/A_star/Greedy algorithms
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class SearchablePriorityQueue<E> implements SearchableList<E> {
    private Queue<E> queue;

    /**
     * pass the queue the proper comparator for the algorithm
     * @param comparator to base the priority upon
     */
    public SearchablePriorityQueue(Comparator<E> comparator) {
        queue = new PriorityQueue<>(comparator);
    }

    @Override
    public void add(E value) {
        queue.add(value);
    }

    @Override
    public E remove() {
        return queue.remove();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
