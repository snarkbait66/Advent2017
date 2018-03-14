package graph;

/**
 * SearchType enum. Made into its own class initially because there
 * was more going on here, switched to a Factory pattern for generating
 * the proper list structure for searching.
 * @author /u/Philboyd_Studge on 1/1/2016.
 */
public enum SearchType {
    BFS,
    DFS,
    DIJKSTRA,
    A_STAR,
    GREEDY,
    GREEDY_K_OPT
}
