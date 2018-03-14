package AssemBunny;

/**
 * @author /u/Philboyd_Studge on 12/28/2016.
 */
public class Register {
    private final char name;
    private int value;

    public Register(char name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}
