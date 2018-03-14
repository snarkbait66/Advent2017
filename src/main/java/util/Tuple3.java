package util;

/**
 * @author /u/Philboyd_Studge on 3/19/2017.
 */
public class Tuple3<T, U, V> extends Tuple2<T, U> {
    private final V v;

    public Tuple3(T t, U u, V v) {
        super(t, u);
        this.v = v;
    }

    /**
     * get third item of tuple
     * @return item of type V
     */
    public V getThird() {
        return v;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple3)) return false;
        if (!super.equals(o)) return false;

        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;

        return v != null ? v.equals(tuple3.v) : tuple3.v == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (v != null ? v.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{ "  + super.getFirst() +
                ", " + super.getSecond() +
                ", " + v.toString() + " }";
    }
}
