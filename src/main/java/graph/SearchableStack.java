package graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * SearchableStack class for use with DFS search algorithm
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public class SearchableStack<E> implements SearchableList<E> {
    private Deque<E> queue = new LinkedList<>();

    /**
     * add Vertex to top of Stack (push)
     * @param e Vertex to add
     */
    @Override
    public void add(E e) {
        queue.push(e);
    }

    /**
     * remove Vertex from top of Stack (pop)
     * @return Vertex removed
     */
    @Override
    public E remove() {
        return queue.pop();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
