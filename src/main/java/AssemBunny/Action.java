package AssemBunny;

/**
 * @author /u/Philboyd_Studge on 1/1/2017.
 */
@FunctionalInterface
public interface Action {
    /**
     * Integer bi-function called by the Executor
     * @param x
     * @param y
     * @return
     */
    int apply(int x, int y);
}
