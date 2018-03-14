package AssemBunny;

/**
 * @author /u/Philboyd_Studge on 12/29/2016.
 */
@FunctionalInterface
public interface Executor {

    int apply(Instruction i, int index);
}
