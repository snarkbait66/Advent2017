package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SearchableQueue class for use with the BFS search algorithm
 * wrapper for LinkedList-based Queue structure
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class SearchableQueue<E> implements SearchableList<E> {
    private Queue<E> queue = new LinkedList<>();

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
