package graph;

/**
 * SearchableList interface
 * this is to allow for different types of data structures to be used
 * for the different search algorithms all to be handled by
 * the same interface.
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public interface SearchableList<E> {
    /**
     * Adds a Vertex to the list.
     * @param value Vertex to be added.
     */
    void add(E value);

    /**
     * remove the Vertex from the list.
     * @return Vertex removed.
     */
    E remove();

    /**
     * @return true if list is empty.
     */
    boolean isEmpty();
}
